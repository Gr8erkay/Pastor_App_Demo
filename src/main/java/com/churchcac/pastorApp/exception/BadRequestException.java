package com.churchcac.pastorApp.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
