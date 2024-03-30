package org.software.productservice.common;

import org.software.productservice.entity.User;
import org.software.productservice.service.UserService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String servletPath = request.getServletPath();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/page/end/login.html");
            return false;
        }
//        List<Permission> permissions = userService.getPermissions(user.getId());
//        if (permissions.stream().noneMatch(p -> servletPath.contains(p.getFlag())) && !servletPath.contains("index")) {
//            response.sendRedirect("/page/end/auth.html");
//            return false;
//        }
        return true;
    }

}
