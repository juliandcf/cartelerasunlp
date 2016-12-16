package serviciosInt;

import java.util.List;

import modelo.Publicacion;

public interface PublicacionService extends GenericService<Publicacion> {

	List<Publicacion> getPublicaciones(Long idCartelera);
}
