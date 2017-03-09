package jwt;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import dto.UsuarioVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class TokenManagerSecurity {
	
	public TokenManagerSecurity() {
		super();

	}
	
//	@Inject
//	private ObjectMapper mapper;
	
	
	public String createJWT(UsuarioVO usuarioVO) throws Exception {

		String subject = this.getMapper().writeValueAsString(usuarioVO);

		// The JWT signature algorithm we will be using to sign the token

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setIssuedAt(now).claim("content", subject).signWith(SignatureAlgorithm.HS512,
				this.getSecretKey());

		// if it has been specified, let's add the expiration
		if (getTtlSeg() >= 0) {
			long ttlMillis = getTtlSeg() * 1000;
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();

	}

	private Long getTtlSeg() {
		//Esto deberia estar en otro lugar
		return Long.valueOf(1 * 60 * 60 * 10);
	}
	
	public UsuarioVO parseJWT(String jwt) {

		Claims claims = Jwts.parser().setSigningKey(this.getSecretKey()).parseClaimsJws(jwt).getBody();

		return this.getContentJWT(claims.get("content").toString());
	}
	
	public UsuarioVO getContentJWT(String contentJson) {

		try {
			return this.getMapper().readValue(contentJson, UsuarioVO.class);
		} catch (Exception e) {
			System.out.println("Error intentando parsear el payload del token: " + contentJson + e.getMessage());
			throw new IllegalStateException("Error de parseo. El payload del token no puede parsearse");
		}

	}
	
	private String getSecretKey(){
		//Esto deberia estar en otro lugar y que se injecte
		return "&%$#!GINASIA";
	}
	
	private ObjectMapper getMapper() {
		return new ObjectMapper();
	}

}
