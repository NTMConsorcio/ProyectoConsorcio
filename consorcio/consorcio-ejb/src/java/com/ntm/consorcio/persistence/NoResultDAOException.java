<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
=======
>>>>>>> f28100bdb2b71470c6c121be303794e7bf1c3f34
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
<<<<<<< HEAD
}
=======
}
>>>>>>> f28100bdb2b71470c6c121be303794e7bf1c3f34
