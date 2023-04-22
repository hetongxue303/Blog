package com.blog.service;

import com.blog.domain.dto.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件业务
 *
 * @author hy
 * @version 1.0
 */
public interface FileService {

    Result uploadImage(MultipartFile file);

}