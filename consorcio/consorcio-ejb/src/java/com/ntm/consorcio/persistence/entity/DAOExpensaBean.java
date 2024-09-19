/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ntm.consorcio.persistence.entity;

import com.ntm.consorcio.domain.entity.Expensa;
import com.ntm.consorcio.persistence.ErrorDAOException;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Mauro Sorbello
 */
@Stateless
@LocalBean
public class DAOExpensaBean {
        @PersistenceContext private EntityManager em;
/**
     * Persiste el objeto en base de datos
     * @param expensa Expensa
     */
    public void guardarExpensa(Expensa expensa) {
        em.persist(expensa);
    }
    
        /**
     * Actualiza el objeto actual en base de datos
     * @param expensa Expensa
     */
    public void actualizarExpensa(Expensa expensa) {
        em.setFlushMode(FlushModeType.COMMIT);
        em.merge(expensa);
        em.flush();
    }
    
        /**
     * Busca el objeto con el id ingresado
     * @param id String
     * @return Expensa
     * @throws NoResultException 
     */
    
    //Preguntar profe si esta no es solo buscar expensa, en vez de buscar expensaInmueble
    public Expensa buscarExpensa(String id) throws NoResultException {
        return em.find(Expensa.class, id);
    }
    
    
    public Collection<Expensa> listarExpensaActivo() throws ErrorDAOException {
        try {  
            return em.createQuery("SELECT e "
                                    + " FROM expensa e"
                                    + " WHERE e.eliminado = FALSE").
                                    getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorDAOException("Error del sistema.");
        }
    }  
    
    public Expensa buscarExpensaPorFecha(Date fecha) throws ErrorDAOException  {
        try {  
            return (Expensa) em.createQuery("SELECT e "
                                    + " FROM expensa e"
                                    + " WHERE e.fechaDesde <= :fecha"
                                    + " AND e.fechaHasta >=: fecha").
                                    setParameter("fecha", fecha).
                                    getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorDAOException("Error del sistema.");
        } 
    }
}

