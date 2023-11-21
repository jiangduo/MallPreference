package com.totoro.mall.manager.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.totoro.mall.model.entity.system.SysUser;
import com.totoro.mall.model.vo.common.Result;
import com.totoro.mall.model.vo.common.ResultCodeEnum;
import com.totoro.mall.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class LoginAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override// preHandle 表示在方法之前执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1、获取请求方式
        //  如果是option预检请求，直接放行。这里是为了预先查看服务器是否连通，或者是否支持跨域，如果支持再来发送正式请求，通俗点提前彩排，
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }

        // 2、从请求头获取token
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response);
            return false;
        }

        // 3、如果token空，返回错误提示
        // 4、拿着token查询redis，
        String tempUserKey = "user:login:" + token;
        String userInfoString = redisTemplate.opsForValue().get(tempUserKey);
        // 5、redis查询不到，返回错误提示
        if (StrUtil.isEmpty(userInfoString)) {
            responseNoLoginInfo(response);
            return false;
        }
        // 6、如果redis查询成功，把用户信息放到threadLocal中
        AuthContextUtil.set(JSON.parseObject(userInfoString, SysUser.class));
        // 7、把redis用户信息数据更新过期时间
        redisTemplate.expire(tempUserKey, 30, TimeUnit.MINUTES);
        // 8、放行,这里直接return true就是方形
        return true;
    }

    @Override// 表示在方法之后执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //、把threadLocal数据删除掉
        // threadLocal中的数据是threadLocalMap里面的Entry,而entry的value是强引用，key是弱引用，GC每次回收都会回收弱引用
        AuthContextUtil.remove();
    }

    /**
     * 注意：这里如果判断没有登录，会返回给前端，208，因此需要前端检测到208之后直接跳转到登录页面
     * @param response
     */
    // 响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }
}
