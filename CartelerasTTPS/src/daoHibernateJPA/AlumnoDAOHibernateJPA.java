package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import dao.AlumnoDAO;
import modelo.Alumno;

@Repository
public class AlumnoDAOHibernateJPA extends GenericDAOHibernateJPA<Alumno> implements AlumnoDAO {
	
	public AlumnoDAOHibernateJPA(){
		super(Alumno.class);
	}

}
