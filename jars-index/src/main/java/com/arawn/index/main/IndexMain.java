package com.arawn.index.main;

import com.arawn.index.constant.IndexConstant;
import com.arawn.lib.dao.JarDao;
import com.arawn.lib.entity.Jar;
import lombok.Cleanup;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;
import java.util.List;

/**
 * 索引起始类
 * Created by ArawN on 2017/9/23.
 */
public class IndexMain {

    private static Logger logger = Logger.getLogger(IndexMain.class);

    /**
     * jar包Dao
     */
    private static JarDao jarDao = new JarDao();

    public static void main(String[] args) {
        logger.info("创建索引开始");

        @Cleanup IndexWriter indexWriter = null;
        try {
            Directory directory = FSDirectory.open(Paths.get(IndexConstant.FILE_PATH));

            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            indexWriter = new IndexWriter(directory, indexWriterConfig);

            Jar param = new Jar();
            param.setIndexState(0);
            List<Jar> jarList = jarDao.listByParam(param);

            for (Jar jar : jarList) {
                Document document = new Document();
                document.add(new StringField(IndexConstant.JAR_ID,
                        jar.getJarId(), Field.Store.YES));

                document.add(new TextField(IndexConstant.NAME,
                        jar.getName().replaceAll(IndexConstant.HYPHEN, IndexConstant.BLANK), Field.Store.YES));

                indexWriter.addDocument(document);
                logger.info("添加document:" + document);

                jarDao.updateIndexStateByJarId(jar.getJarId());
            }

        } catch (Exception e) {
            logger.error("创建索引异常", e);
        }

        logger.info("创建索引完成");
    }
}
