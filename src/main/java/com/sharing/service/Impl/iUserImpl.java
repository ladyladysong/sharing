package com.sharing.service.Impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sharing.common.ServerResponse;
import com.sharing.dao.UserMapper;
import com.sharing.dao.UserTagMapper;
import com.sharing.pojo.User;
import com.sharing.pojo.UserTag;
import com.sharing.service.iUserService;
import com.sharing.util.DateTimeUtil;
import com.sharing.util.JsonUtil;
import com.sharing.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("iUserService")
public class iUserImpl implements iUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTagMapper userTagMapper;

    public ServerResponse<User> login(String email, String passwd){
        int count=userMapper.checkEmail(email);
        if(count==0){
            return ServerResponse.createByErrorMessage("user not exists");
        }
        String md5Passwd= MD5Util.MD5EncodeUtf8(passwd);
        User user=userMapper.selectByEmail(email,md5Passwd);
        if(user==null){
            return ServerResponse.createByErrorMessage("wrong password");
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

    public ServerResponse get_user_info(Integer id){
        User record = userMapper.selectByPrimaryKey(id);
        if (record==null){
            return ServerResponse.createByErrorMessage("No such user records!");
        }
        record.setPassword(StringUtils.EMPTY);

        String CreateTime = DateTimeUtil.dateToStr(record.getCreateTime());

        List<Integer> tags=userTagMapper.selectByUserId(id);

        Map map = Maps.newHashMap();
        map.put("User info",record);
        map.put("User tag",tags);
        map.put("Create time",CreateTime);
        return ServerResponse.createBySuccess("retrieve record successfully ",map);
    }


    public ServerResponse update_info( Integer id, User update,String tag){

        User user = userMapper.selectByPrimaryKey(id);
        //log.info("original user info : "+JsonUtil.obj2StringPretty(user));

        if (user==null){
            return ServerResponse.createByErrorMessage("No such user");
        }
        if (update.getUsername()!=null){
            user.setUsername(update.getUsername());
        }
        if (update.getGender()!=null){
            user.setGender(update.getGender());
        }
        if (update.getTel()!=null){
            user.setTel(update.getTel());
        }
        if (update.getImage()!=null){
            user.setImage(update.getImage());
        }

        //User update = parseUpdateInfo(user,info);
        int re = userMapper.updateByPrimaryKeySelective(user);

        if (re==0){
            return ServerResponse.createByErrorMessage("Cannot update normal information");
        }
        if (!update_tag(user,tag)){
            return ServerResponse.createByErrorMessage("Cannot update user tags");
        }
        return ServerResponse.createBySuccessMessage("Successfully update all information");

    }

    public ServerResponse update_password(Integer id, String pwd_){
        User user = userMapper.selectByPrimaryKey(id);
        if (user==null){
            ServerResponse.createByErrorMessage("No such user");
        }
        String md5pwd = MD5Util.MD5EncodeUtf8(pwd_);
        log.info("original pwd ="+pwd_+" md5: "+md5pwd);
        user.setPassword(md5pwd);
        int re = userMapper.updateByPrimaryKeySelective(user);
        if (re==0){
            return ServerResponse.createByErrorMessage("cannot update password");
        }

        return ServerResponse.createBySuccessMessage("successfully update password");

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
        String phone = info_list.getString("phone");
        String avatar = info_list.getString("avatar");

        if (username!=null){
            user.setUsername(username);
        }
        if (gender!=null){
            user.setGender(gender);

        }
        if (phone!=null){
            user.setTel(phone);
        }
        if (avatar!=null){
            user.setImage(avatar);
        }
        return user;
    }


    private boolean update_tag(User user,String tag){
        boolean status = true;
        if (tag==null){
            return true;
        }

        userTagMapper.deleteByUserId(user.getId());

        String[] Tags = tag.split(";");
//        List<String> new_tags = Arrays.asList(Tags);
        for (String s:Tags) {
            int t = Integer.parseInt(s);
            UserTag userTag=new UserTag();
            userTag.setUserProfileId(user.getId());
            userTag.setTag(t);
            int re = userTagMapper.insert(userTag);
            if (re==0){
                status=false;
            }
        }

        return status;
    }

}
