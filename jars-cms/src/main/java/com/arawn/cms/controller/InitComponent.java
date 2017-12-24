package com.arawn.cms.controller;

import com.arawn.cms.entity.Tag;
import com.arawn.cms.service.TagService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.List;

/**
 * 标签数据初始化
 * Created by ArawN on 2017/12/24.
 */
@Component
public class InitComponent extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        super.contextInitialized(sce);

        ServletContext application = sce.getServletContext();
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);

        TagService tagService = (TagService)applicationContext.getBean("tagService");

        // 初始化标签数据
        List<Tag> tagList = tagService.listByRand(200);
        application.setAttribute("tagList", tagList);
    }
}
