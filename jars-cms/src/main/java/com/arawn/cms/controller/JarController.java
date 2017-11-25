package com.arawn.cms.controller;

import com.arawn.cms.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Jar包Controller
 * Created by ArawN on 2017/11/25.
 */
@Controller
@RequestMapping("/jar")
public class JarController {

    @Resource
    private TagService tagService;

    /**
     * 根据关键字查询相关Jar包信息
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/query")
    public ModelAndView query(@RequestParam(value = "q", required = false) String q) throws Exception {
        ModelAndView mav = new ModelAndView();

        mav.addObject("q", q);
        mav.addObject("tagList", tagService.listByRand(200));
        mav.setViewName("result");
        return mav;
    }
}
