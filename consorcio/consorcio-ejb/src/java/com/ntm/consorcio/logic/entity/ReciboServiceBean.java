/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ntm.consorcio.logic.entity;

import com.ntm.consorcio.domain.entity.Recibo;
import com.ntm.consorcio.logic.ErrorServiceException;
import com.ntm.consorcio.persistence.NoResultDAOException;
import com.ntm.consorcio.persistence.entity.DAOReciboBean;
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
public class ReciboServiceBean {
     private @EJB DAOReciboBean dao;
     
      /**
     * Crea un objeto de la clase
     * @param id String con el id
     * @throws ErrorServiceException 
     */
    public void crearRecibo(Date fechaPago,enum formaDePago) throws ErrorServiceException {

        try {
            
            if (fechaPago == null || fechaPago.isEmpty()) {
                throw new ErrorServiceException("Debe indicar fecha de pago");
            }
            Recibo recibo = new Recibo();
            recibo.setId(UUID.randomUUID().toString());
            recibo.setFechaPago(fechaPago);
            recibo.setFormaDePago(formaDePago);
            recibo.setEliminado(false);

            dao.guardarRecibo(recibo);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex){
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
    
     /**
     * Busca el objeto y lo devuelve si lo encuentra
     * @param id String con el id
     * @return Objeto país
     * @throws ErrorServiceException 
     */
    public Recibo buscarRecibo(String id) throws ErrorServiceException {

        try {
            
            if (id == null) {
                throw new ErrorServiceException("Debe indicar el recibo");
            }

            Recibo recibo = dao.buscarRecibo(id);
            
            if (pais.getEliminado()){
                throw new ErrorServiceException("No se encuentra en recibo indicado");
            }

            return recibo;
            
        } catch (ErrorServiceException ex) {  
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
    
    public void calcularTotal(Collection<DetalleRecibo> detalles){
        double total = 0.0;
        
        for (DetalleRecibo detalle : detalles) {
            total += detalle.getSubtotal();
        }
    }
    
    public void agregarDetalle(){
        dao.agregarDetalle();
    }
    
    public Collection<Recibo> listarReciboPorHabitante(String id) throws ErrorServiceException {
        try {
            
            return dao.listarReciboPorHabitante(id);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
