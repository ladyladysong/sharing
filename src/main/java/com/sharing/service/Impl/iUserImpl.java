package com.sharing.service.Impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sharing.common.ServerResponse;
import com.sharing.dao.UserMapper;
import com.sharing.pojo.User;
import com.sharing.service.iUserService;
import com.sharing.util.DateTimeUtil;
import com.sharing.util.JsonUtil;
import com.sharing.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service("iUserService")
public class iUserImpl implements iUserService {
    @Autowired
    private UserMapper userMapper;

    public ServerResponse<User> login(String email, String passwd) {
        int count = userMapper.checkEmail(email);
        if (count == 0) {
            return ServerResponse.createByErrorMessage("user not exists");
        }
        String md5Passwd = MD5Util.MD5EncodeUtf8(passwd);
        User user = userMapper.selectByEmail(email, md5Passwd);
        if (user == null) {
            return ServerResponse.createByErrorMessage("wrong password "+md5Passwd);
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }

    public ServerResponse register(String email,String password){
        int count = userMapper.checkEmail(email);
        if (count!=0){
            return ServerResponse.createByErrorMessage("user already exists");
        }
        String md5Pwd = MD5Util.MD5EncodeUtf8(password);
        User user = new User();
        user.setUsername(email);
        user.setEmail(email);
        user.setPassword(md5Pwd);
        int res = userMapper.addNewUser(user);
        if (res==0){
            return ServerResponse.createByErrorMessage("Not successfully register");
        }
        return ServerResponse.createBySuccessMessage("Successfully register");

    }

    public ServerResponse register(String email, String password, BigDecimal latitute, BigDecimal longitude){
        int count = userMapper.checkEmail(email);
        if (count!=0){
            return ServerResponse.createByErrorMessage("user already exists");
        }
        String md5Pwd = MD5Util.MD5EncodeUtf8(password);
        User user = new User();
        user.setUsername(email);
        user.setEmail(email);
        user.setPassword(md5Pwd);
        user.setLocationLatitute(latitute);
        user.setLocationLongitude(longitude);
        int res = userMapper.addNewUser(user);
        if (res==0){
            return ServerResponse.createByErrorMessage("Not successfully register");
        }
        return ServerResponse.createBySuccessMessage("Successfully register");

    }

    public ServerResponse<User> get_user_info(Integer id){
        User record = userMapper.selectByPrimaryKey(id);
        if (record==null){
            return ServerResponse.createByErrorMessage("No such user records!");
        }
        record.setPassword(StringUtils.EMPTY);
        String CreateTime = DateTimeUtil.dateToStr(record.getCreateTime());

        //TODO: Time format transformation

        return ServerResponse.createBySuccess("retrieve record successfully, createTime = "+CreateTime,record);
    }


    public ServerResponse update_info( Integer id, String info){

        //User update = JsonUtil.string2Obj(info,User.class);

        User user = userMapper.selectByPrimaryKey(id);
        log.info("original user info : "+JsonUtil.obj2StringPretty(user));
        if (user==null){
            return ServerResponse.createByErrorMessage("No such user");
        }
        if (info==null){
            return ServerResponse.createByErrorMessage("Nothing to update");
        }

        User update = parseUpdateInfo(user,info);
        int re = userMapper.updateByPrimaryKeySelective(update);
        if (re==0){
            return ServerResponse.createByErrorMessage("Cannot update information");
        }
        return ServerResponse.createBySuccessMessage("Successfully update");

    }

    public ServerResponse update_password(String id, String pwd_){
        User user = userMapper.selectByPrimaryKey(Integer.parseInt(id));
        if (user!=null){
            user.setPassword(pwd_);
            int re =userMapper.updateByPrimaryKeySelective(user);
            if (re > 0){
                return ServerResponse.createBySuccessMessage("successfully update password");
            }
            return ServerResponse.createByErrorMessage("cannot update password");
        }
        return ServerResponse.createByErrorMessage("No such user");
    }


    public ServerResponse update_lo(Integer id, BigDecimal latitute, BigDecimal longitude){

        int re = userMapper.updateLoById(id,latitute,longitude);
        if (re==0){
            return ServerResponse.createByErrorMessage("Cannot update current location");
        }
        return ServerResponse.createBySuccessMessage("Successfully update location");
    }


    private User parseUpdateInfo(User user,String info){
        JSONObject info_list = JSON.parseObject(info);
        String username = info_list.getString("username");
        Integer gender = info_list.getInteger("gender");
        String tel = info_list.getString("tel");
        String image = info_list.getString("image");
        log.info("username="+username);
        log.info("gender="+gender);
        log.info("tel="+tel);
        if (username!=null){
            user.setUsername(username);
        }
        if (gender!=null){
            user.setGender(gender);

        }
        if (tel!=null){
            user.setTel(tel);
        }
        if (image!=null){
            user.setImage(image);
        }
        return user;
    }


}
