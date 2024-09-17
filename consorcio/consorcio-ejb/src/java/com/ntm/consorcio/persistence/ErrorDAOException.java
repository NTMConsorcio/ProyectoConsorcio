package com.ntm.consorcio.persistence;

import javax.ejb.ApplicationException;

/**
 *
 * @author Tomas
 */

@ApplicationException(rollback=false)
public class ErrorDAOException extends Exception {
    
    public ErrorDAOException() {
    }
    
    public ErrorDAOException(String mensaje) {
        super(mensaje);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> f28100bdb2b71470c6c121be303794e7bf1c3f34
