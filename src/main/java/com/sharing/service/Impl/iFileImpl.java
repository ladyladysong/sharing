package com.sharing.service.Impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sharing.common.ServerResponse;
import com.sharing.service.iFileService;
import com.sharing.util.FTPUtil;
import com.sharing.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service("iFileService")
public class iFileImpl implements iFileService {
    @Override
    public ServerResponse uploadFile(MultipartFile file, String path) {
        String fileName=file.getOriginalFilename();
        String fileExtensionName= fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName= UUID.randomUUID().toString()+"."+fileExtensionName;
        log.info("begin to upload:{}，upload to：{}，rename：{}",fileName,path,uploadFileName);

        File fileDir=new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile=new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            FTPUtil.upload(Lists.newArrayList(targetFile));
            targetFile.delete();

        } catch (IOException e) {
            log.error("upload exception",e);
            return null;
        }
        Map fileMap= Maps.newHashMap();
        String url= PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFile.getName();
        fileMap.put("url",url);
        return ServerResponse.createBySuccess(fileMap);
    }
}
