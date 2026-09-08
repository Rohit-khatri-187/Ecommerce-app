package com.rohit.ecommerce_backend.security;

import com.rohit.ecommerce_backend.utils.ExceptionResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);

        if(token == null){
            filterChain.doFilter(request,response);
            return;
        }

        try {
            String email = jwtUtils.getUsernameFromToken(token);

            if(!StringUtils.hasText(email)){
                sendErrorResponse(
                        response,
                        HttpStatus.UNAUTHORIZED,
                        "Invalid jwt token"
                );
                return;
            }

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            if(!jwtUtils.isTokenValid(token,userDetails)){
                sendErrorResponse(
                        response,
                        HttpStatus.UNAUTHORIZED,
                        "Invalid or expired Jwt token"
                );
                return;
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request,response);

        } catch (Exception e) {
            sendErrorResponse(
                    response,
                    HttpStatus.UNAUTHORIZED,
                    "Invalid or expired JWT token"
            );
        }



    }

    private String getTokenFromRequest(HttpServletRequest request){
        String tokenWithBearer = request.getHeader("Authorization");

        log.info("Authorization Header : " + tokenWithBearer );

        if(tokenWithBearer != null && tokenWithBearer.startsWith("Bearer ") ){
            return tokenWithBearer.substring(7);
        }
        return null;
    }

    private void sendErrorResponse(
            HttpServletResponse response,
            HttpStatus status,
            String message
    ) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ExceptionResponse errorResponse = ExceptionResponse.builder()
                .status(status.value())
                .message(message)
                .success(false)
                .build();
        response.getWriter().write(
                objectMapper.writeValueAsString(errorResponse)
        );

    }

}
