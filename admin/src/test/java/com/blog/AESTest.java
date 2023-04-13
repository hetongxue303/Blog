package com.blog;

import com.baomidou.mybatisplus.core.toolkit.AES;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author hy
 * @version 1.0
 */
@SpringBootTest
public class AESTest {

    @Test
    void test() {
        String s = AES.generateRandomKey();
        System.out.println("s = " + s);
        String url = AES.encrypt("jdbc:mysql://47.113.147.153:3306/blog?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&useSSL=false", s);
        String username = AES.encrypt("root", s);
        String password = AES.encrypt("123456", s);
        System.out.println("url = " + url);
        System.out.println("username = " + username);
        System.out.println("password = " + password);
    }

}