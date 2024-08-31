package com.ntm.consorcio.domain.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 *
 * @author Tomas
 */

@Entity
public class Pais implements Serializable {
    @Id
    private String id;
    private String nombre;
    private boolean eliminado;
    
    public Pais() {
    }
    
    public Pais(String id, String nombre, boolean eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.eliminado = eliminado;
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
    
}
