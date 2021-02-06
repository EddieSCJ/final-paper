package com.bedigital.application.resources.exceptions;

import lombok.Data;

@Data
public class ErrorMessage {
    private int code;
    private String message;
}