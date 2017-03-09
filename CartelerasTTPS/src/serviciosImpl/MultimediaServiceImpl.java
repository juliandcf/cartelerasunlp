package serviciosImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.MultimediaDAO;
import dto.CarteleraVO;
import dto.GenericDTO;
import modelo.Multimedia;
import serviciosInt.MultimediaService;

@Transactional
@Service
public class MultimediaServiceImpl extends GenericServiceImpl<Multimedia,MultimediaDAO>  implements MultimediaService{

	
	@Override
	public List<Multimedia> recuperarTodos(Serializable idPublicacion) {
		return this.getDao().recuperarMultimediaDePublicacion(idPublicacion);
	}

}
