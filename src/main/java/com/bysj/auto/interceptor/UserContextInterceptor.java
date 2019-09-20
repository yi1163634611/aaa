package com.bysj.auto.interceptor;

import com.bysj.auto.bean.User;
import com.bysj.auto.service.UserService;
import com.bysj.auto.util.CookieUtil;
import com.bysj.auto.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.logging.Logger;

import static com.bysj.auto.common.Const.AUTH_COOKIE_TOKEN_NAME;
import static com.bysj.auto.common.Const.AUTH_SESSION_ATTRIBUTE_NAME;
import static com.bysj.auto.util.JWTUtil.verifyToken;

public class UserContextInterceptor implements HandlerInterceptor {

    Logger logger = Logger.getLogger(UserContextInterceptor.class.getName());

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        logger.info(" Interceptor! <<<<<<<<<========================");

        HttpSession session = request.getSession();
        if (null != session) {
            // 验证 session 内的 User 信息
            Object obj = session.getAttribute(AUTH_SESSION_ATTRIBUTE_NAME);
            if (null != obj) {
                if (obj instanceof User) {
                    User user = (User) obj;
                    UserThreadLocal.set(user);
                }
                return true;
            } else {
                //若 session 验证失败，验证 jwt
                // 从 cookies 忠获得 token
                String token = CookieUtil.getCookie(request, AUTH_COOKIE_TOKEN_NAME);
                if (token == null || token.isEmpty()) {
                    token = request.getParameter("token");
                    if (token == null || token.isEmpty()) {
                        logger.warning("请求未携带 token 已被拦截访问");
                        return false;
                    }
                }
                //验证token
                Map<String, String> res = verifyToken(token);
                if (res == null) {
                    UserThreadLocal.set(null);
                    logger.warning("请求 token 验证失败 已被拦截访问！");
                    return false;
                } else {
                    int userId = Integer.parseInt(res.get("id"));
                    User user = userService.getUserById(userId);
                    if (user == null) {
                        logger.warning(" token 指示的用户不存在 ， token验证失败 已被拦截访问！");
                        return false;
                    }
                    UserThreadLocal.set(user);
                    return true;
                }
            }
        }
        logger.warning(" Session 为空！");
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
