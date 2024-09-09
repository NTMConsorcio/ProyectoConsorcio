/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ntm.consorcio.logic.entity;

import com.ntm.consorcio.domain.entity.DetalleRecibo;
import com.ntm.consorcio.domain.entity.Recibo;
import com.ntm.consorcio.logic.ErrorServiceException;
import com.ntm.consorcio.persistence.entity.DAOReciboBean;
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
public class ReciboServiceBean {
     private @EJB DAOReciboBean dao;
     
      /**
     * Crea un objeto de la clase
     * @param id String con el id
     * @throws ErrorServiceException 
     */
    public void crearRecibo(Date fechaPago, Recibo.FormaDePago formaDePago) throws ErrorServiceException {
        try {
            // Verificamos si la fecha de pago es null
            if (fechaPago == null) {
                throw new ErrorServiceException("Debe indicar fecha de pago");
            }
            Recibo recibo = new Recibo();
            recibo.setId(UUID.randomUUID().toString()); // Genera un UUID único para el recibo
            recibo.setFechaPago(fechaPago);             // Asigna la fecha de pago
            recibo.setFormaDePago(formaDePago);         // Asigna la forma de pago
            recibo.setEliminado(false);                 // Por defecto no eliminado

            dao.guardarRecibo(recibo);                  // Guardar el recibo

        } catch (ErrorServiceException e) {
            throw e; // Re-lanzar la excepción específica de servicio
        } catch (Exception ex) {
            ex.printStackTrace(); // Imprimir el stack trace para depuración
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
    
    
     /**
     * Busca el objeto y lo devuelve si lo encuentra
     * @param id String con el id
     * @return Objeto recibo
     * @throws ErrorServiceException 
     */
    public Recibo buscarRecibo(String id) throws ErrorServiceException {
        try {
            if (id == null) {
                throw new ErrorServiceException("Debe indicar el recibo");
            }

            Recibo recibo = dao.buscarRecibo(id);
            
            if (recibo.getEliminado()){
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
