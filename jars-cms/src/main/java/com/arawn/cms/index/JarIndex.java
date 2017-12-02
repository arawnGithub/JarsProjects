package com.arawn.cms.index;

import com.arawn.cms.constant.IndexConstant;
import com.arawn.cms.entity.Jar;
import com.arawn.cms.util.StringUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Jar包索引类
 * Created by ArawN on 2017/12/2.
 */
public class JarIndex {

    private Directory dir;

    /**
     * 查询Jar包信息
     * @param q
     * @param num
     * @return
     * @throws Exception
     */
    public List<Jar> searchJar(String q, int num) throws Exception {
        dir = FSDirectory.open(Paths.get(IndexConstant.FILE_PATH));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        Analyzer analyzer = new StandardAnalyzer();
        QueryParser parser = new QueryParser(IndexConstant.NAME, analyzer);
        Query query = parser.parse(q);

        TopDocs topDocs = searcher.search(query, num); // 查询num条

        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter(IndexConstant.PREFIX_TAG, IndexConstant.SUFFIX_TAG);
        QueryScorer scorer = new QueryScorer(query);
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);

        Highlighter highlighter = new Highlighter(formatter, scorer);
        highlighter.setTextFragmenter(fragmenter);

        List<Jar> jarList = new LinkedList<>();
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document document = searcher.doc(scoreDoc.doc);
            Jar jar = new Jar();
            jar.setJarId(document.get(IndexConstant.JAR_ID));

            String name = document.get(IndexConstant.NAME);
            jar.setName(name);
            if (StringUtil.isNotEmpty(name)) {
                TokenStream tokenStream = analyzer.tokenStream(IndexConstant.NAME, new StringReader(name));
                String hasTagName = highlighter.getBestFragment(tokenStream, name);
                if (StringUtil.isEmpty(hasTagName)) {
                    jar.setHasTagName(name);
                } else {
                    jar.setHasTagName(hasTagName);
                }
            }

            jarList.add(jar);
        }

        return jarList;
    }

}
