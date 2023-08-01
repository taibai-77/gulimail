package com.study.gulimall.thirdparty;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThirdPartyApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    OSSClient ossClient;

    @Test
    public void testUpload() throws Exception {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        //String endpoint = "oss-cn-shenzhen.aliyuncs.com";
        //String accessKeyID = "LTAI5tEzcdBLLdDvABUb74UH";
        //String accessKeySecret = "1Vae2OI0JIWpKADciWIf6KACavOkCX";
        // 创建OSSClient实例。
        //OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyID, accessKeySecret);

        // Bucket名称
        String bucketName = "gulimall-knfb";
        // Object完整路径，完整路径中不能包含Bucket名称
        String objectName = "cropped-1920-1080-877538.jpg";

        // 上传文件流
        String filePath = "D:\\桌面文件\\saber\\cropped-1920-1080-877538.jpg";
        InputStream inputStream = new FileInputStream(filePath);
        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
        // 创建PutObject请求。
        PutObjectResult result = ossClient.putObject(putObjectRequest);

        // 打印信息
        System.out.println("文件上传成功......");
    }
}
