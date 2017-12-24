package com.arawn.cms.controller;

import com.arawn.cms.constant.IndexConstant;
import com.arawn.cms.entity.Jar;
import com.arawn.cms.index.JarIndex;
import com.arawn.cms.service.JarService;
import com.arawn.cms.util.FastJsonUtil;
import com.arawn.cms.util.PageUtil;
import com.arawn.cms.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private JarService jarService;

    @Resource
    private JarIndex jarIndex;

    /**
     * 根据关键字查询相关Jar包信息
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/query")
    public ModelAndView query(@RequestParam("q") String q, @RequestParam(value = "page", required = false) Integer page, HttpServletRequest request) throws Exception {
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
        int toIndex = resultTotal > page * 20 ? page * 20 : resultTotal;

        mav.addObject("q", q);
        mav.addObject("jarList", jarList.subList(fromIndex, toIndex));
        mav.addObject("resultTotal", resultTotal);
        mav.addObject("pageCode", PageUtil.genPagination(request.getContextPath() + "/jar/query.do", resultTotal, page, 20, "&q=" + q));
        mav.setViewName("result");

        // logger.info("JarController query end ==>" + FastJsonUtil.toJSONString(mav));
        return mav;
    }

    /**
     * 请求具体jar包页面
     * @param jarId
     * @return
     * @throws Exception
     */
    @RequestMapping("/view/{jarId}")
    public ModelAndView view(@PathVariable("jarId") String jarId) throws Exception {
        logger.info("JarController view start ==>" + FastJsonUtil.toJSONString(jarId));

        if (StringUtil.isEmpty(jarId)) {
            return null;
        }

        ModelAndView mav = new ModelAndView();
        Jar jar = jarService.queryByJarId(jarId);

        // 更新点击次数
        jar.setClick(jar.getClick() + 1);
        jarService.updateByJarId(jar);

        mav.addObject("jar", jar);
        mav.addObject("relJarList", jarIndex.searchJar(jar.getName().replaceAll(IndexConstant.HYPHEN, IndexConstant.BLANK), 16));
        mav.setViewName("view");

        // logger.info("JarController view end ==>" + FastJsonUtil.toJSONString(mav));
        return mav;
    }

    /**
     * 下载指定jar包
     * @param jarId
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/download/{jarId}")
    public void download(@PathVariable("jarId") String jarId, HttpServletResponse response) throws Exception {
        logger.info("JarController download start ==>" + FastJsonUtil.toJSONString(jarId));

        if (StringUtil.isEmpty(jarId)) {
            return;
        }

        Jar jar = jarService.queryByJarId(jarId);

        // 更新下载次数
        jar.setDownHit(jar.getDownHit() + 1);
        jarService.updateByJarId(jar);

        logger.info("JarController download end ==>" + FastJsonUtil.toJSONString(jar));
        response.sendRedirect(jar.getPath());
    }

}
