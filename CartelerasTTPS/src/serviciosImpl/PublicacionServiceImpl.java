package serviciosImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CarteleraDAO;
import dao.PublicacionDAO;
import dao.UsuarioPublicadorDAO;
import modelo.Cartelera;
import modelo.Publicacion;
import serviciosInt.PublicacionService;

@Transactional
@Service
public class PublicacionServiceImpl implements PublicacionService {
	
	@Autowired
	private PublicacionDAO publicacionDAO;
	
	@Override
	public Publicacion alta(Publicacion publicacion) {
		return this.getPublicacionDAO().persistir(publicacion);
	}

	@Override
	public Publicacion modificar(Publicacion publicacion) {
		return this.getPublicacionDAO().actualizar(publicacion);
	}

	@Override
	public boolean baja(Publicacion publicacion) {
		//Ver si hay que borrar los comentarios tambien.
		return this.getPublicacionDAO().borrar(publicacion);
	}

	@Override
	public Publicacion recuperar(Serializable id) {
		return this.getPublicacionDAO().recuperar(id);
	}

	@Override
	public List<Publicacion> recuperarTodos() {
		return this.getPublicacionDAO().recuperarTodos();
	}

	@Override
	public boolean existe(Publicacion publicacion) {
		return this.getPublicacionDAO().existe(publicacion);
		
	}

	public PublicacionDAO getPublicacionDAO() {
		return publicacionDAO;
	}

	public void setPublicacionDAO(PublicacionDAO publicacionDAO) {
		this.publicacionDAO = publicacionDAO;
	}
}
