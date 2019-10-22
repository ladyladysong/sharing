package com.sharing.service;

import com.sharing.common.ServerResponse;
import com.sharing.pojo.User;

import java.math.BigDecimal;

public interface iUserService {
    ServerResponse<User> login(String email, String passwd);
    ServerResponse register(String email, String password);
    ServerResponse register(String email, String password, BigDecimal latitute, BigDecimal longitude);

    ServerResponse get_user_info(Integer id);
    ServerResponse update_info(Integer id, User update, String tag);
    ServerResponse update_password(Integer id, String password);
    ServerResponse update_lo(Integer id,BigDecimal latitute, BigDecimal longitude);
}
