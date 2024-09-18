/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ntm.consorcio.domain.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Mauro Sorbello
 */
@Entity
public class Inmueble implements Serializable {
    @Id
    private String id;
    private String piso;
    private String dpto;
    private EstadoInmueble estadoInmueble;
    private Boolean eliminado;
    
    public enum EstadoInmueble{
        
    }

    public Inmueble(String id, String piso, String dpto, EstadoInmueble estadoInmueble) {
        this.id = id;
        this.piso = piso;
        this.dpto = dpto;
        this.estadoInmueble = estadoInmueble;
        this.eliminado=false;
    }
    

    public Inmueble() {
        this.eliminado=false;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
    
    
    
    
    public EstadoInmueble geEstadoInmueble(){
        return estadoInmueble;
    }
    
    public String getPiso() {
        return piso;
    }
    
    public String getdpto(){
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public void setEstadoInmueble(EstadoInmueble estadoInmueble) {
        this.estadoInmueble = estadoInmueble;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }
            
    
            
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inmueble)) {
            return false;
        }
        Inmueble other = (Inmueble) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntm.consorcio.domain.entity.Inmueble[ id=" + id + " ]";
    }
    
}
