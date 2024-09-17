/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ntm.consorcio.logic.entity;

import com.ntm.consorcio.domain.entity.Consorcio;
import java.util.UUID;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Martinotebook
 */
@Stateless
@LocalBean
public class ConsorcioServiceBean {

    public void crearConsorcio(String nombre){
        if (nombre == null || nombre.isEmpty()) {
                 throw new IllegalArgumentException("Debe indicar el nombre");
            }        
        Consorcio consorcio = new Consorcio();
        consorcio.setId(UUID.randomUUID().toString());
        consorcio.setNombre(nombre);
        consorcio.setEliminado(false);
    }
}

