package com.arawn.controller;

import com.arawn.entity.Manager;
import com.arawn.service.ManagerService;
import com.arawn.utils.Md5Utils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 管理员Controller
 * Created by ArawN on 2017/11/18.
 */
@Controller
@RequestMapping("/myManager")
public class ManagerController {

    private Logger logger = Logger.getLogger(ManagerController.class);

    @Resource
    private ManagerService managerService;

    /**
     * 管理员登录
     * @param manager
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(Manager manager, HttpServletRequest request) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(manager.getUsername(),
                Md5Utils.md5(manager.getPassword(), Md5Utils.SALT));
        try {
            subject.login(token); // 登录验证
            logger.info("登录成功 ==> username:" + token.getPrincipal());

            return "redirect:/admin/main.jsp";
        } catch (AuthenticationException e) {
            logger.error("登录失败 ==> username:" + token.getPrincipal());

            request.setAttribute("manager", manager);
            request.setAttribute("errorMsg", "用户名或密码错误");
            return "login";
        }
    }

}
