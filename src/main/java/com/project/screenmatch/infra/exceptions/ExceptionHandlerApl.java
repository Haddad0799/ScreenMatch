package com.project.screenmatch.infra.exceptions;

import com.project.screenmatch.model.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerApl {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerApl.class);

    private ResponseEntity<ApiErrorResponse> buildErrorResponse(HttpStatus status, String message, HttpServletRequest request) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiErrorResponse, status);
    }

    @ExceptionHandler({IllegalArgumentException.class,
            })
    public ResponseEntity<ApiErrorResponse> handleBadRequestExceptions(RuntimeException ex, HttpServletRequest request) {
        logger.error("Erro BAD_REQUEST: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler({TituloNotFoundException.class,
    DadoOmdbTituloTipoNullException.class})
    public ResponseEntity<ApiErrorResponse> handleNotFoundExceptions(RuntimeException ex, HttpServletRequest request) {
        logger.error("Erro NOT_FOUND: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler({ErroNaSerializacaoDosDados.class
    })
    public ResponseEntity<ApiErrorResponse> handleInternalServerErrorExceptions(RuntimeException ex, HttpServletRequest request) {
        logger.error("Erro INTERNAL_SERVER_ERROR: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
    }

    @ExceptionHandler({ErroComunicacaoApiOmdb.class})
    public ResponseEntity<ApiErrorResponse> handleErroDeComunicacaoApiExternaExceptions(RuntimeException ex, HttpServletRequest request) {
        logger.error("SERVICE_UNAVAILABLE: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage(), request);
    }

    @ExceptionHandler({TituloNotPresentException.class})
    public ResponseEntity<ApiErrorResponse> handleNoContentExceptions(RuntimeException ex, HttpServletRequest request) {
        logger.error("Erro NO_CONTENT: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NO_CONTENT, ex.getMessage(), request);
    }

}
