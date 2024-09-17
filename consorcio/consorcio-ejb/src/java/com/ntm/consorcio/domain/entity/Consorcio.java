/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ntm.consorcio.domain.entity;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;

/**
 *
 * @author Martinotebook
 */
@Entity
public class Consorcio implements Serializable{
    
    @Id
    private String id;
    private String nombre;
    //private Direccion direccion;
    private boolean eliminado;
    
    public Consorcio(){
    this.eliminado = false;
}
    
    public Consorcio(String id, String nombre){
        this.id=id;
        this.nombre=nombre;
        this.eliminado=Boolean.FALSE;
        
    }
    public String getId(){
        return this.id;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public boolean getEliminado() {
        return this.eliminado;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    @Override
    public String toString() {
        return "Consorcio{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", eliminado=" + eliminado +
                '}';
    }
    //    public Direccion getDireccion() {
    //    return direccion;
    //}

    //public void setDireccion(Direccion direccion) {
    //    this.direccion = direccion;
    //}
}
