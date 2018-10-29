package com.weka.demo.util;

import java.math.BigDecimal;

/**
 * Created by changlu on 10/16/18.
 */
public class ThreadLocalDemo extends ThreadLocal{

    @Override
    protected Object initialValue() {
        return super.initialValue();
    }

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return super.initialValue();
        }
    };
}
