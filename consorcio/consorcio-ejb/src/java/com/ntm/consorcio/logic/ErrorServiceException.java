package com.ntm.consorcio.logic;

import javax.ejb.ApplicationException;


/**
 *
 * @author Tomas Rando
 */

@ApplicationException(rollback=true)
public class ErrorServiceException extends Exception {
    
    public ErrorServiceException() {
    }
    
    public ErrorServiceException(String msg) {
        super(msg);
    }
}
