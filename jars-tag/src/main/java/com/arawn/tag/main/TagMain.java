package com.arawn.tag.main;

import com.arawn.lib.dao.JarDao;
import com.arawn.lib.dao.TagDao;
import com.arawn.lib.entity.Jar;
import com.arawn.lib.entity.Tag;
import com.arawn.tag.constant.TagConstant;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 标签起始类
 * Created by ArawN on 2017/9/23.
 */
public class TagMain {

    private static Logger logger = Logger.getLogger(TagMain.class);

    /**
     * jar包Dao
     */
    private static JarDao jarDao = new JarDao();

    /**
     * 标签Dao
     */
    private static TagDao tagDao = new TagDao();

    public static void main(String[] args) {
        logger.info("开始生成标签");

        Jar param = new Jar();
        param.setTagState(0);
        List<Jar> jarList = jarDao.listByParam(param);

        for (Jar jar : jarList) {
			// 更新jar包生成标签状态
			jarDao.updateTagStateByJarId(jar.getJarId());
			
            String tagName = jar.getName().split(TagConstant.HYPHEN)[0];
			if (tagName.contains(TagConstant.POINT)) {
                continue;
            }

            boolean exist = tagDao.existByName(tagName);
            if (!exist) {
                Tag tag = new Tag();
                tag.setName(tagName);
                tagDao.insert(tag);
            }
        }

        logger.info("结束生成标签");
    }
}
