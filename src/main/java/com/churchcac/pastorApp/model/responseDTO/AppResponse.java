package com.churchcac.pastorApp.model.responseDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppResponse<T> {

    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String statusCode;
    private Boolean isSuccessful;
    private HttpStatus httpStatus;
    private final LocalDateTime time = LocalDateTime.now();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public static  <T> AppResponse<T> buildSuccessTxn(T data) {
        AppResponse<T> response = new AppResponse<>();
        response.setHttpStatus(HttpStatus.CREATED);
        response.setMessage("SUCCESS");
        response.setIsSuccessful(true);
        response.setResult(data);
        return response;
    }

    public static  <T> AppResponse<T> buildSuccess(T data) {
        AppResponse<T> response = new AppResponse<>();
        response.setIsSuccessful(true);
        response.setResult(data);
        response.setMessage("Completed");
        return response;
    }

    public static AppResponse<Void> build() {
        AppResponse<Void> response = new AppResponse<>();
        response.setIsSuccessful(true);
        return response;
    }

}