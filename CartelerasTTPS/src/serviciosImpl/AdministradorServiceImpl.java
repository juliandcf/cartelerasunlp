package serviciosImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AdministradorDAO;
import modelo.Administrador;
import serviciosInt.AdministradorService;

@Transactional
@Service
public class AdministradorServiceImpl extends GenericServiceImpl<Administrador,AdministradorDAO> implements AdministradorService{

	public AdministradorServiceImpl(){
	}
	

	@Override
	public Administrador alta(Administrador entity) {
		Administrador adminReturn = null;
		if(!this.existe(entity))
			adminReturn = super.alta(entity);
		return adminReturn;
	}
	
	@Override
	public boolean existe(Administrador entity) {
		return (this.getDao().existe(entity));
	}
	
	public boolean baja(Administrador entity){
		return (this.getDao().borrar(entity.getId()));
	}
	
	
	
}
