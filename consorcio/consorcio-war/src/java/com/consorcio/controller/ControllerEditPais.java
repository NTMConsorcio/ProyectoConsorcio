/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consorcio.controller;

import com.ntm.consorcio.logic.entity.PaisServiceBean;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author tomas
 */
/*
@ManagedBean
@ViewScoped
public class ControllerEditPais implements Serializable {

    private @EJB PaisServiceBean paisServiceBean;
    
    private String nombre;
    
    public String aceptar() {
        
        try{
        
            paisServiceBean.crearPais(nombre);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "País creado con éxito"));

            
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR , "", "Error"));
            return null;
        }
        return "index";
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
}
*/