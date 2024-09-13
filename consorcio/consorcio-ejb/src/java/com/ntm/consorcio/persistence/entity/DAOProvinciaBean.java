package com.ntm.consorcio.persistence.entity;

import com.ntm.consorcio.domain.entity.Provincia;
import com.ntm.consorcio.persistence.ErrorDAOException;
import com.ntm.consorcio.persistence.NoResultDAOException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Acceso a datos
 * @author Tomas Rando
 * @version 1.0.0
 */
@Stateless
@LocalBean
public class DAOProvinciaBean {
    @PersistenceContext private EntityManager em;
    
    /**
     * Persiste el objeto en base de datos
     * @param provincia Provincia
     */
    public void guardarProvincia(Provincia provincia) {
        em.persist(provincia);
    }
    
    /**
     * Actualiza el objeto actual en base de datos
     * @param provincia Provincia
     */
    public void actualizarProvincia(Provincia provincia) {
        em.setFlushMode(FlushModeType.COMMIT);
        em.merge(provincia);
        em.flush();
    }
    
    /**
     * Busca el objeto con el id ingresado
     * @param id String
     * @return Provincia
     * @throws NoResultException 
     */
    public Provincia buscarProvincia(String id) throws NoResultException {
        return em.find(Provincia.class, id);
    }
    
    /**
     * Busca el objeto con el nombre especificado
     * @param nombre String
     * @return Provincia
     * @throws ErrorDAOException
     * @throws NoResultDAOException 
     */
    public Provincia buscarProvinciaPorNombre(String nombre) throws ErrorDAOException, NoResultDAOException {
        try {
            if (nombre.length() > 255) {
                throw new ErrorDAOException("La longitud del nombre es incorrecta. Debe tener menos de 255 caracteres");
            }
            
            return (Provincia)  em.createQuery("SELECT pa "
                                          + " FROM provincia pa"
                                          + " WHERE pa.nombre = :nombre"
                                          + " AND pa.eliminado = FALSE").
                                          setParameter("nombre", nombre).
                                          getSingleResult();
        } catch (NoResultException ex) {
            throw new NoResultDAOException("No se encontró la información solicitada");
        } catch (ErrorDAOException ex) {
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorDAOException("Error del sistema.");
        }
    }
    
    /**
     * Busca la lista de objetos de la clase actualmente activos
     * @return Collection<Provincia>
     * @throws ErrorDAOException 
     */
    public Collection<Provincia> listarProvinciaActivo() throws ErrorDAOException {
        try {  
            return em.createQuery("SELECT p "
                                    + " FROM provincia p"
                                    + " WHERE p.eliminado = FALSE").
                                    getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorDAOException("Error del sistema.");
        }
    }  
}