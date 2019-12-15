package br.com.reclameaqui.complainsapi.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

import static br.com.reclameaqui.complainsapi.util.SecurityUtil.obterTokenUsuarioLogado;

@Component
public class JwtHelper {

    private final AppProperties appProperties;

    public JwtHelper(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String retrieveStringValueOfToken(String chave) {
        return (String) getClaims(obterTokenUsuarioLogado()).get(chave);
    }

    public Integer retrieveIntegerValueOfToken(String chave) {
        return (Integer) getClaims(obterTokenUsuarioLogado()).get(chave);
    }

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(getSecretKeyBytes());
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public byte[] getSecretKeyBytes() {
        String segredo = appProperties.getSeguranca().getAutenticacao().getJwt().getSegredo();
        return !StringUtils.isEmpty(segredo) ? getSecretBytes(segredo) : getBytesBase64();
    }

    private byte[] getSecretBytes(String secret) {
        return secret.getBytes(StandardCharsets.UTF_8);
    }

    private byte[] getBytesBase64() {
        return Decoders.BASE64.decode(appProperties.getSeguranca().getAutenticacao().getJwt().getSegredoBase64());
    }
}
