package jwt;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UsuarioVO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * Servlet Filter implementation class TokenSecurityFilter
 */
public class TokenSecurityFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TokenSecurityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//System.out.println("Estoy en el filtro");
		HttpServletRequest req = (HttpServletRequest) request;
		String[] splitPath = req.getRequestURI().split("/");
		
		String path = "";
		
		if(splitPath.length > 0) {
			path = splitPath[splitPath.length -1];
		}
        
		System.out.println(path);
		if ("login".equals(path)) {
			// sigue la cadena de ejecucion hacia el login
			chain.doFilter(req, response);
		}else{
			//Debo verificar la validez del token.
			chain.doFilter(req, response);
		}
		
		
		/*HttpServletRequest req = (HttpServletRequest) request;
		String[] splitPath = req.getRequestURI().split("/");
		
		String path = "";
		
		if(splitPath.length > 0) {
			path = splitPath[splitPath.length -1];
		}

		if ("login".equals(path)) {
			// sigue la cadena de ejecucion hacia el login
			chain.doFilter(req, response);
		} else {
			// valido token
			String jwt = req.getHeader("Authorization");
			try {
				// Si la validacion es correcta y el token no expiro, parsea el
				// contenido del token y devuelve el user
				UsuarioVO user = this.getTokenManagerSecurity().parseJWT(jwt);

				// Seteo el user en un atributo nuevo, de esta forma
				// ya estaria disponible para el resto de los controllers
				request.setAttribute("user", user);

				chain.doFilter(req, response);

			} catch (ExpiredJwtException e) {
				System.out.println(
						"El token ya no es valido, expiro su tiempo de validez: " + jwt + " " + e.getMessage());

				// ejecuta el logout para invalidar la session en banca
				if (e.getClaims().containsKey("content")) {
					UsuarioVO user = this.getTokenManagerSecurity().getContentJWT(e.getClaims().get("content").toString());
					System.out.println(
							"Expiro el tiempo de ejecucion del token, por lo que se invalida la session del usuario (logout) "
									+ e.getMessage());
					
				}
				((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			} catch (SignatureException e) {
				System.out.println("No es un token valido, token: " + jwt + " " + e.getMessage());
				((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			} catch (Exception e) {
				System.out.println("No es un token valido, token: " + jwt + " " + e.getMessage());
				((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}

		}*/
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	private TokenManagerSecurity getTokenManagerSecurity(){
		return new TokenManagerSecurity();
	}

}
