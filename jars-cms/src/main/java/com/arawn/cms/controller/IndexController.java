package com.arawn.cms.controller;

import com.arawn.cms.entity.Tag;
import com.arawn.cms.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页Controller
 * Created by ArawN on 2017/11/22.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private TagService tagService;

    /**
     * 请求首页
     * @return
     * @throws Exception
     */
    @RequestMapping("/index")
    public ModelAndView index() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Tag> tagList = tagService.listByRand(200);

        mav.addObject(tagList);
        mav.setViewName("index");
        return mav;
    }
}
