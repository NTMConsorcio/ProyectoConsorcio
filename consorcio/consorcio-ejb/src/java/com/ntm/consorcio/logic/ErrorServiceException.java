<<<<<<< HEAD
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

=======
>>>>>>> f28100bdb2b71470c6c121be303794e7bf1c3f34
package com.ntm.consorcio.logic;

import javax.ejb.ApplicationException;

<<<<<<< HEAD
/**
 *
 * @author Dell
 */
@ApplicationException(rollback=true)
public class ErrorServiceException extends Exception {

    /**
     * Creates a new instance of <code>PaisException</code> without detail message.
     */
    public ErrorServiceException() {
    }


    /**
     * Constructs an instance of <code>PaisException</code> with the specified detail message.
     * @param msg the detail message.
     */
=======

/**
 *
 * @author Tomas Rando
 */

@ApplicationException(rollback=true)
public class ErrorServiceException extends Exception {
    
    public ErrorServiceException() {
    }
    
>>>>>>> f28100bdb2b71470c6c121be303794e7bf1c3f34
    public ErrorServiceException(String msg) {
        super(msg);
    }
}
