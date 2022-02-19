package backend.api.project.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import backend.api.project.common.service.JwtService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProjectInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("request url is {}", request.getRequestURI());
		boolean flag = false;
		
		if(request.getMethod().equals("OPTIONS")) {
			flag = true;
		} else {
			String token = request.getHeader("Authorization");
			
			if(token!=null && token.length()>0) {
				jwtService.checkValid(token);
				flag = true;
			}
		}
		return flag;
	}

}
