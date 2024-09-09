/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ntm.consorcio.persistence;

import javax.ejb.ApplicationException;

/**
 *
 * @author Tomas
 */

@ApplicationException(rollback=false)
public class NoResultDAOException extends Exception {
    
    public NoResultDAOException() {
    }
    
    public NoResultDAOException(String mensaje) {
        super(mensaje);
    }
}