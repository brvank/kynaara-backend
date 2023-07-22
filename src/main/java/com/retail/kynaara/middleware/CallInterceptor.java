package com.retail.kynaara.middleware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.kynaara.utility.AppMessages;
import com.retail.kynaara.utility.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CallInterceptor implements HandlerInterceptor {

    @Autowired
    public TokenUtil tokenUtil;

    @Autowired
    public AppMessages.Error errorMessages;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURI());
        if(request.getRequestURI().contains("api/v1/") && !request.getRequestURI().contains("api/v1/auth") && !request.getRequestURI().contains("api/v1/redirect")){
            System.out.println("request header");
            System.out.println(request.getHeader("Authorization"));
            TokenUtil.TokenValidity tokenValidity = tokenUtil.validateToken(request.getHeader("Authorization"));

            if(tokenValidity == TokenUtil.TokenValidity.VALID){
                //TODO: check for user exists or not
                return true;
            }else{
                ObjectMapper objectMapper = new ObjectMapper();

                Message message = new Message(errorMessages.notAuthorized, false);
                response.setContentType("application/json");
                response.setStatus(401);
                response.getWriter().write(objectMapper.writeValueAsString(message));

                return false;
            }
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
