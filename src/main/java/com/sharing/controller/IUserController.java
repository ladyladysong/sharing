package com.sharing.controller;

import com.sharing.common.Const;
import com.sharing.common.ServerResponse;
import com.sharing.pojo.User;
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
    public ServerResponse<User> login(HttpSession session, String email, String passwd){
        ServerResponse<User> response=iUserService.login(email,passwd);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }
}
