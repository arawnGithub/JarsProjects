package com.arawn.cms.task;

import com.arawn.cms.entity.Tag;
import com.arawn.cms.service.TagService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.List;

/**
 * 标签数据刷新任务
 * Created by ArawN on 2017/12/24.
 */
@Component
public class TagRefreshTask {

    @Resource
    private TagService tagService;

    /**
     * 从0小时开始，每2个小时执行一次
     */
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void run() {
        ServletContext application = ContextLoader.getCurrentWebApplicationContext().getServletContext();

        // 刷新标签数据
        List<Tag> tagList = tagService.listByRand(200);
        application.setAttribute("tagList", tagList);
    }
}
