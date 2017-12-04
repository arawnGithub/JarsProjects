package com.arawn.cms.controller.admin;

import com.arawn.cms.entity.Manager;
import com.arawn.cms.service.ManagerService;
import com.arawn.cms.util.FastJsonUtil;
import com.arawn.cms.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员后台Controller
 * Created by ArawN on 2017/12/4.
 */
@Controller
@RequestMapping("/admin/manager")
public class ManagerAdminController {

    @Resource
    private ManagerService managerService;

    /**
     * 修改管理员密码
     * @param manager
     * @return
     * @throws Exception
     */
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public String modifyPassword(Manager manager) throws Exception {
        manager.setPassword(MD5Util.md5(manager.getPassword(), MD5Util.SALT));
        int resultTotal = managerService.updateById(manager);

        Map<String, Object> map = new HashMap<>();
        if (resultTotal > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return FastJsonUtil.collectToString(map);
    }

    /**
     * 注销
     * @return
     * @throws Exception
     */
    @RequestMapping("/logout")
    public String logout() throws Exception {
        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }
}
