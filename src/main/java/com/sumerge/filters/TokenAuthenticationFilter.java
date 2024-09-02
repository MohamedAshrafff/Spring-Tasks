package com.sumerge.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter implements Filter {
     private static final String TOKEN_VALUE = "Admin";


        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            String headerToken = httpRequest.getHeader("auth-admin");
            if (headerToken != null && headerToken.equals(TOKEN_VALUE)) {
                chain.doFilter(request, response);
                return;
            }

            Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("token".equals(cookie.getName()) && TOKEN_VALUE.equals(cookie.getValue())) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }

            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
}
