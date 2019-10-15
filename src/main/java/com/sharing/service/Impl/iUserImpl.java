package com.sharing.service.Impl;
import com.sharing.common.ServerResponse;
import com.sharing.dao.UserMapper;
import com.sharing.pojo.User;
import com.sharing.service.iUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("iUserService")
public class iUserImpl implements iUserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public ServerResponse login(String email,String password){
        try{
            User user = this.userMapper.isValidUser(email,password);
            if (user!=null){
                return ServerResponse.createBySuccess();
            }

        }catch (Exception e){
            log.info(e.getMessage());
        }
        return ServerResponse.createByErrorMessage("Cannot find this user");

    }

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




}
