package serviciosImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AdministradorDAO;
import dao.CarteleraDAO;
import modelo.Administrador;
import modelo.Cartelera;
import modelo.Publicacion;
import serviciosInt.CarteleraService;

@Transactional
@Service
public class CarteleraServiceImpl extends GenericServiceImpl<Cartelera,CarteleraDAO>  implements CarteleraService{
	
	
	
	@Override
	public Cartelera alta(Cartelera cartelera) {
		Cartelera carteleraReturn = null;	
		if (!this.existe(cartelera)){
			carteleraReturn = this.getDao().persistir(cartelera);
		}
		return carteleraReturn;
	}


	
//	@Override
//	public boolean existe(Cartelera cartelera) {
//		return this.getDao().existe(cartelera);
//	}

}
