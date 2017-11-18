package com.arawn.controller;

import com.arawn.service.ManagerService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * 管理员Controller
 * Created by ArawN on 2017/11/18.
 */
@Controller("/myManager")
public class ManagerController {

    @Resource
    private ManagerService managerService;
}
