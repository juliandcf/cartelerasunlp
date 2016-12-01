package test;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dao.CarteleraDAO;
import dao.DocenteDAO;
import dao.MultimediaDAO;
import dao.PublicacionDAO;
import junit.framework.TestCase;
import modelo.Anio;
import modelo.Cartelera;
import modelo.Docente;
import modelo.Multimedia;
import modelo.Publicacion;
import modelo.PublicadorExterno;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/META-INF/ApplicationContext.xml"} )
public class TestHibernateJPA extends TestCase {
	
	@Autowired
	private CarteleraDAO carteleraDAO;
	
	@Autowired
	private MultimediaDAO multimediaDAO;
	
	@Autowired
	private DocenteDAO docenteDAO;
	
	@Autowired
	private PublicacionDAO publicacionDAO;
	
	@Test
	public void testAgregarCartelera(){
		Multimedia m = new Multimedia("Multimedia", "YOUTUBE");
		Multimedia mObtenido = getMultimediaDAO().persistir(m);
		assertNotNull(mObtenido);
		
	}

	public CarteleraDAO getCarteleraDAO() {
		return carteleraDAO;
	}

	public void setCarteleraDAO(CarteleraDAO carteleraDAO) {
		this.carteleraDAO = carteleraDAO;
	}

	public MultimediaDAO getMultimediaDAO() {
		return multimediaDAO;
	}

	public void setMultimediaDAO(MultimediaDAO multimediaDAO) {
		this.multimediaDAO = multimediaDAO;
	}
	
	
	@Test
	public void testAgregarDocente(){
		Docente docente1 = new Docente("doc1", "doc1", "Maria Rosa", "Grecco");
		getDocenteDAO().persistir(docente1);
		Docente docAgregado = getDocenteDAO().recuperar(docente1.getId());
		assertEquals(docente1,docAgregado);
	}
	
	@Test
	public void testRecuperarDocente(){
		Docente docente2 = new Docente("doc2", "doc2", "Susana", "Kutchen");
		getDocenteDAO().persistir(docente2);
		List<Docente> docentes = getDocenteDAO().recuperarTodos("nombre");
		assertNotNull(docentes);
		assertTrue(docentes.size()>0);
	}
//	
//	@Test
//	public void testModificarDocente(){
//		Docente docente3 = new Docente("doc3", "doc3", "Gustavo", "Tombilla");
//		FactoryDAO.getDocenteDAO().persistir(docente3);
//		AnioDictado anio = new AnioDictado(3);
//		FactoryDAO.getAnioDictadoDAO().persistir(anio);
//		docente3.getAnios().add(anio);
//		docente3.setUsuario("DOCENTE3EDITADO");
//		docente3.setNombre("Rodolfo");
//		FactoryDAO.getDocenteDAO().actualizar(docente3);
//		Docente docModificado = FactoryDAO.getDocenteDAO().recuperar(docente3.getId());
//		assertEquals(docente3, docModificado);			
//	}
//	
//	@Test
//	public void testEliminarDocente(){
//		Docente docente4 = new Docente("doc4", "doc4", "Ricardo", "Santillan");
//		AnioDictado anio = new AnioDictado(2);
//		docente4.getAnios().add(anio);
//		FactoryDAO.getAnioDictadoDAO().persistir(anio);
//		FactoryDAO.getDocenteDAO().persistir(docente4);
//		FactoryDAO.getDocenteDAO().borrar(docente4.getId());
//		Docente docEliminado = FactoryDAO.getDocenteDAO().recuperar(docente4.getId());
//		assertNull(docEliminado);			
//	}
//	
//	@Test
//	public void testAgregarPublicacion(){
//
//		Cartelera c = new Cartelera("PrimerAño", "Cartelera de Primer año");
//		getCarteleraDAO().persistir(c);
//		
//		Docente docente5 = new Docente("doc5", "doc5", "Monica", "Suarez");
//		docente5.getAnios().add(Anio.PRIMERO);
//		docente5.getPermisosCarteleras().add(c);
//		getDocenteDAO().persistir(docente5);
//		
//		Publicacion p = new Publicacion("Una publicacion", "Tengo un texto", new Date(), true, docente5, c);
//		docente5.getPublicaciones().add(p);		
//		getPublicacionDAO().persistir(p);
//		
//		docente5.getPublicaciones().add(p);
//		getDocenteDAO().actualizar(docente5);
//		
//		Publicacion publicacionAgregado = getPublicacionDAO().recuperar(p.getId());
//		Docente docenteActualizado = getDocenteDAO().recuperar(docente5.getId()); 
//		
//		assertEquals(p.getId(),publicacionAgregado.getId());
//		assertEquals(docente5,publicacionAgregado.getAutor());
//		assertTrue(docenteActualizado.getPublicaciones().size() == 1);
//		assertTrue(docenteActualizado.getAnios().size() == 1);
//	}
//	
//	@Test
//	public void testAgregarModificarPublicacion(){
//		Docente docente5 = new Docente("doc5", "doc5", "Joaquin", "Sabina");
//		FactoryDAO.getDocenteDAO().persistir(docente5);
//		
//		Cartelera c = new Cartelera("SegundoAño", "Cartelera de Segundo año");
//		FactoryDAO.getCarteleraDAO().persistir(c);
//		
//		Publicacion p = new Publicacion("Publicacion 2", "Tengo un texto", new Date(), true, docente5, c);
//		FactoryDAO.getPublicacionDAO().persistir(p);
//		
//		Docente docente6 = new Docente("doc6", "doc6", "Ricardo", "Sava");
//		FactoryDAO.getDocenteDAO().persistir(docente6);
//		
//		p.setAutor(docente6);
//		p.setTitulo("Publicacion 2 modificada");
//		Publicacion publicacionRecuperada = FactoryDAO.getPublicacionDAO().actualizar(p);
//		
//		assertEquals(p.getAutor(),publicacionRecuperada.getAutor());
//		assertEquals("Publicacion 2 modificada",publicacionRecuperada.getTitulo());
//	}
//	
//	@Test
//	public void testEliminarPublicacion(){
//		PublicadorExterno publicadorExterno = new PublicadorExterno("publicador1", "publicador1", "Rogelio", "Gonzalez");
//		FactoryDAO.getPublicadorExternoDAO().persistir(publicadorExterno);
//		
//		Cartelera c = new Cartelera("Jobs", "Cartelera de Trabajos");
//		FactoryDAO.getCarteleraDAO().persistir(c);
//		
//		Publicacion p = new Publicacion("Publicacion Rogelio Gonzalez", "Rogelio Gonzalez es calidad", new Date(), true, publicadorExterno, c);
//		FactoryDAO.getPublicacionDAO().persistir(p);
//		
//		FactoryDAO.getPublicacionDAO().borrar(p.getId());
//		
//		Publicacion publicacionEliminada = FactoryDAO.getPublicacionDAO().recuperar(p.getId());
//		
//		assertNull(publicacionEliminada);
//	}

	public DocenteDAO getDocenteDAO() {
		return docenteDAO;
	}

	public void setDocenteDAO(DocenteDAO docenteDAO) {
		this.docenteDAO = docenteDAO;
	}

	public PublicacionDAO getPublicacionDAO() {
		return publicacionDAO;
	}

	public void setPublicacionDAO(PublicacionDAO publicacionDAO) {
		this.publicacionDAO = publicacionDAO;
	}
	
}
