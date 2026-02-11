package ceu.dam.ad.users.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	    @Autowired
	    private JwtUtil jwtUtil;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	                                    FilterChain filterChain) throws ServletException, IOException {

	        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	        
	        // LOG 1: Ver si llega el header
	        System.out.println("Authorization Header: " + authHeader);

	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            String token = authHeader.substring(7);
	            
	            // LOG 2: Ver el token extraído
	            System.out.println("Token extraído: " + token);
	            
	            // LOG 3: Ver si es válido
	            boolean isValid = jwtUtil.isValid(token);
	            System.out.println("¿Token válido?: " + isValid);

	            if (isValid) {
	                String username = jwtUtil.getUsername(token);
	                System.out.println("Username: " + username);
	                
	                // Establecer el contexto de seguridad
	                UsernamePasswordAuthenticationToken authToken = 
	                    new UsernamePasswordAuthenticationToken(
	                        username, 
	                        null, 
	                        Collections.emptyList()
	                    );
	                
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	                
	                filterChain.doFilter(request, response);
	                return;
	            } else {
	                System.out.println("Token NO válido");
	            }
	        } else {
	            System.out.println("No hay header o no empieza con 'Bearer '");
	        }

	        response.setStatus(HttpStatus.UNAUTHORIZED.value());
	        response.setContentType("application/json");
	        response.getWriter().write("{\"error\": \"Token JWT inválido o inexistente\"}");
	    }

	    @Override
	    protected boolean shouldNotFilter(HttpServletRequest request) {
	        String url = request.getRequestURI();
	        return url.startsWith("/auth") ||
	               url.equals("/users/login") ||  // AÑADE ESTO
	               url.startsWith("/swagger") ||
	               url.startsWith("/api-docs") ||
	               url.startsWith("/v3/api-docs");
	    }
}
