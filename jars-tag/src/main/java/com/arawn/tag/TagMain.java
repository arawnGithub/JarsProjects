package com.arawn.tag;

import com.arawn.dao.JarDao;
import com.arawn.dao.TagDao;
import com.arawn.entity.Jar;
import com.arawn.entity.Tag;
import org.apache.log4j.Logger;

import java.util.List;

/**
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
            String[] nameArray = jar.getName().replaceAll(".jar", "").split("-");
            for (String name : nameArray) {
                if (name.contains(".")) {
                    continue;
                }

                boolean exist = tagDao.existByName(name);
                if (!exist) {
                    Tag tag = new Tag();
                    tag.setName(name);
                    tagDao.insert(tag);

                    jarDao.updateTagStateByJarId(jar.getJarId());
                }
            }
        }

        logger.info("结束生成标签");
    }
}
