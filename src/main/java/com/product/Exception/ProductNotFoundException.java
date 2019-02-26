package com.product.Exception;

import com.product.controller.LogUtil;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends  RuntimeException {
    Logger log = LogUtil.getLog(this);
    public ProductNotFoundException(String exception){
        super(exception);
        log.info("exception occured as no data ");

    }


}
