package com.sharing.service;

import com.sharing.common.ServerResponse;

public interface iRatingService {
    ServerResponse rateUser(Integer uid,Integer oid, double rate);
}
