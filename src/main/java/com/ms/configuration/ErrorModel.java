package com.ms.configuration;

import lombok.Data;


@Data
public class ErrorModel {

    private String mensaje;

    public ErrorModel(String message) {

        this.mensaje = message;

    }


}
