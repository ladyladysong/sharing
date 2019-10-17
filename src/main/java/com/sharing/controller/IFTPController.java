package com.sharing.controller;

import com.sharing.common.ServerResponse;
import com.sharing.service.iFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/util")
public class IFTPController {

    @Autowired
    private iFileService iFileService;

    @RequestMapping("/upload.do")
    @ResponseBody
    public ServerResponse fileUpload(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file){
        String path=session.getServletContext().getRealPath("upload");
        ServerResponse response=iFileService.uploadFile(file,path);
        if(response==null){
            return ServerResponse.createByErrorMessage("upload failed");
        }
        return response;
    }
}
