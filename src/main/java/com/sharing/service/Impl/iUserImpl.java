package com.sharing.service.Impl;
import com.sharing.common.ServerResponse;
import com.sharing.dao.UserMapper;
import com.sharing.service.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iUserService")
public class iUserImpl implements iUserService {
    @Autowired
    private UserMapper userMapper;
    public ServerResponse login(String email, String passwd){
        return null;
    }
}
