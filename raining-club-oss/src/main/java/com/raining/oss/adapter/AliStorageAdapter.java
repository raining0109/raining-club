package com.raining.oss.adapter;

import com.raining.oss.config.AliyunConfig;
import com.raining.oss.entity.FileInfo;
import com.raining.oss.util.AliyunUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * 阿里云oss适配器
 *
 * @author: raining
 * @date: 2023/10/14
 */
public class AliStorageAdapter implements StorageAdapter {

    @Resource
    private AliyunUtil aliyunUtil;

    @Value("${aliyun.endpoint}")
    private String endpoint;

    @Value("${aliyun.bucket}")
    private String bucketName;

    @Override
    public void createBucket(String bucket) {
        aliyunUtil.create(bucket);
    }

    @Override
    public void uploadFile(MultipartFile uploadFile, String bucket, String objectName) throws IOException {
//        aliyunUtil.create(bucket);
        aliyunUtil.uploadFile(uploadFile.getInputStream(), bucket, objectName);
    }

    @Override
    public List<String> getAllBucket() {
        return aliyunUtil.getAllBucket();
    }

    @Override
    public List<FileInfo> getAllFile(String bucket) {
        return null;
    }

    @Override
    public InputStream downLoad(String bucket, String objectName) {
        return null;
    }

    @Override
    public void deleteBucket(String bucket) {

    }

    @Override
    public void deleteObject(String bucket, String objectName) {

    }

    @Override
    public String getUrl(String bucket, String objectName) {
        return "https://" + bucketName + "." + endpoint.substring(8) +
                "/" + objectName;
    }

}
