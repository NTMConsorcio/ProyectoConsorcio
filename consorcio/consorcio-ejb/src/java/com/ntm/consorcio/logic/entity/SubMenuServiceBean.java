/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ntm.consorcio.logic.entity;

import com.ntm.consorcio.domain.entity.Pais;
import com.ntm.consorcio.domain.entity.SubMenu;
import com.ntm.consorcio.logic.ErrorServiceException;
import com.ntm.consorcio.persistence.NoResultDAOException;
import com.ntm.consorcio.persistence.entity.DAOSubMenuBean;
import java.awt.Menu;
import java.util.ArrayList;
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
public class SubMenuServiceBean {
    @EJB
    private MenuServiceBean menuService;
    
    @EJB
    private DAOSubMenuBean dao;
    
    @EJB
    private DAOMenu daoMenu;
    
    /**
     * Crea un objeto de la clase SubMenu
     * @param idMenu String con el idMenu
     * @param nombre String con el nombre
     * @param url String con la url
     * @param orden int con el orden
     * @throws ErrorServiceException 
     */
    public void crearSubMenu(String idMenu, String nombre, String url, int orden) throws ErrorServiceException {
        try {
            // Validar idMenu
            if (idMenu == null || idMenu.isEmpty()) {
                throw new ErrorServiceException("Debe indicar el id del menú");
            }

            // Buscar el menú por idMenu
            Menu menu = menuService.buscarMenu(idMenu);
            if (menu == null) {
                throw new ErrorServiceException("No se encontró el menú con el ID indicado");
            }

            // Validar nombre del submenú
            if (nombre == null || nombre.isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre del submenú");
            }

            // Verificar si ya existe un submenú con ese nombre
            if (daoMenu.buscarSubMenuPorNombre(nombre) != null) {
                throw new ErrorServiceException("Existe un submenú con el nombre indicado");
            }

            // Validar el orden
            if (orden <= 0) {
                throw new ErrorServiceException("Debe indicar un orden positivo");
            }

            // Verificar si ya existe un submenú con ese orden en el mismo menú
            if (dao.buscarSubMenuPorMenuYOrden(menu.getId(), orden) != null) {
                throw new ErrorServiceException("Existe un submenú para el menú con el orden indicado");
            }

            // Crear el nuevo submenú
            SubMenu subMenu = new SubMenu();
            subMenu.setId(UUID.randomUUID().toString());
            subMenu.setNombre(nombre);
            subMenu.setUrl(url); 
            subMenu.setOrden(orden);

            Collection<SubMenu> subMenus = new ArrayList<SubMenu>();
            if (Menu.getSubMenu() != null) {
                subMenus.addAll(menu.getSubMenu());
            }
            subMenus.add(subMenu);
            
            menu.setSubMenu(subMenus);
            dao.guardarSubMenu(subMenu);
            daoMenu.guardarMenu(menu);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistemas");
        }
    }
    
    /**
     * Modifica los atributos del objeto
     * @param idPais String con el id
     * @param nombre String con el nombre
     * @throws ErrorServiceException 
     */
    public void modificarSubMenu(String idMenu, String idSubMenu, String nombre, String url, int orden) throws ErrorServiceException {
        try {
            // Validar idMenu
            if (idMenu == null || idMenu.isEmpty()) {
                throw new ErrorServiceException("Debe indicar el id del menú");
            }

            // Buscar el menú por idMenu
            Menu menu = menuService.buscarMenu(idMenu);
            if (menu == null || menu.getEliminado() == true) {
                throw new ErrorServiceException("No se encontró el menú con el ID indicado");
            }
            
            SubMenu subMenu = dao.buscarSubMenu(idSubMenu);
            if (subMenu == null || subMenu.getEliminado() == true) {
                throw new ErrorServiceException("No se encontró el sub menú con el ID indicado");
            }
            
            
            // Validar nombre del submenú
            if (nombre == null || nombre.isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre del submenú");
            }

            // Verificar si ya existe un submenú con ese nombre
            if (daoMenu.buscarSubMenuPorNombre(nombre) != null) {
                throw new ErrorServiceException("Existe un submenú con el nombre indicado");
            }

            // Validar el orden
            if (orden <= 0) {
                throw new ErrorServiceException("Debe indicar un orden positivo");
            }
            
            if (url == null || url.isEmpty()) {
                throw new ErrorServiceException("Debe indicar la url del submenú");
            }

            SubMenu subMenuAux = dao.buscarSubMenuPorMenuYOrden(menu.getId(), orden);
            if (!subMenuAux.getId().equals(subMenu.getId())) {
                throw new ErrorServiceException("Existe un submenú para el menú con el orden indicado y con diferente ID");
            }
               
            // Crear el nuevo submenú
            subMenu.setNombre(nombre);
            subMenu.setUrl(url); 
            subMenu.setOrden(orden);
            
            dao.actualizarSubMenu(subMenu);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistemas");
        }
    }

    /**
     * Busca el objeto y lo devuelve si lo encuentra
     * @param id String con el id
     * @return Objeto país
     * @throws ErrorServiceException 
     */
    public Pais buscarPais(String id) throws ErrorServiceException {

        try {
            
            if (id == null) {
                throw new ErrorServiceException("Debe indicar el país");
            }

            Pais pais = dao.buscarPais(id);
            
            if (pais.getEliminado()){
                throw new ErrorServiceException("No se encuentra en país indicado");
            }

            return pais;
            
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
    public void eliminarPais(String id) throws ErrorServiceException {

        try {

            Pais pais = buscarPais(id);
            pais.setEliminado(true);
            
            dao.actualizarPais(pais);

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }

    }

    /**
     * Devuelve una lista con los objetos de la clase activos
     * @return Collection<Pais>
     * @throws ErrorServiceException 
     */
    public Collection<Pais> listarPaisActivo() throws ErrorServiceException {
        try {
            
            return dao.listarPaisActivo();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
