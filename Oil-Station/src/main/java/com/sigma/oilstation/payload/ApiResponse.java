package com.sigma.oilstation.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {

    private boolean success;

    private String message;

    private T data;

    private ApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    private ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    private ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> successResponse(T data) {
        return new ApiResponse<>(true, data);
    }

    public static <T> ApiResponse<T> successResponse(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> successResponse(String message) {
        return new ApiResponse<>(true, message);
    }

    public static <T> ApiResponse<T> errorResponse(String message) {
        return new ApiResponse<>(false, message);
    }

}
