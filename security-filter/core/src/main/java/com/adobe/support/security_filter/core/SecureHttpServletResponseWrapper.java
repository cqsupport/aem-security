package com.adobe.support.security_filter.core;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class SecureHttpServletResponseWrapper extends HttpServletResponseWrapper implements HttpServletResponse {

	public SecureHttpServletResponseWrapper(HttpServletResponse response) {
		super(response);
	}

    public void addCookie(Cookie cookie) {
        String name = cookie.getName();
        String value = cookie.getValue();
        int maxAge = cookie.getMaxAge();
        String domain = cookie.getDomain();
        String path = cookie.getPath();
        boolean secure = cookie.getSecure();

        String cookieHeader = createCookieHeader(name, value, maxAge, domain, path, secure);
        addHeader("Set-Cookie", cookieHeader);
    }

    private String createCookieHeader(String name, String value, int maxAge, String domain, String path, boolean secure) {
        String cookieHeader = name + "=" + value;
        if (maxAge >= 0) {
            cookieHeader += "; Max-Age=" + maxAge;
        }
        if (domain != null) {
            cookieHeader += "; Domain=" + domain;
        }
        if (path != null) {
            cookieHeader += "; Path=" + path;
        }
        // Force Secure and HttpOnly
        cookieHeader += "; Secure";
		cookieHeader += "; HttpOnly";
        return cookieHeader;
    }
}
