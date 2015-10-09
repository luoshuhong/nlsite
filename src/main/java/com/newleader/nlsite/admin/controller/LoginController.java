package com.newleader.nlsite.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newleader.nlsite.common.RequestUtils;
import com.newleader.nlsite.common.SessionUtils;

@Controller
@RequestMapping("/admin")
public class LoginController {
	private Log log = LogFactory.getLog("admin");
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		log.info("method=login,userName=" + username + ",password=*****" );
		
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return RequestUtils.failReturn("error");
		}
		if(username.equals("admin") && password.equals("newleader007")){
			SessionUtils.setSession(request, SessionUtils.LONIG_FLAG, "true");
			log.info("method=login,登陆成功" );
            return RequestUtils.successReturn("success");
		}
        log.info("method=login,用户名或密码错误" );
        return RequestUtils.failReturn("error");
	}
	
}
