/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ntm.consorcio.logic.entity;

import com.ntm.consorcio.domain.entity.Perfil;
import com.ntm.consorcio.domain.entity.Usuario;
import com.ntm.consorcio.logic.ErrorServiceException;
import com.ntm.consorcio.logic.ErrorServiceLoginException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Mauro Sorbello
 */ 
@Stateless
@LocalBean
public class initAppServiceBean {
    private @EJB UsuarioServiceBean usuarioService;
    private @EJB PerfilServiceBean perfilService;
    private @EJB MenuServiceBean menuService;
    private @EJB SubmenuServiceBean subMenuService;
    public void iniciarAplicacion()throws ErrorServiceException {
        
        try{
<<<<<<< HEAD
            
            try{ 

                usuarioService.login("administrador", "admin123");

            }catch(ErrorServiceException e){

                 Usuario usuario = usuarioService.crearUsuario("ADMINISTRADOR","ADMINISTRADOR", "2615034140", "admin@consorcio.com", "administrador", "admin123", "admin123");
                 menuService.configuracionInicial();
                 Perfil perfil = perfilService.crearPerfil("ADMINISTRADOR", "Perfil creado para el usuario administrador", menuService.listarMenuActivo());
                 System.out.println(usuario.getId());
                 usuarioService.asignarPerfil(usuario.getId(), perfil.getId());
            }       
=======
           try{ 
                Usuario usuario1 = usuarioService.login("administrador","admin123");
             
           } catch (ErrorServiceLoginException e) {
                Usuario usuario = usuarioService.crearUsuario("ADMINISTRADOR","ADMINISTRADOR", "2615034140", "admin@consorcio.com", "administrador", "admin123", "admin123");
                menuService.configuracionInicial();
                Perfil perfil = perfilService.crearPerfil("ADMINISTRADOR", "Perfil creado para el usuario administrador", menuService.listarMenuActivo());
                usuarioService.asignarPerfil(usuario.getId(), perfil.getId());
           }
>>>>>>> 3d436eb5f057983b0fdfd3a04e8e838809d8c6bf
            
        } catch (Exception ex){
            ex.getMessage();
            throw new ErrorServiceException("Error");
        }
    }
}
