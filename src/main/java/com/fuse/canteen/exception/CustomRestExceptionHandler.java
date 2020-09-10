package com.fuse.canteen.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuse.canteen.utils.ApiError;
import com.fuse.canteen.utils.CustomMessageSource;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    private final CustomMessageSource customMessageSource;

    public CustomRestExceptionHandler(CustomMessageSource customMessageSource) {
        this.customMessageSource = customMessageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<String>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            logger.info(objectMapper.writeValueAsString(ex.getBindingResult()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            switch (error.getCode()) {
                case "NotNull":
                    errors.add(customMessageSource.get("error.method.argument.notnull", error.getField()));
                    break;
                case "Pattern":
                    errors.add(customMessageSource.get("error.method.argument.pattern2", error.getField(), error.getDefaultMessage()));
                    break;
                default:
                    errors.add(error.getField() + ": " + error.getDefaultMessage());
            }
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final ApiError apiError = new ApiError(false, HttpStatus.BAD_REQUEST, ex.getClass().getName(), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getHttpStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            String fieldName = error.getField().toLowerCase();
            String defaultmessage = error.getDefaultMessage();
            Object rejectedValue = error.getRejectedValue();
            String message = "";
            switch (defaultmessage) {
                case "notnull":
                    message = customMessageSource.get("notnull", customMessageSource.get(fieldName));
                    break;
                case "notblank":
                    message = customMessageSource.get("notblank", customMessageSource.get(fieldName));
                    break;
                case "minlength":
                    message = customMessageSource.get("minlength", customMessageSource.get(fieldName), rejectedValue);
                    break;
                case "maxlength":
                    message = customMessageSource.get("maxlength", customMessageSource.get(fieldName), rejectedValue);
                    break;
                case "minvalue":
                    message = customMessageSource.get("minvalue", customMessageSource.get(fieldName), rejectedValue);
                    break;
                case "maxvalue":
                    message = customMessageSource.get("maxvalue", customMessageSource.get(fieldName), rejectedValue);
                    break;
                case "pattern":
                    message = customMessageSource.get("pattern", customMessageSource.get(fieldName), rejectedValue);
                    break;
                default:
                    message = defaultmessage;
            }
            errors.add(message);
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final ApiError apiError = new ApiError(false, HttpStatus.BAD_REQUEST, errors.get(0), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getHttpStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = customMessageSource.get("error.type.mismatch", ex.getValue(), ex.getPropertyName(), ex.getRequiredType());

        final ApiError apiError = new ApiError(false, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = customMessageSource.get("error.request.part.missing", ex.getRequestPartName());
        final ApiError apiError = new ApiError(false, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = customMessageSource.get("error.request.parameter.missing", ex.getParameterName());
        final ApiError apiError = new ApiError(false, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }

    //

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = customMessageSource.get("error.method.argument.mismatch", ex.getName(), ex.getRequiredType().getName());

        final ApiError apiError = new ApiError(false, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }

    @ExceptionHandler({DuplicateDataException.class})
    public ResponseEntity<Object> duplicateData(final DuplicateDataException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = customMessageSource.get("error.duplicate.data", ex.getFieldName());
        final ApiError apiError = new ApiError(false, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<String>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
            errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
        }

        final ApiError apiError = new ApiError(false, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }

    @ExceptionHandler({TransactionSystemException.class})
    public ResponseEntity<Object> handleTransactionSystem(final TransactionSystemException e, final WebRequest request) {
        logger.info(e.getClass().getName());
        if (e.getRootCause() instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException) e.getRootCause();
            final List<String> errors = new ArrayList<String>();
            for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
                if (violation.getMessage().contains("null"))
                    errors.add(customMessageSource.get("error.doesn't.exist", violation.getPropertyPath()));
                else
                    errors.add(violation.getPropertyPath() + " " + violation.getMessage());

            }
            final ApiError apiError = new ApiError(false, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
            return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
        }
        return this.handleAll(e, request);
    }


    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(DataIntegrityViolationException ex, WebRequest request) {
        ex.printStackTrace();
        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            logger.info(ex.getClass().getName());
            org.hibernate.exception.ConstraintViolationException violation = ((org.hibernate.exception.ConstraintViolationException) ex.getCause());
            final List<String> errors = new ArrayList<String>();
            if (violation.getConstraintName().contains("unique_") || violation.getConstraintName().contains("uk_") )
                errors.add(ex.getRootCause().getMessage().split("\n")[1].split("Detail:")[1]);
            else if (violation.getConstraintName().contains("_check"))
                errors.add(customMessageSource.get("error.check.constraint", violation.getConstraintName().split("_check")[0]));
            else if (violation.getCause().getLocalizedMessage().contains("not-null"))
                errors.add(customMessageSource.get("error.doesn't.exist", violation.getConstraintName()));
            else if (violation.getCause().getLocalizedMessage().contains("is not present in table"))
                errors.add(customMessageSource.get("error.doesn't.exist", violation.getConstraintName().replace("fk_", "")));
            else
                errors.add(customMessageSource.get("error.database.error"));

            final ApiError apiError = new ApiError(false, HttpStatus.BAD_REQUEST, errors.get(0), errors);
            return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
        }
        final ApiError apiError = new ApiError(false, HttpStatus.BAD_REQUEST, ex.getClass().getName(), customMessageSource.get("error.database.error"));
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }

    // 404

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        final ApiError apiError = new ApiError(false, HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }

    // 405

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        final ApiError apiError = new ApiError(false, HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.toString());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }

    // 415

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

        final ApiError apiError = new ApiError(false, HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }


    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        ex.printStackTrace();
        System.out.println("!!!!!!!!!!!!!!!");
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }


    // 500

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final ApiError apiError = new ApiError(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }


}
