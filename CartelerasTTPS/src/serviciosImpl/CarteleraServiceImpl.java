package serviciosImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CarteleraDAO;
import modelo.Cartelera;
import serviciosInt.CarteleraService;

@Transactional
@Service
public class CarteleraServiceImpl implements CarteleraService{
	
	@Autowired
	private CarteleraDAO carteleraDAO;
	
	@Override
	public Cartelera alta(Cartelera cartelera) {
		Cartelera carteleraReturn = null;	
		if (!this.existe(cartelera)){
			carteleraReturn = this.getCarteleraDAO().persistir(cartelera);
		}
		return carteleraReturn;
	}

	@Override
	public Cartelera modificar(Cartelera cartelera) {
		return carteleraDAO.actualizar(cartelera);
	}

	
	public boolean baja(Cartelera cartelera) {
		
		return (this.baja(cartelera.getId()));
	}

	@Override
	public Cartelera recuperar(Serializable id) {
		return carteleraDAO.recuperar(id);
	}

	public CarteleraDAO getCarteleraDAO() {
		return carteleraDAO;
	}

	public void setCarteleraDAO(CarteleraDAO carteleraDAO) {
		this.carteleraDAO = carteleraDAO;
	}

	@Override
	public List<Cartelera> recuperarTodos() {
		return this.getCarteleraDAO().recuperarTodos();
	}

	@Override
	public boolean existe(Cartelera cartelera) {
		return this.getCarteleraDAO().existe(cartelera);
	}

	@Override
	public boolean baja(Serializable id) {
		return carteleraDAO.borrar(id);
	}

}
