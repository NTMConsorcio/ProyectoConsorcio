/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ntm.consorcio.logic.entity;

import com.ntm.consorcio.domain.entity.Expensa;
import com.ntm.consorcio.persistence.entity.DAOExpensaInmuebleBean;
import com.ntm.consorcio.domain.entity.ExpensaInmueble;
import com.ntm.consorcio.domain.entity.EstadoExpensaInmueble;
import com.ntm.consorcio.domain.entity.Inmueble;
import com.ntm.consorcio.logic.ErrorServiceException;
import com.ntm.consorcio.persistence.NoResultDAOException;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
/**
 *
 * @author Mauro Sorbello
 */
@Stateless
@LocalBean
public class ExpensaInmuebleServiceBean {
    private @EJB DAOExpensaInmuebleBean dao;
    private @EJB InmuebleServiceBean inmuebleService;
    private @EJB ExpensaServiceBean expensaService;
    
    public void crearExpensaInmueble(Date periodo, EstadoExpensaInmueble estado,String idInmueble, String idExpensa) throws ErrorServiceException {
        try {
            // Verificaciones
            
            if (periodo == null) {
                throw new ErrorServiceException("Debe indicar el periodo");
            }
            
            if (estado == null) {
                throw new ErrorServiceException("Debe indicar el estado");
            }
              
            if (idInmueble == null) {
                throw new ErrorServiceException("Debe indicar el inmueble");
            }
            
            if (idExpensa == null) {
                throw new ErrorServiceException("Debe indicar la expensa");
            }
            /*
            try {
                // Intentar buscar la expensa
                dao.buscarExpensaInmueblePorInmExp(idExpensa, idInmueble, periodo);
                
                // Si llega aquí, significa que se encontró la expensa, así que lanza la excepción
                throw new ErrorServiceException("Existe una expensa generada para el inmueble y el periodo indicado.");
            } catch (NoResultDAOException e) {}
            */
            
            
            ExpensaInmueble expensaInmueble = new ExpensaInmueble();
            expensaInmueble.setId(UUID.randomUUID().toString()); // Genera un UUID único para el recibo
            expensaInmueble.setPeriodo(periodo);             
            expensaInmueble.setEstado(estado);
            expensaInmueble.setEliminado(false);
            
            Inmueble inmueble = inmuebleService.buscarInmueble(idInmueble);
            Expensa expensa = expensaService.buscarExpensa(idExpensa);
            expensaInmueble.setExpensa(expensa);
            expensaInmueble.setInmueble(inmueble);
            dao.guardarExpensaInmueble(expensaInmueble);                 

        } catch (ErrorServiceException e) {
            throw e; // Re-lanzar la excepción específica de servicio
        } catch (Exception ex) {
            ex.printStackTrace(); // Imprimir el stack trace para depuración
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
    
    public ExpensaInmueble buscarExpensaInmueble(String id) throws ErrorServiceException {
        try {
            if (id == null) {
                throw new ErrorServiceException("Debe indicar la expensaInmueble");
            }

            ExpensaInmueble expensaInmueble = dao.buscarExpensaInmueble(id);
            //Preguntar si añadir eliminado
            /*
            if (expensa.getEliminado()){
                throw new ErrorServiceException("No se encuentra la expensa indicada");
            }
            */
            
            
            return expensaInmueble;
            
        } catch (ErrorServiceException ex) {  
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
    
     /**
     * Modifica los atributos del objeto
     * @param idExpensaInmuble String con el id
     * @param periodo Date con la fecha Desde
     * @param estado Estado con la fecha hasta
     * @throws ErrorServiceException 
     */
    public void modificarExpensaInmueble(String idExpensaInmueble, Date periodo, String idInmueble, String idExpensa, EstadoExpensaInmueble estado) throws ErrorServiceException {

        try {

            ExpensaInmueble expensaInmueble = buscarExpensaInmueble(idExpensaInmueble);

            if (idExpensaInmueble== null || idExpensaInmueble.isEmpty()) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            /*
            try {
                // Intentar buscar la expensa
                dao.buscarExpensaInmueblePorInmExp(idExpensa, idInmueble, periodo);
                
                // Si llega aquí, significa que se encontró la expensa, así que lanza la excepción
                throw new ErrorServiceException("Existe una expensa generada para el inmueble y el periodo indicado.");
            } catch (NoResultDAOException e) {}
            */
            /*
            El método compareTo() devuelve:

            0 si las dos fechas son iguales,
            Un valor negativo si la fecha que invoca el método es anterior,
            Un valor positivo si la fecha que invoca el método es posterior.
            */
            
            expensaInmueble.setPeriodo(periodo); 
            expensaInmueble.setEstado(estado);
            Expensa expensa = expensaService.buscarExpensa(idExpensa);
            Inmueble inmueble = inmuebleService.buscarInmueble(idInmueble);
            expensaInmueble.setExpensa(expensa); 
            expensaInmueble.setInmueble(inmueble);
            
            dao.actualizarExpensaInmueble(expensaInmueble);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex){
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
    public Collection<ExpensaInmueble> listarExpensaInmueblePorInmueble(String id, EstadoExpensaInmueble estado) throws ErrorServiceException {
        try {
            
            return dao.listarExpensaInmueblePorInmueble(id, estado);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
    
    public ExpensaInmueble buscarExpensaInmueblePorExpInm(String idExpensa, String idInmueble, Date periodo) throws ErrorServiceException{
        try {
            return dao.buscarExpensaInmueblePorInmExp(idExpensa, idInmueble, periodo);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
    public Collection<ExpensaInmueble> listarExpensaInmuebleActivo() throws ErrorServiceException {
        try {
            return dao.listarExpensaInmuebleActivo();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
    public void eliminarExpensaInmueble(String id) throws ErrorServiceException {

        try {

            ExpensaInmueble expensaInmueble = buscarExpensaInmueble(id);
            expensaInmueble.setEliminado(true);
            
            dao.actualizarExpensaInmueble(expensaInmueble);

        } catch (ErrorServiceException ex) {
            throw ex;
        }
    }
}   