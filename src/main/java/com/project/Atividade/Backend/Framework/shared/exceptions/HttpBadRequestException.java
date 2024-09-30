package com.project.Atividade.Backend.Framework.shared.exceptions;

public class HttpBadRequestException extends RuntimeException {
    public HttpBadRequestException(String message) {
        super(message);
    }
}
