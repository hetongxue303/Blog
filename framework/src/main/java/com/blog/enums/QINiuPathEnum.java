package com.blog.enums;

import com.blog.constants.SystemConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 何同学
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum QINiuPathEnum {

    AVATAR(SystemConstant.UPLOAD_FILE_ROOT_PATH + "/avatar", "头像路径"),

    ARTICLE(SystemConstant.UPLOAD_FILE_ROOT_PATH + "articles/", "文章图片路径"),

    VOICE(SystemConstant.UPLOAD_FILE_ROOT_PATH + "voice/", "音频路径"),

    PHOTO(SystemConstant.UPLOAD_FILE_ROOT_PATH + "photos/", "相册路径"),

    CONFIG(SystemConstant.UPLOAD_FILE_ROOT_PATH + "config/", "配置图片路径"),

    TALK(SystemConstant.UPLOAD_FILE_ROOT_PATH + "talks/", "配置图片路径"),

    MD(SystemConstant.UPLOAD_FILE_ROOT_PATH + "markdown/", "md文件路径");

    private final String path;
    private final String desc;

}