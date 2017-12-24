package com.arawn.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页Controller
 * Created by ArawN on 2017/11/22.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    /**
     * 请求首页
     * @return
     * @throws Exception
     */
    @RequestMapping("/index")
    public ModelAndView index() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
}
