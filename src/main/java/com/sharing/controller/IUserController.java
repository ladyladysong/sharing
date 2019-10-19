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
import java.math.BigDecimal;


@Slf4j
@Controller
@RequestMapping("/user")
public class IUserController {

    @Autowired
    private iUserService iUserService;

    @RequestMapping("login.do")
    @ResponseBody
    public ServerResponse<User> login(HttpSession session, String email, String password){
        ServerResponse<User> response=iUserService.login(email,password);
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
    public ServerResponse register(HttpSession session, String email, String password, BigDecimal latitute, BigDecimal longitude){
        return iUserService.register(email,password,latitute,longitude);
    }


    @RequestMapping("get_user_info.do")
    @ResponseBody
    public ServerResponse getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return iUserService.get_user_info(user.getId());
    }

    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update_info(HttpSession session,String info){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return iUserService.update_info(user.getId(),info);

    }

    @RequestMapping("update_password.do")
    @ResponseBody
    public ServerResponse update_password(HttpSession session, String password){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return iUserService.update_password (user.getId(),password);
    }

    @RequestMapping("update_location.do")
    @ResponseBody
    public ServerResponse update_location(HttpSession session, BigDecimal latitute, BigDecimal longitude){
        User user=(User) session.getAttribute(Const.CURRENT_USER);
        return iUserService.update_lo(user.getId(),latitute,longitude);
    }


//   reset_password.do {email}


//    public ServerResponse<String> resetPassword(HttpSession session, String email){
//        ServerResponse<String> response;
//        //user login -> direct reset
//
//        //user not login -> email link
//        return response;
//    }

}
