package com.sharing.service.Impl;
import com.sharing.common.ServerResponse;
import com.sharing.dao.UserMapper;
import com.sharing.pojo.User;
import com.sharing.service.iUserService;
import com.sharing.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            return ServerResponse.createByErrorMessage("wrong password");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
 

    @Transactional
    public ServerResponse register(String email,String password){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(email);
        if (userMapper.isUserExist(email)==null){
            try{
                //userMapper.insertSelective(user);
                userMapper.addUser(user);
            }catch (Exception e){
                log.info(e.getMessage());
            }
            if (this.userMapper.isUserExist(email)!=null){
                return ServerResponse.createBySuccessMessage("successfully create");

            }
            return ServerResponse.createByErrorMessage("Not successfully register");
        }
        return ServerResponse.createByErrorMessage("This email is already registered!");

    }


    public ServerResponse get_user_info(String id){
        //TODO: the meaning of id
        User record = userMapper.selectByPrimaryKey(Integer.parseInt(id));
        if (record!=null){
            return ServerResponse.createBySuccess("retrieve record successfully",record);
        }
        return ServerResponse.createByErrorMessage("No such user record!");
    }

    //@Transactional
    public ServerResponse register(String email,String password){
        User user = new User();
        user.setEmail(email);
        String md5Pwd = MD5Util.MD5EncodeUtf8(password);
        user.setPassword(md5Pwd);
        user.setUsername(email);
        if (userMapper.isUserExist(email)==null){
            try{
                //userMapper.insertSelective(user);
                userMapper.addUser(user);
            }catch (Exception e){
                log.info(e.getMessage());
            }
            if (this.userMapper.isUserExist(email)!=null){
                return ServerResponse.createBySuccessMessage("successfully create");

            }
            return ServerResponse.createByErrorMessage("Not successfully register");
        }
        return ServerResponse.createByErrorMessage("This email is already registered!");

    }


    public ServerResponse get_user_info(String id){
        //TODO: the meaning of id
        User record = userMapper.selectByPrimaryKey(Integer.parseInt(id));
        if (record!=null){
            return ServerResponse.createBySuccess("retrieve record successfully",record);
        }
        return ServerResponse.createByErrorMessage("No such user record!");
    }


    public ServerResponse update_info(User update, String id){
        User user = userMapper.selectByPrimaryKey(Integer.parseInt(id));
        if (user!=null){
            if (update.getGender()!=null){
                user.setGender(update.getGender());
            }
            if (update.getTel()!=null){
                user.setTel(update.getTel());
            }
            if (update.getUsername()!=null){
                user.setUsername(update.getUsername());
            }
            int re = userMapper.updateByPrimaryKeySelective(user);
            if (re > 0){
                return ServerResponse.createBySuccessMessage("successfully update");
            }
            return ServerResponse.createByErrorMessage("cannot update");
        }
        return ServerResponse.createByErrorMessage("No such user");
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




}
