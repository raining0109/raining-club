package com.raining.oss.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.Bucket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AliyunUtil {

    @Resource
    private OSS ossClient;

    @Value("${aliyun.bucket}")
    private String bucketName;

    public void create(String bucketName) {
//        boolean exists = ossClient.doesBucketExist(bucketName);
//        if (!exists) { //判定是否存在此存储空间
//            // 创建存储空间。
//            ossClient.createBucket(bucketName);
//        }
    }


    public void uploadFile(InputStream inputStream, String bucket, String objectName) {
        ossClient.putObject(bucketName, objectName, inputStream);
    }

    /**
     * 列出所有桶
     */
    public List<String> getAllBucket() {
        List<Bucket> buckets = ossClient.listBuckets();
        return buckets.stream().map(Bucket::getName).collect(Collectors.toList());
    }
}
