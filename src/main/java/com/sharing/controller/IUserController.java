package com.sharing.controller;

import com.sharing.common.Const;
import com.sharing.common.ServerResponse;
import com.sharing.pojo.User;
import com.sharing.service.iUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ServerResponse logout(HttpSession session){
        session.invalidate();
        return ServerResponse.createBySuccess();
    }

    @RequestMapping("register.do")
    @ResponseBody
    public ServerResponse register(HttpSession session, String email, String password){
        return iUserService.register(email,password);
    }


    @RequestMapping("get_user_info.do")
    @ResponseBody
    public ServerResponse getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return iUserService.get_user_info(user.getId());
    }

    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update_info(HttpSession session,@RequestParam(required = false) String username,
                                      @RequestParam(required = false) Integer gender,
                                      @RequestParam(required = false) String phone,
                                      @RequestParam(required = false) String image,
                                      @RequestParam(required = false) String tag){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        User update = new User();
        update.setUsername(username);
        update.setTel(phone);
        update.setGender(gender);
        update.setImage(image);
        return iUserService.update_info(user.getId(),update,tag);

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


}
