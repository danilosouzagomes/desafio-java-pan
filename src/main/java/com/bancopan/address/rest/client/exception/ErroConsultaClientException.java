package com.bancopan.address.rest.client.exception;

import org.springframework.web.client.RestClientException;

public class ErroConsultaClientException extends RuntimeException {
    public ErroConsultaClientException(Object object, RestClientException e) {
    }
}
