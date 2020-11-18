package com.myhome.demo.config;

import com.myhome.demo.domain.Node;
import com.myhome.demo.domain.Roler;
import com.myhome.demo.domain.User;
import com.myhome.demo.service.NodeServiceImpl;
import com.myhome.demo.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    NodeServiceImpl nodeService;
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("username");
        System.out.println("url: " + request.getRequestURL());
        //查询数据库
        System.out.println(user.getUser()+" "+user.getRole_id());
        List<Roler> rolerList = roleService.searchJdbcRoler(user.getRole_id());
        if (rolerList.size() == 0) {
            // 返回到登录成功页面
            response.sendRedirect("/toLoginPage");
            return false;
        } else {
            String curURL = request.getRequestURL().toString();
            for(Roler roler: rolerList){
                int node_id = roler.getNode_id();
                List<Node> nodeList = nodeService.searchJdbcNode(node_id);
                for(Node n: nodeList){
                    System.out.println(n.getUrl());
                    if(n.getUrl().equals(curURL))
                        return true;
                    System.out.println(n.getUrl());
                }
            }
            response.sendRedirect("/toLoginPage");
            return false;
        }
    }
}
