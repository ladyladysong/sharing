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

    @RequestMapping("logout.do")
    @ResponseBody
    public void logout(HttpSession session){
        session.invalidate();

    }

    @RequestMapping("register.do")
    @ResponseBody
    public ServerResponse<User> register(HttpSession session, String email, String password){
        log.info("1");
        ServerResponse<User> response = iUserService.register(email,password);
        return response;
    }


    @RequestMapping("get_user_info.do")
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        String id = session.getId();

        return iUserService.get_user_info(id);
    }

    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse<String> update_info(HttpSession session, User user){
        String id = session.getId();
        return iUserService.update_info(user,id);

    }

    @RequestMapping("update_password.do")
    @ResponseBody
    public ServerResponse<String> update_password(HttpSession session, String password){
        String id = session.getId();
        return iUserService.update_password(id,password);
    }


//   reset_password.do {email}
//    update_location.do {lo;la}


//    public ServerResponse<String> resetPassword(HttpSession session, String email){
//        ServerResponse<String> response;
//        //user login -> direct reset
//
//        //user not login -> email link
//        return response;
//    }

}
