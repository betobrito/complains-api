package br.com.reclameaqui.complainsapi.config.jwt;

import br.com.reclameaqui.complainsapi.shared.exception.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.reclameaqui.complainsapi.shared.Constantes.Jwt.KEY_ROLES;
import static br.com.reclameaqui.complainsapi.shared.Constantes.Jwt.KEY_USER;

@Component
public class TokenProvider {

    private final JwtHelper jwtHelper;

    public TokenProvider(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    public boolean validarToken(String token) {
        try {
            jwtHelper.getClaims(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new TokenException("Assinatura do JWT inválida.");
        } catch (ExpiredJwtException e) {
            throw new TokenException("Token expirado");
        } catch (UnsupportedJwtException e) {
            throw new TokenException("Token não suportado.");
        } catch (IllegalArgumentException e) {
            throw new TokenException("Compactação do token inválida.");
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = jwtHelper.getClaims(token);
        Collection<? extends GrantedAuthority> authorities = obterPermissoesToken(claims);
        User principal = new User(claims.get(KEY_USER).toString(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    private List<SimpleGrantedAuthority> obterPermissoesToken(Claims claims) {
        return Arrays.stream(claims.get(KEY_ROLES).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
