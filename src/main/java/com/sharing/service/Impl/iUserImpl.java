package com.sharing.service.Impl;
import com.sharing.common.ServerResponse;
import com.sharing.dao.UserMapper;
import com.sharing.pojo.User;
import com.sharing.service.iUserService;
import com.sharing.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iUserService")
public class iUserImpl implements iUserService {
    @Autowired
    private UserMapper userMapper;

    public ServerResponse<User> login(String email, String passwd){
        int count=userMapper.checkEmail(email);
        if(count==0){
            return ServerResponse.createByErrorMessage("user not exists");
        }
        String md5Passwd= MD5Util.MD5EncodeUtf8(passwd);
        User user=userMapper.selectByEmail(email,passwd);
        if(user==null){
            return ServerResponse.createByErrorMessage("wrong password");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }
}
