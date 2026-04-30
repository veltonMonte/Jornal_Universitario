package com.jornal.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        // 1. Obtém o caminho da requisição
        String path = request.getRequestURI();

        // 2. EXCEÇÃO: Se for uma rota de registro, ignore a validação de token e siga em frente
        // Verifique se o seu prefixo é realmente /api/...
        if (path.equals("/api/universidades") || path.equals("/api/usuarios/registro")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Obtém o cabeçalho de autorização
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                if (jwtUtil.validar(token)) {
                    String email = jwtUtil.extrairEmail(token);
                    String role  = jwtUtil.extrairRole(token);

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    email, null,
                                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
                            );

                    // Define a autenticação no contexto do Spring Security
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                // Em caso de erro no token (expirado ou malformado), limpa o contexto por segurança
                SecurityContextHolder.clearContext();
            }
        }

        // 4. Continua a execução da cadeia de filtros
        filterChain.doFilter(request, response);
    }
}