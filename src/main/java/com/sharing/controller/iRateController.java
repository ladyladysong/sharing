package com.sharing.controller;


import com.sharing.common.ServerResponse;
import com.sharing.service.iRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Slf4j
@RequestMapping("/rating")
@Controller
public class iRateController {
    @Autowired
    private iRatingService iRatingService;

    @RequestMapping("rate_user.do")
    @ResponseBody
    public ServerResponse rateUser(HttpSession session, Integer uid, Integer oid, double rating){
        return iRatingService.rateUser(uid,oid,rating);
    }
}
