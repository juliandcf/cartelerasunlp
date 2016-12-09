package serviciosInt;

import java.io.Serializable;
import java.util.List;

import modelo.Cartelera;

public interface CarteleraService {

	public Cartelera alta(Cartelera cartelera);
	public Cartelera modificar(Cartelera cartelera);
	public boolean baja(Cartelera cartelera);
	public Cartelera recuperar(Serializable id);
	public List<Cartelera> recuperarTodos();
	public boolean existe(Cartelera cartelera);
	
	
}
