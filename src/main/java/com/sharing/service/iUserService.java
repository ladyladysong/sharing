package com.sharing.service;

import com.sharing.common.ServerResponse;
import com.sharing.pojo.User;

public interface iUserService {
    ServerResponse<User> login(String email, String passwd);
}
