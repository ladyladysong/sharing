package com.sharing.controller;

import com.sharing.common.ServerResponse;
import com.sharing.service.iUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/user")
public class IUserController {

    @Autowired
    private iUserService iUserService;
    @RequestMapping("login.do")
    @ResponseBody
    public ServerResponse<String> login(HttpSession session, String email, String passwd){
        log.info("log :{}",1);
        return iUserService.login(email,passwd);
    }
}
