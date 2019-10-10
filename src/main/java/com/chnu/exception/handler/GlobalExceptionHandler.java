package com.chnu.exception.handler;

import com.chnu.exception.GeneralValidationException;
import com.chnu.rest.GenericResponse;
import com.chnu.util.LoggerUtil;
import com.chnu.util.PropertiesUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.chnu.util.PropertiesUtil.getProperty;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerUtil.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception ex, WebRequest request) {
        GenericResponse response = GenericResponse.error(ex.getMessage());
        response.setAdditionalInformation(Collections.singletonMap("requestUrl", request.getDescription(false)));
        logger.error("The following exception occurred " + ex.getMessage());
        if(Boolean.parseBoolean(getProperty(PropertiesUtil.LOGGER_PROPERTIES, "custom.logging.exception.trace.display"))) {
            logger.error("Description " + getExceptionString(ex));
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<?> lockedExceptionHandler(LockedException ex, WebRequest request) {
        GenericResponse response = GenericResponse.error(ex.getMessage());
        response.setAdditionalInformation(Collections.singletonMap("requestUrl", request.getDescription(false)));
        return new ResponseEntity<>(response, HttpStatus.LOCKED);
    }

    @ExceptionHandler(GeneralValidationException.class)
    public ResponseEntity<?> validationExceptionHandler(GeneralValidationException ex, WebRequest request) {
        GenericResponse response = GenericResponse.error(ex.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("requestUrl", request.getDescription(false));
        map.put("Validation errors", ex.getValidationErrors());
        response.setAdditionalInformation(map);
        StringBuilder log = new StringBuilder();
        log.append("Cannot validate instance of ").append(ex.getClazz().getName()).append(" class\n");
        ex.getValidationErrors().forEach(err -> {
            log.append(" with message: ").append(err).append("\n");
        });
        logger.warn(log.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String getExceptionString(Exception ex) {
        StringBuilder builder = new StringBuilder();
        for (StackTraceElement traceElement : ex.getStackTrace())
            builder.append("\n\tat ").append(traceElement);
        return builder.toString();
    }
}
