/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ntm.consorcio.persistence.entity;

import com.ntm.consorcio.domain.entity.ExpensaInmueble;
import com.ntm.consorcio.domain.entity.ExpensaInmueble.Estado;
import com.ntm.consorcio.persistence.ErrorDAOException;
import com.ntm.consorcio.persistence.NoResultDAOException;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Mauro Sorbello
 */
@Stateless
@LocalBean
public class DAOExpensaInmuebleBean {
    @PersistenceContext private EntityManager em;
    /**
     * Persiste el objeto en base de datos
     * @param expensaInmueble ExpensaInmueble
     */
    public void guardarExpensaInmueble(ExpensaInmueble expensaInmuble) {
        em.persist(expensaInmuble);
    }    
    
    /**
     * Actualiza el objeto actual en base de datos
     * @param expensaInmueble ExpensaInmueble
     */
    public void actualizarExpensaInmueble(ExpensaInmueble expensaInmueble) {
        em.setFlushMode(FlushModeType.COMMIT);
        em.merge(expensaInmueble);
        em.flush();
    }
    
     /**
     * Busca el objeto con el id ingresado
     * @param id String
     * @return ExpensaInmueble
     * @throws NoResultException 
     */
    public ExpensaInmueble buscarExpensaInmueble(String id) throws NoResultException {
        return em.find(ExpensaInmueble.class, id);
    }
    
        /**
     * Busca la lista de objetos por inmueble
     * @param idInmueble String 
     * @param estado Estado
     * @return Collection<ExpensaInmueble>
     * @throws ErrorDAOException
     * @throws NoResultDAOException 
     */
    
    public Collection<ExpensaInmueble> listarExpensaInmueblePorInmueble(String idInmueble, Estado estado) throws ErrorDAOException {
        try {  
            return em.createQuery("SELECT ei "
                                    + " FROM expensaInmueble ei"
                                    + " JOIN ei.inmueble i"
                                    + " WHERE i.id = :idInmueble "
                                    + " AND ei.estado = :estado  ").
                                    setParameter("idInmueble", idInmueble).
                                    setParameter("estado", estado).
                                    getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorDAOException("Error del sistema.");
        }
    } 
}