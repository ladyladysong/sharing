package com.sharing.controller;

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
    public ServerResponse<User> login(HttpSession session, String email, String password){
        ServerResponse<User> response = iUserService.login(email,password);
        if (response.isSuccess()){
            //record session
            session.setAttribute("user",email);
        }else
            session.setAttribute("error msg:",response.getMsg());
        //log.info("log :{}",1);
        return response;
    }



//    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
//    @ResponseBody
//    public ServerResponse<String> logout(){
//
//
//    }

    @RequestMapping("register.do")
    @ResponseBody
    public ServerResponse<String> register(HttpSession session, String email, String password){
        log.info("1");
        return iUserService.register(email,password);
    }







//    @RequestMapping("get_user_info.do")
//    @ResponseBody
//    public ServerResponse<String> getUserInfo(){
//
//    }


//    public ServerResponse<String> resetPassword(HttpSession session, String email){
//        ServerResponse<String> response;
//        //user login -> direct reset
//
//        //user not login -> email link
//        return response;
//    }

}
