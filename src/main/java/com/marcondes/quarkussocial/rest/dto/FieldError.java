package com.marcondes.quarkussocial.rest.dto;

import lombok.Data;

@Data
public class FieldError {

    private String field;
    private String messsage;

    public FieldError(String field, String messsage) {
        this.field = field;
        this.messsage = messsage;
    }
}
