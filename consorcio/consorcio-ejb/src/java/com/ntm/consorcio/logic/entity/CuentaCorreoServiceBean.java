package com.ntm.consorcio.logic.entity;

import com.ntm.consorcio.domain.entity.Consorcio;
import com.ntm.consorcio.domain.entity.CuentaCorreo;
import com.ntm.consorcio.logic.ErrorServiceException;
import com.ntm.consorcio.persistence.NoResultDAOException;
import com.ntm.consorcio.persistence.entity.DAOCuentaCorreoBean;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 * Clase que implementa los métodos de CuentaCorreo
 * @version 1.0.0
 * @author Tomas Rando
 */
@Stateless
@LocalBean
public class CuentaCorreoServiceBean {

   private @EJB DAOCuentaCorreoBean dao;
   private @EJB ConsorcioServiceBean consorcioService;
    
   /**
    * Crea una cuenta de correo y llama al dao para persistirla en la base de datos
    * @param correo String
    * @param clave String
    * @param puerto String
    * @param smtp String
    * @param tls boolean
    * @param idConsorcio String
    * @throws ErrorServiceException 
    */
    public void crearCuentaCorreo(String correo, String clave, String puerto, String smtp, boolean tls, String idConsorcio) throws ErrorServiceException {
                
        try {
            Consorcio consorcio = consorcioService.buscarConsorcio(idConsorcio);
            if (!verificar(correo)) {
                throw new ErrorServiceException("Debe indicar el correo");
            }
            
            if (!verificar(clave)) {
                throw new ErrorServiceException("Debe indicar la clave");
            }
            
            if (!verificar(puerto)) {
                throw new ErrorServiceException("Debe indicar el puerto");
            }
            
            if (!verificar(smtp)) {
                throw new ErrorServiceException("Debe indicar el smtp");
            }
            
            if (!verificar(idConsorcio)) {
                throw new ErrorServiceException("Debe indicar el consorcio");
            }
            
            try {
                dao.buscarCuentaCorreoPorCorreo(correo);
                throw new ErrorServiceException("Existe esa cuenta de correo");
            } catch (NoResultDAOException e) {
            }
            
            CuentaCorreo cuentaCorreo = new CuentaCorreo();
            cuentaCorreo.setCorreo(correo);
            cuentaCorreo.setClave(clave);
            cuentaCorreo.setSmtp(smtp);
            cuentaCorreo.setEliminado(false);
            cuentaCorreo.setTls(tls);
            cuentaCorreo.setPuerto(puerto);
            cuentaCorreo.setId(UUID.randomUUID().toString());
            cuentaCorreo.setConsorcio(consorcio);
            
            dao.guardarCuentaCorreo(cuentaCorreo);
            
        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
    
    /**
     * Modifica una cuenta de correo
     * @param idCuentaCorreo String
     * @param correo String
     * @param clave String
     * @param puerto String
     * @param smtp String
     * @param tls boolean
     * @param idConsorcio String
     * @throws ErrorServiceException 
     */
    public void modificarCuentaCorreo(String idCuentaCorreo, String correo, String clave, String puerto, String smtp, boolean tls, String idConsorcio) throws ErrorServiceException {

        try {

            CuentaCorreo cuentaCorreo = buscarCuentaCorreo(idCuentaCorreo);
            Consorcio consorcio = consorcioService.buscarConsorcio(idConsorcio);
            if (!verificar(correo)) {
                throw new ErrorServiceException("Debe indicar el correo");
            }
            
            if (!verificar(clave)) {
                throw new ErrorServiceException("Debe indicar la clave");
            }
            
            if (!verificar(puerto)) {
                throw new ErrorServiceException("Debe indicar el puerto");
            }
            
            if (!verificar(smtp)) {
                throw new ErrorServiceException("Debe indicar el smtp");
            }
            
            if (!verificar(idConsorcio)) {
                throw new ErrorServiceException("Debe indicar el consorcio");
            }

            try {
                CuentaCorreo cuentaCorreoExistente = dao.buscarCuentaCorreoPorCorreo(correo);
                if (!cuentaCorreoExistente.getId().equals(idCuentaCorreo)){
                  throw new ErrorServiceException("Existe una cuenta de correo con el mismo correo");  
                }
            } catch (NoResultDAOException ex) {
            }

            cuentaCorreo.setCorreo(correo);
            cuentaCorreo.setClave(clave);
            cuentaCorreo.setSmtp(smtp);
            cuentaCorreo.setTls(tls);
            cuentaCorreo.setPuerto(puerto);
            cuentaCorreo.setConsorcio(consorcio);
            
            dao.actualizarCuentaCorreo(cuentaCorreo);

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
     * @return Objeto CuentaCorreo
     * @throws ErrorServiceException 
     */
    public CuentaCorreo buscarCuentaCorreo(String id) throws ErrorServiceException {

        try {
            
            if (verificar(id)) {
                throw new ErrorServiceException("Debe indicar la cuenta de correo");
            }

            CuentaCorreo cuentaCorreo = dao.buscarCuentaCorreo(id);
            
            if (cuentaCorreo.isEliminado()){
                throw new ErrorServiceException("No se encuentra el correo indicado");
            }

            return cuentaCorreo;
            
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
    public void eliminarCuentaCorreo(String id) throws ErrorServiceException {

        try {

            CuentaCorreo cuentaCorreo = buscarCuentaCorreo(id);
            cuentaCorreo.setEliminado(true);
            
            dao.actualizarCuentaCorreo(cuentaCorreo);

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }

    }

    /**
     * Devuelve una CuentaCorreo con los objetos de la clase activos
     * @return CuentaCorreo
     * @throws ErrorServiceException 
     */
    public CuentaCorreo listarCuentaCorreoActiva() throws ErrorServiceException {
        try {
            return dao.buscarCuentaCorreoActiva();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
    
    /**
     * Verifica que la String no sea vacía o nula.
     * @param st String
     * @return boolean
     */
    public boolean verificar(String st) {
        if (st.isEmpty() || st == null) {
            return false;
        }
        return true;
    }
}
