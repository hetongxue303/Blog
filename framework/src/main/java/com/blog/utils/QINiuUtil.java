package com.blog.utils;

import com.blog.enums.QINiuPathEnum;
import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛云工具
 *
 * @author hy
 * @version 1.0
 */
public class QINiuUtil {
    private static final String accessKey = "CkF4TEkB27uKifghgc6TOBjERjlzqzas5K6TCUuj";
    private static final String secretKey = "UPp80Y9YGPGoU8GBiXZ8k4tBft0htyuaEYuOVTwe";
    private static final String bucket = "hetongxue-blog";
    private static final String endpoint = "http://rsj189dkg.bkt.clouddn.com/";
    private static final Configuration configuration = new Configuration(Region.huadongZheJiang2());

    /**
     * 上传文件
     *
     * @param file 文件
     */
    public static String uploadImg(MultipartFile file, QINiuPathEnum pathEnum) {
        try {
            configuration.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
            UploadManager uploadManager = new UploadManager(configuration);
            InputStream inputStream = file.getInputStream();
            String key = FileUtil.generateFilePath(file, "images/" + pathEnum.getPath(), null, null);
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            DefaultPutRet result = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return endpoint + result.key;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}