package com.sami.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {

		String url = "/login?error=true";
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		if (roles.contains("ROLE_ADMIN") && roles.contains("Examination")) {
			url = "/examination/index";
		} else if (roles.contains("ROLE_ADMIN") && roles.contains("Library")) {
			url = "/Library/index";
		} else if (roles.contains("ROLE_ADMIN") && roles.contains("Transport")) {
			url = "/Transport/index";
		} else if (roles.contains("ROLE_ADMIN") && roles.contains("Inventory")) {
			url = "/Inventory/index";
		}else if (roles.contains("ROLE_ADMIN") && roles.contains("Admission")) {
			url = "/Admission/index";
		}else if (roles.contains("ROLE_ADMIN") && roles.contains("Accounting")) {
			url = "/Accounting/index";
		}
		return url;
	}

}
