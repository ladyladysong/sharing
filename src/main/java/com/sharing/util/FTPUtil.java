package com.sharing.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import sun.net.ftp.FtpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class FTPUtil {
    public static String getFtpIp() {
        return ftpIp;
    }

    public static void setFtpIp(String ftpIp) {
        FTPUtil.ftpIp = ftpIp;
    }

    public static String getFtpUser() {
        return ftpUser;
    }

    public static void setFtpUser(String ftpUser) {
        FTPUtil.ftpUser = ftpUser;
    }

    public static String getFtpPassword() {
        return ftpPassword;
    }

    public static void setFtpPassword(String ftpPassword) {
        FTPUtil.ftpPassword = ftpPassword;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    private static String ftpIp=PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser=PropertiesUtil.getProperty("ftp.user");
    private static String ftpPassword=PropertiesUtil.getProperty("ftp.pass");

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public FTPUtil(String ip, int port,String user,String pwd){
        this.ip=ip;
        this.port=port;
        this.user=user;
        this.pwd=pwd;
    }

    public static boolean upload(List<File> fileList) throws IOException{
        FTPUtil ftpUtil=new FTPUtil(ftpIp,21,ftpUser,ftpPassword);
        log.info("begin to connect ftp server");
        boolean result=ftpUtil.upload("img",fileList);
        log.info("upload result: {}",result);
        return result;
    }

    private boolean upload(String remotePath, List<File> fileList) throws IOException {
        boolean uploaded=true;
        FileInputStream fis=null;
        if(connectServer(this.ip,this.port,this.user,this.pwd)){
            try{
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for (File file:fileList){
                    fis=new FileInputStream(file);
                    ftpClient.storeFile(file.getName(),fis);
                }
            }
            catch (IOException e){
                log.error("fail to upload file");
                uploaded=false;
            }
            finally {
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }

    private boolean connectServer(String ip, int port, String user,String pwd){
        boolean isSuccess=true;
        ftpClient=new FTPClient();
        try{
            ftpClient.connect(ip,port);
            isSuccess=ftpClient.login(user,pwd);
        }
        catch (IOException e){
            log.info("fail to connect ftp server");
        }
        return isSuccess;
    }
}
