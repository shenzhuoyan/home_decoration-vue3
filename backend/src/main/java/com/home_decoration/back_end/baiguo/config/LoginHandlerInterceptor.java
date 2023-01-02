package com.home_decoration.back_end.baiguo.config;

import com.auth0.jwt.interfaces.Claim;
import com.home_decoration.back_end.baiguo.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        } else {
            final String token = request.getHeader("token");
            if (token == null)
                return false;
            Map<String, Claim> userData = JwtUtil.verifyToken(token);
//            if (userData != null) {
//                int userId = userData.get("userId").asInt();
//                //校验token是否正确
//                //往请求里加用户的id，以保证用户是本人
//                request.setAttribute("userId", userId);
//                return true;
//            }
            return userData != null;
        }
        //指定相应的字符集：解决响应的乱码问题。这里的代码是直接作为网页显示到前端
    }
}
