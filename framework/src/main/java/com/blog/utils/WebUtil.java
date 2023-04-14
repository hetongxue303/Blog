package com.blog.utils;

import com.blog.domain.dto.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * web工具类
 *
 * @author hy
 * @version 1.0
 */
public class WebUtil {

    public static void responseString(HttpServletResponse response, Integer status, String message) {
        try {
            response.setStatus(status);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(new ObjectMapper().writeValueAsString(Result.fail(status, message)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}