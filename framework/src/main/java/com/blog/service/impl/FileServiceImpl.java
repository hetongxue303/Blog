package com.blog.service.impl;

import com.blog.domain.dto.Result;
import com.blog.enums.QINiuPathEnum;
import com.blog.service.FileService;
import com.blog.utils.QINiuUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public Result uploadImage(MultipartFile file) {
        String path = QINiuUtil.uploadImg(file, QINiuPathEnum.MARKDOWN);
        return Result.success("上传成功", path);
    }
}