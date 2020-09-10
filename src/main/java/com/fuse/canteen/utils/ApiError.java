package com.fuse.canteen.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private boolean status;
    private HttpStatus httpStatus;
    @JsonProperty("message")
    private String message;
    @JsonProperty("errors")
    private List<String> errors;

    public ApiError(boolean status, final HttpStatus httpStatus, final String message, final String error) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.message = message;
        errors = Arrays.asList(error);
    }
}