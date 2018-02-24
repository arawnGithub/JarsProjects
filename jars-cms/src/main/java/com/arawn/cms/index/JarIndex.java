package com.arawn.cms.index;

import com.arawn.cms.constant.IndexConstant;
import com.arawn.cms.entity.Jar;
import com.arawn.cms.util.StringUtil;
import lombok.Cleanup;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
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

    private Directory directory;

    /**
     * 获取IndexWriter实例
     * @return
     * @throws Exception
     */
    private IndexWriter getWriter() throws Exception {
        directory = FSDirectory.open(Paths.get(IndexConstant.FILE_PATH));

        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(directory, config);
        return writer;
    }

    /**
     * 添加jar包索引
     * @param jar
     * @throws Exception
     */
    public void addIndex(Jar jar) throws Exception {
        @Cleanup IndexWriter writer = getWriter();

        Document document = new Document();
        document.add(new StringField(IndexConstant.JAR_ID,
                jar.getJarId(), Field.Store.YES));
        document.add(new TextField(IndexConstant.NAME,
                jar.getName().replaceAll(IndexConstant.HYPHEN, IndexConstant.BLANK), Field.Store.YES));

        writer.addDocument(document);
    }

    /**
     * 更新jar包索引
     * @param jar
     * @throws Exception
     */
    public void updateIndex(Jar jar) throws Exception {
        @Cleanup IndexWriter writer = getWriter();

        Document document = new Document();
        document.add(new StringField(IndexConstant.JAR_ID,
                jar.getJarId(), Field.Store.YES));
        document.add(new TextField(IndexConstant.NAME,
                jar.getName().replaceAll(IndexConstant.HYPHEN, IndexConstant.BLANK), Field.Store.YES));

        writer.updateDocument(new Term(IndexConstant.JAR_ID, jar.getJarId()), document);
    }

    /**
     * 删除指定jar包的索引
     * @param jarId
     * @throws Exception
     */
    public void deleteIndex(String jarId) throws Exception {
        @Cleanup IndexWriter writer = getWriter();

        writer.deleteDocuments(new Term(IndexConstant.JAR_ID, jarId));
        writer.forceMergeDeletes(); // 强制删除
        writer.commit();
    }

    /**
     * 查询Jar包信息
     * @param q
     * @param num
     * @return
     * @throws Exception
     */
    public List<Jar> searchJar(String q, int num) throws Exception {
        directory = FSDirectory.open(Paths.get(IndexConstant.FILE_PATH));
        IndexReader reader = DirectoryReader.open(directory);
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

                // 如果高亮的jar名称不为空才设置，否则设置原先的jar名称
                if (StringUtil.isNotEmpty(hasTagName)) {
                    jar.setHasTagName(hasTagName);
                } else {
                    jar.setHasTagName(name);
                }
            }

            jarList.add(jar);
        }
        return jarList;
    }

}