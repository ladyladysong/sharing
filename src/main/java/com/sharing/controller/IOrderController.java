package com.sharing.controller;

import com.sharing.common.Const;
import com.sharing.common.ServerResponse;
import com.sharing.pojo.Order;
import com.sharing.pojo.User;
import com.sharing.service.iOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Slf4j
@Controller
@RequestMapping("/order")
public class IOrderController {

    @Autowired
    private iOrderService iOrderService;

    @RequestMapping("create.do")
    @ResponseBody
    public ServerResponse create(HttpSession session,Order order){
        User user=(User) session.getAttribute(Const.CURRENT_USER);
        if(!user.getId().equals(order.getUserProfileId())){
            log.info(order.getUserProfileId().toString());
            log.info(user.getId().toString());
            return ServerResponse.createByErrorMessage("invalid operation, try to alter others' order");
        }
        return iOrderService.createOrder(order);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse list(HttpSession session, BigDecimal latitute, BigDecimal longitude){
        User user=(User) session.getAttribute(Const.CURRENT_USER);
        return iOrderService.list(latitute,longitude,user.getId());
    }

    @RequestMapping("get_detail.do")
    @ResponseBody
    public ServerResponse getOrderDetail(HttpSession session, Long orderNo){
        User user=(User) session.getAttribute(Const.CURRENT_USER);
        return iOrderService.getOrderDetail(orderNo,user.getId());
    }

    @RequestMapping("change_order_status.do")
    @ResponseBody
    public ServerResponse changeOrderStatus(HttpSession session, Long orderNo, Integer status){
        User user=(User) session.getAttribute(Const.CURRENT_USER);
        return iOrderService.changeOrderStatus(orderNo,status,user.getId());
    }

    @RequestMapping("get_candidates.do")
    @ResponseBody
    public ServerResponse getCandidates(HttpSession session,Long orderNo){
        User user=(User) session.getAttribute(Const.CURRENT_USER);
        return iOrderService.getCandidates(orderNo,user.getId());
    }

}
