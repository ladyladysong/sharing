package com.sharing.service.Impl;
import com.sharing.common.ServerResponse;
import com.sharing.dao.UserMapper;
import com.sharing.pojo.User;
import com.sharing.service.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("iUserService")
public class iUserImpl implements iUserService {
    @Autowired
    private UserMapper userMapper;

    public ServerResponse login(String email,String password){
        User user = this.userMapper.isValidUser(email,password);
        if (user!=null){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("Cannot find this user");

    }

    @Transactional
    public ServerResponse register(String email,String password){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(email);
        userMapper.addUser(user);
        if (this.userMapper.isUserExist(email)!=null){
            return ServerResponse.createBySuccessMessage("successfully create");

        }
        return ServerResponse.createByErrorMessage("Not successfully register");

    }

//    public ServerResponse get_user_info(){
//
//    }
//

}
