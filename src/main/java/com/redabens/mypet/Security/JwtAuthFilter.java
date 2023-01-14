package com.redabens.mypet.Security;

import com.redabens.mypet.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


    @Component
//@RequiredArgsConstructor
    public class JwtAuthFilter extends OncePerRequestFilter {

        @Autowired
        private UserDetailsService userDetailsService;
        @Autowired
        private JwtUtils jwtUtils;

//    @Autowired
//    public JwtAuthFilter(UserDetailsService userDetailsService, JwtUtils jwtUtils) {
//        this.userDetailsService = userDetailsService;
//        this.jwtUtils = jwtUtils;
//    }

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {
            System.out.println("ur in doFilterInternal now");

            final String authHeader = request.getHeader("Authorization");
            final String userEmail;
            final String jwtToken;

            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request,response);
                return;
            }
            jwtToken = authHeader.substring(7);
            userEmail = jwtUtils.extractUsername(jwtToken);
            System.out.println("jwtToken : " + jwtToken);
            System.out.println("email : " + userEmail);

            if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                System.out.println("userDetails from doFilterInternal : ");
                System.out.println(userDetails);

//            UserDetails userDetails = (UserDetails) userService.find_user_by_email(userEmail);

                if(jwtUtils.isTokenValid(jwtToken, userDetails))
                {
                    final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request,response);
        }
    }

