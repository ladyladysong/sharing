package com.sharing.service;

import com.sharing.common.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

public interface iFileService {
    public ServerResponse uploadFile(MultipartFile file, String path);
}
