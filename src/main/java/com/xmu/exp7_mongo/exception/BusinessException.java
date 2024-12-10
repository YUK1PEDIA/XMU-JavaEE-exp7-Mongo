//School of Informatics Xiamen University, GPL-3.0 license
package com.xmu.exp7_mongo.exception;

import com.xmu.exp7_mongo.model.ReturnNo;

public class BusinessException extends RuntimeException{

    private ReturnNo errno;

    public BusinessException(ReturnNo errno, String message) {
        super(message);
        this.errno = errno;
    }

    public BusinessException(ReturnNo errno) {
        super(errno.getMessage());
        this.errno = errno;
    }

    public ReturnNo getErrno(){
        return this.errno;
    }
}
