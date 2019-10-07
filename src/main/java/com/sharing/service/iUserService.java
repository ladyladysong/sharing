package com.sharing.service;

import com.sharing.common.ServerResponse;


public interface iUserService {
    ServerResponse login(String email, String password);
    ServerResponse register(String email, String password);
}
