/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ntm.consorcio.logic.entity;

import com.ntm.consorcio.domain.entity.Menu;
import com.ntm.consorcio.domain.entity.SubMenu;
import com.ntm.consorcio.logic.ErrorServiceException;
import com.ntm.consorcio.persistence.NoResultDAOException;
import com.ntm.consorcio.persistence.entity.DAOMenuBean;
import com.ntm.consorcio.persistence.entity.DAOSubMenuBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


/**
 *
 * @author Mauro Sorbello
 */
@Stateless
@LocalBean
public class MenuServiceBean {
    @EJB
    private DAOMenuBean dao;
    public void crearMenu(String nombre, String icon, int orden) throws ErrorServiceException {
        try {
            // Validar nombre del submenú
            if (nombre == null || nombre.isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre del menú");
            }

            // Verificar si ya existe un submenú con ese nombre
            if (dao.buscarMenuPorNombre(nombre) != null) {
                throw new ErrorServiceException("Existe un menú con el nombre indicado");
            }

            // Validar el orden
            if (orden <= 0) {
                throw new ErrorServiceException("Debe indicar el orden");
            }

            // Verificar si ya existe un submenú con ese orden en el mismo menú
            if (dao.buscarMenuPorOrden(orden) != null) {
                throw new ErrorServiceException("Existe un menú con el orden indicado");
            }

            // Crear el nuevo submenú
            Menu menu = new Menu();
            menu.setId(UUID.randomUUID().toString());
            menu.setNombre(nombre);
            menu.setOrden(orden);
            menu.setEliminado(false);
            menu.setIcon(icon);
            dao.guardarMenu(menu);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistemas");
        }
    }
    
    public void modificarMenu(String idMenu, String nombre, String icon, int orden) throws ErrorServiceException {
        try {
            // Validar idMenu
            if (idMenu == null || idMenu.isEmpty()) {
                throw new ErrorServiceException("Debe indicar el id del menú");
            }

            // Buscar el menú por idMenu
            Menu menu = buscarMenu(idMenu);
            if (menu == null || menu.getEliminado() == true) {
                throw new ErrorServiceException("No se encontró el menú con el ID indicado");
            }
            
            // Validar nombre del menú
            if (nombre == null || nombre.isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre del submenú");
            }
            try {
                Menu menuAux = dao.buscarMenuPorNombre(nombre);
                if (!menuAux.getId().equals(menu.getId())) {
                    throw new ErrorServiceException("Existe un menú con el nombre indicado");
                }      
            } catch (NoResultDAOException ex) {}

            // Validar el orden
            if (orden <= 0) {
                throw new ErrorServiceException("Debe indicar el orden");
            } else {
                try {
                    Menu menuAux = dao.buscarMenuPorOrden(orden);
                    if (!menuAux.getId().equals(menu.getId())){
                       throw new ErrorServiceException("Existe un menú con el orden indicado"); 
                    }
                }catch (NoResultDAOException nrx) {}
            }
            
            if (icon == null || icon.isEmpty()) {
                throw new ErrorServiceException("Debe indicar el icon del menú");
            }
               
            // Crear el nuevo submenú
            menu.setNombre(nombre);
            menu.setIcon(icon); 
            menu.setOrden(orden);
            
            dao.actualizarMenu(menu);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistemas");
        }
    }
    
     /**
     * Elimina el objeto
     * @param id String que representa el id
     * @throws ErrorServiceException 
     */
    public void eliminarMenu(String id) throws ErrorServiceException {

        try {

            Menu menu = buscarMenu(id);
            menu.setEliminado(true);
            
            dao.actualizarMenu(menu);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
    
    public Menu buscarMenu(String id) throws ErrorServiceException {

        try {
            
            if (id == null) {
                throw new ErrorServiceException("Debe indicar el sub menu");
            }

            Menu menu = dao.buscarMenu(id);
            
            if (menu.getEliminado()){
                throw new ErrorServiceException("No se encuentra en subMenu indicado");
            }

            return menu;
            
        } catch (ErrorServiceException ex) {  
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
    
    public Menu buscarMenuPorSubMenu(String idSubMenu) throws ErrorServiceException {

        try {
            
            if (idSubMenu == null) {
                throw new ErrorServiceException("Debe indicar el menu");
            }

            Menu menu = dao.buscarMenuPorSubMenu(idSubMenu);

            return menu;
            
        } catch (ErrorServiceException ex) {  
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
    
    public Menu buscarMenuPorNombre(String nombre) throws ErrorServiceException {

        try {
            
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre del menu");
            }

            Menu menu = dao.buscarMenuPorNombre(nombre);

            return menu;
            
        } catch (ErrorServiceException ex) {  
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
