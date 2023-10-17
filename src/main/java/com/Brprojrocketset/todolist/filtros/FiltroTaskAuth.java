package com.Brprojrocketset.todolist.filtros;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Brprojrocketset.todolist.user.IUserRepositorio;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FiltroTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepositorio iuserRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();
                System.out.println("PAATH" +servletPath);
        if (servletPath.startsWith("/tasks/")) {

            // Pegar a autenticação(usuario e senha)
            var authorization = request.getHeader("Authorization");

            var authEncoded = authorization.substring("Basic".length()).trim();

            byte[] authDeconder = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDeconder);

            String[] credentials = authString.split(":");
            String Username = credentials[0];
            String senha = credentials[1];

            // Validar Usuario
            var user = this.iuserRepositorio.findByUsername(Username);
            if (user == null) {
                response.sendError(401, "Erro ao acessar, com esse ser");
            } else {

                // Validar senha
                var passwordVerify = BCrypt.verifyer().verify(senha.toCharArray(), user.getSenha());
                if (passwordVerify.verified) {
                    // Tudo certo(Segue viagem)
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    
                    response.sendError(401, "Erro ao acessar c/ senha");
                }

            }
        }else{
            // Tudo certo(Segue viagem)
            filterChain.doFilter(request, response);
        }

    }

}
