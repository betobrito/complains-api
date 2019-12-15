package br.com.reclameaqui.complainsapi.util;

import br.com.reclameaqui.complainsapi.shared.exception.ServiceException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtil {

    private SecurityUtil() {}

    public static String obterLoginUsuarioLogado() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> ((UserDetails) authentication.getPrincipal()).getUsername())
                .orElseThrow(() -> new ServiceException("Erro ao obter dados do usuário."));
    }

    public static String obterTokenUsuarioLogado() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getCredentials() instanceof String)
                .map(authentication -> (String) authentication.getCredentials())
                .orElseThrow(() -> new ServiceException("Erro ao obter token do usuário."));
    }
}
