package com.blog.utils;

import com.blog.domain.dto.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.blog.constants.CommonConstant.APPLICATION_JSON;

/**
 * web工具类
 *
 * @author hy
 * @version 1.0
 */
public class WebUtil {

    public static void render(HttpServletResponse response, Integer status, String message) {
        try {
            response.setStatus(status);
            response.setContentType(APPLICATION_JSON);
            response.getWriter().println(new ObjectMapper().writeValueAsString(Result.fail(status, message)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void render(HttpServletResponse response, Result result) throws Exception {
        try {
            response.setContentType(APPLICATION_JSON);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().println(new ObjectMapper().writeValueAsString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}