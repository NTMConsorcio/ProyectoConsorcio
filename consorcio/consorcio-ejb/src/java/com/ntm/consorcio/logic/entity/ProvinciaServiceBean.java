package com.ntm.consorcio.logic.entity;

import com.ntm.consorcio.domain.entity.Provincia;
import com.ntm.consorcio.logic.ErrorServiceException;
import com.ntm.consorcio.persistence.NoResultDAOException;
import com.ntm.consorcio.persistence.entity.DAOProvinciaBean;
import java.util.Collection;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Clase que implementa los métodos correspondientes a la lógica de negocio
 * @author Tomas Rando
 * @version 1.0.0
 */
@Stateless
@LocalBean
public class ProvinciaServiceBean {
    
    private @EJB DAOProvinciaBean dao;
    
    /**
     * Crea un objeto de la clase
     * @param nombre String con el nombre
     * @throws ErrorServiceException 
     */
    public void crearProvincia(String nombre) throws ErrorServiceException {

        try {
            
            if (nombre == null || nombre.isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre");
            }

            try {
                dao.buscarProvinciaPorNombre(nombre);
                throw new ErrorServiceException("Existe una provincia con el nombre indicado");
            } catch (NoResultDAOException ex) {
               
            }

            Provincia provincia = new Provincia();
            provincia.setId(UUID.randomUUID().toString());
            provincia.setNombre(nombre);
            provincia.setEliminado(false);

            dao.guardarProvincia(provincia);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex){
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
    
    /**
     * Modifica los atributos del objeto
     * @param idProvincia String con el id
     * @param nombre String con el nombre
     * @throws ErrorServiceException 
     */
    public void modificarProvincia(String idProvincia, String nombre) throws ErrorServiceException {

        try {

            Provincia provincia = buscarProvincia(idProvincia);

            if (nombre == null || nombre.isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre");
            }

            try{
                Provincia provinciaExistente = dao.buscarProvinciaPorNombre(nombre);
                if (!provinciaExistente.getId().equals(idProvincia)){
                  throw new ErrorServiceException("Existe una provincia con el nombre indicado");  
                }
            } catch (NoResultDAOException ex) {}

            provincia.setNombre(nombre);
            
            dao.actualizarProvincia(provincia);

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
     * @return Objeto Provincia
     * @throws ErrorServiceException 
     */
    public Provincia buscarProvincia(String id) throws ErrorServiceException {

        try {
            
            if (id == null) {
                throw new ErrorServiceException("Debe indicar la provincia");
            }

            Provincia provincia = dao.buscarProvincia(id);
            
            if (provincia.getEliminado()){
                throw new ErrorServiceException("No se encuentra la provincia indicada");
            }

            return provincia;
            
        } catch (ErrorServiceException ex) {  
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    /**
     * Elimina el objeto
     * @param id String que representa el id
     * @throws ErrorServiceException 
     */
    public void eliminarProvincia(String id) throws ErrorServiceException {

        try {

            Provincia provincia = buscarProvincia(id);
            provincia.setEliminado(true);
            
            dao.actualizarProvincia(provincia);

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }

    }

    /**
     * Devuelve una lista con los objetos de la clase activos
     * @return Collection<Provincia>
     * @throws ErrorServiceException 
     */
    public Collection<Provincia> listarProvinciaActivo() throws ErrorServiceException {
        try {
            
            return dao.listarProvinciaActivo();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}