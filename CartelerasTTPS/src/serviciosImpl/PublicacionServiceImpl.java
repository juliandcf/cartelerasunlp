package serviciosImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.PublicacionDAO;
import modelo.Publicacion;
import serviciosInt.PublicacionService;

@Transactional
@Service
public class PublicacionServiceImpl extends GenericServiceImpl<Publicacion,PublicacionDAO>  implements PublicacionService {

	@Override
	public boolean existe(Publicacion entity) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Publicacion> getPublicaciones(Long idCartelera) {
		List<Publicacion> publicaciones = this.getDao().recuperarTodos();
		List<Publicacion> publicacionesRetornar = new ArrayList<>(); 
		for (Publicacion p : publicaciones) {
			if(p.getCartelera().getId() == idCartelera)
				publicacionesRetornar.add(p);
		}
		return publicacionesRetornar;
		
	}
	
}
