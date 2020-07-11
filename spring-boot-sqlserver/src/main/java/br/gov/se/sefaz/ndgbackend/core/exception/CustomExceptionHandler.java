package br.gov.se.sefaz.ndgbackend.core.exception;

import java.lang.reflect.UndeclaredThrowableException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.core.result.UtilResult;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  private UtilResult utilResult = UtilResult.getInstance();

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    StringBuilder msgUsuario = new StringBuilder(ex.getBindingResult().getFieldError().getField()).append(": ")
      .append(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());

    EntidadeResult result = utilResult.entidadeResult(HttpStatus.BAD_REQUEST, msgUsuario.toString());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status,
    WebRequest request) {
    StringBuilder msgUsuario = new StringBuilder("O método ").append(ex.getMethod())
      .append(" não é suportado para esta solicitação. Método suportado: ").append(ex.getSupportedMethods()[0]);

    EntidadeResult result = utilResult.entidadeResult(HttpStatus.METHOD_NOT_ALLOWED, msgUsuario.toString());
    return new ResponseEntity<Object>(result, new HttpHeaders(),HttpStatus.METHOD_NOT_ALLOWED);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status,
    WebRequest request) {
    StringBuilder msgUsuario = new StringBuilder("O tipo de mídia ").append(ex.getContentType())
      .append(" não é suportado. Tipo de mídia suportado: ").append(ex.getSupportedMediaTypes().get(0));

    EntidadeResult result = utilResult.entidadeResult(HttpStatus.UNSUPPORTED_MEDIA_TYPE, msgUsuario.toString());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status,
    WebRequest request) {
    StringBuilder msgUsuario = new StringBuilder("Parâmetro '").append(ex.getParameterName()).append("' não encontrado");
    EntidadeResult result = utilResult.entidadeResult(HttpStatus.BAD_REQUEST, msgUsuario.toString());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
    WebRequest request) {
    StringBuilder msgUsuario = new StringBuilder(ex.getName()).append(": deve ser do tipo ").append(ex.getRequiredType().getName());
    EntidadeResult result = utilResult.entidadeResult(HttpStatus.BAD_REQUEST, msgUsuario.toString());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ ConstraintViolationException.class })
  public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
    StringBuilder msgUsuario = new StringBuilder();
    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      msgUsuario.append(violation.getPropertyPath().toString().split("[.]")[2]).append(": ").append(violation.getMessage());
      break;
    }

    EntidadeResult result = utilResult.entidadeResult(HttpStatus.BAD_REQUEST, msgUsuario.toString());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ TransactionAbortException.class })
  public ResponseEntity<Object> handleTransactionAbort(TransactionAbortException ex) {
    EntidadeResult result = utilResult.entidadeResult(HttpStatus.BAD_REQUEST, ex.getMessage());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ DataIntegrityViolationException.class })
  public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
    EntidadeResult result = utilResult.entidadeResult(HttpStatus.CONFLICT, ex.getMostSpecificCause(), utilResult.MSG_DEFAULT_ERRO);
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler({ DataAccessException.class })
  public ResponseEntity<Object> handleDataBase(DataAccessException ex) {
    log.error("handleDataBase", ex);
    EntidadeResult result = utilResult.entidadeResult(HttpStatus.BAD_REQUEST, ex.getMostSpecificCause(), utilResult.MSG_DEFAULT_ERRO);
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ UndeclaredThrowableException.class })
  public ResponseEntity<Object> handleUndeclaredThrowable(UndeclaredThrowableException ex) {
    EntidadeResult result = utilResult.entidadeResult(HttpStatus.BAD_REQUEST, ex.getCause(), utilResult.MSG_DEFAULT_ERRO);
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ Exception.class })
  public ResponseEntity<Object> handleAll(Exception ex) {
    log.error("handleAll", ex);
    EntidadeResult result = utilResult.entidadeResult(HttpStatus.INTERNAL_SERVER_ERROR, ex, utilResult.MSG_DEFAULT_ERRO);
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}