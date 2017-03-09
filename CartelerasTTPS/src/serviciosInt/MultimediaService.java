package serviciosInt;

import java.io.Serializable;
import java.util.List;

import modelo.Multimedia;

public interface MultimediaService extends GenericService<Multimedia>{

	public List<Multimedia> recuperarTodos(Serializable idPublicacion);
}
