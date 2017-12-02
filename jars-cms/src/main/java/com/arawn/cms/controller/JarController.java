package com.arawn.cms.controller;

import com.arawn.cms.entity.Jar;
import com.arawn.cms.index.JarIndex;
import com.arawn.cms.service.TagService;
import com.arawn.cms.util.FastJsonUtil;
import com.arawn.cms.util.PageUtil;
import com.arawn.cms.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Jar包Controller
 * Created by ArawN on 2017/11/25.
 */
@Controller
@RequestMapping("/jar")
public class JarController {

    private static Logger logger = Logger.getLogger(JarController.class);

    @Resource
    private TagService tagService;

    @Resource
    private JarIndex jarIndex;

    /**
     * 根据关键字查询相关Jar包信息
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/query")
    public ModelAndView query(@RequestParam("q") String q, @RequestParam("page") Integer page, HttpServletRequest request) throws Exception {
        logger.info("JarController query start ==>" + FastJsonUtil.toJSONString(q));

        if (StringUtil.isEmpty(q)) {
            return null;
        }

        if (page == null) {
            page = 1;
        }

        ModelAndView mav = new ModelAndView();
        List<Jar> jarList = jarIndex.searchJar(q.trim(), 200);

        int resultTotal = jarList.size();
        int fromIndex = (page - 1) * 20;
        int toIndex = resultTotal >= page * 20 ? page * 20 : resultTotal;

        mav.addObject("q", q);
        mav.addObject("jarList", jarList.subList(fromIndex, toIndex));
        mav.addObject("resultTotal", resultTotal);
        mav.addObject("tagList", tagService.listByRand(200));
        mav.addObject("pageCode", PageUtil.genPagination(request.getContextPath() + "/jar/query.do", resultTotal, page, 20, "q=" + q));
        mav.setViewName("result");

        logger.info("JarController query end ==>" + FastJsonUtil.toJSONString(mav));
        return mav;
    }
}
