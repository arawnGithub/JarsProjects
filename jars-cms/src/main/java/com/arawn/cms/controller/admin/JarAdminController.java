package com.arawn.cms.controller.admin;

import com.arawn.cms.entity.EasyUIResult;
import com.arawn.cms.entity.Jar;
import com.arawn.cms.entity.PageBean;
import com.arawn.cms.service.JarService;
import com.arawn.cms.util.FastJsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jar包后台Controller
 * Created by ArawN on 2017/12/3.
 */
@Controller
@RequestMapping("/admin/jar")
public class JarAdminController {

    @Resource
    private JarService jarService;

    /**
     * 分页查询Jar包信息
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows) throws Exception {
        PageBean pageBean = new PageBean(page, rows);

        // 封装查询条件
        Map<String, Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());

        List<Jar> jarList = jarService.listByMap(map);
        Long jarCount = jarService.countByMap(map);

        EasyUIResult<Jar> result = new EasyUIResult<>(jarList, jarCount);
        return FastJsonUtil.toJSONString(result);
    }

}
