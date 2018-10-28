package com.petronas.fetchtechexam.filtes;

import com.petronas.fetchtechexam.model.AccountCredentials;
import com.petronas.fetchtechexam.service.TokenAuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by hungtv on 08/10/2018.
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        AccountCredentials credentials = new AccountCredentials(request.getParameter("username"), request.getParameter("password"),request.getParameter("userid"),request.getParameter("companyid"));
        // Create an encoder with strength 16
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
//        String result = encoder.encode(credentials.getPassword());
        //assertTrue(encoder.matches("myPassword", result));\
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword(),
                Collections.emptyList()
        );
        Authentication authentication = getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);

        return authentication;
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        System.out.println("Failed JWT");
        if(this.logger.isDebugEnabled()) {
            this.logger.debug("Authentication request failed: " + failed.toString(), failed);
            this.logger.debug("Updated SecurityContextHolder to contain null Authentication");
            this.logger.debug("Delegating to authentication failure handler ");
        }
        TokenAuthenticationService.addAuthenticationFailed(response, request.getPathInfo());
//        this.rememberMeServices.loginFail(request, response);
//        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        TokenAuthenticationService.addAuthentication(response, authResult.getName());
    }
}
