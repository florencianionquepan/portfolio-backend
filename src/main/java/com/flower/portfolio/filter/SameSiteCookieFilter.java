package com.flower.portfolio.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
public class SameSiteCookieFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        filterChain.doFilter(request, response);

        Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
        boolean firstHeader = true;

        for (String header : headers) {
            String newHeader = header;

            if (header.contains("JSESSIONID")) {
                if (!header.toLowerCase().contains("samesite")) {
                    newHeader += "; SameSite=None";
                }
                if (!header.toLowerCase().contains("secure")) {
                    newHeader += "; Secure";
                }
            }

            if (firstHeader) {
                response.setHeader(HttpHeaders.SET_COOKIE, newHeader);
                firstHeader = false;
            } else {
                response.addHeader(HttpHeaders.SET_COOKIE, newHeader);
            }
        }
    }
}
