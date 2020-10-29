package com.zimug.courses.security.basic.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zimug.commons.exception.AjaxResponse;
import com.zimug.commons.exception.CustomException;
import com.zimug.commons.exception.CustomExceptionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
public class MyFaireHandler extends SimpleUrlAuthenticationFailureHandler {


    @Value("${spring.security.loginType}")
    private String loginType;

    //Jackson JSON数据处理类
    private  static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if(loginType!=null&&loginType.equalsIgnoreCase("json")){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write( objectMapper.writeValueAsString(
                    AjaxResponse.error(
                            new CustomException(
                                    CustomExceptionType.USER_INPUT_ERROR,
                                    "用户名或密码存在错误，请检查后再次登录"))));
        }else{
            super.onAuthenticationFailure(request,response,exception);
        }
    }


}
