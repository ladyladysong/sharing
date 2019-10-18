package com.sharing.service;

import com.sharing.common.ServerResponse;
import com.sharing.pojo.User;

public interface iUserService {
    ServerResponse<User> login(String email, String passwd);

    ServerResponse register(String email, String password);
    ServerResponse get_user_info(String id);
    ServerResponse update_info(User user,String id);
    ServerResponse update_password(String id, String password);

}
