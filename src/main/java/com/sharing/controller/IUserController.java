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
import javax.swing.text.html.StyleSheet;


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
            log.info("successful");
            session.setAttribute("user",email);
        }else
            session.setAttribute("error msg:",response.getMsg());
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
    public ServerResponse register(HttpSession session, String email, String password){
        log.info("1");
        return iUserService.register(email,password);
    }

    @RequestMapping("get_user_info.do")
    @ResponseBody
    public ServerResponse<String> getUserInfo(HttpSession session){
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



//    public ServerResponse<String> resetPassword(HttpSession session, String email){
//        ServerResponse<String> response;
//        //user login -> direct reset
//
//        //user not login -> email link
//        return response;
//    }

}
