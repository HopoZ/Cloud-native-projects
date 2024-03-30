package org.software.productservice.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.software.productservice.entity.Log;
import org.software.productservice.entity.User;
import org.software.productservice.exception.CustomException;
import org.software.productservice.mapper.LogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class LogService extends ServiceImpl<LogMapper, Log> {

    @Resource
    private LogMapper logMapper;

    @Resource
    private HttpServletRequest request;

    public User getUser() {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("-1", "请登录");
        }
        return user;
    }

    /**
     * 日志记录
     * @param content
     */
    public void log(String content) {
        Log log = new Log();
        log.setUser(getUser().getUsername());
        log.setTime(DateUtil.formatDateTime(new Date()));
        log.setIp(getIpAddress());
        log.setContent(content);
        save(log);
    }


    /**
     * 描述：获取IP地址
     */
    public String getIpAddress() {

        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
