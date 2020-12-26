package bmnsouza.exception;

import java.lang.reflect.UndeclaredThrowableException;
import java.time.DateTimeException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.fasterxml.jackson.databind.JsonMappingException;

import bmnsouza.exception.ServiceException;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class HandleException {

	@Autowired
	private ResultUtil resultUtil;

	@ExceptionHandler(AsyncRequestTimeoutException.class)
	public ResponseEntity<EntidadeResult> handleAsyncRequestTimeout(AsyncRequestTimeoutException arte) {
		log.error("handleAsyncRequestTimeout", arte);
		return resultUtil.resultErro(HttpStatus.SERVICE_UNAVAILABLE, arte, "O tempo limite de resposta do servidor foi excedido. Tente novamente mais tarde.");
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<EntidadeResult> handleBind(BindException be) {
		log.error("handleBind", be);
		return resultUtil.resultErro(HttpStatus.INTERNAL_SERVER_ERROR, be, ResultUtil.MENSAGEM_ERRO);
	}

	/**
	 * Captura exceções do RequestParam 
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<EntidadeResult> handleConstraintViolation(ConstraintViolationException cve) {
		StringBuilder msgUsuario = new StringBuilder();
		for (ConstraintViolation<?> violation : cve.getConstraintViolations()) {
			msgUsuario.append(violation.getPropertyPath().toString().split("[.]")[1]).append(": ").append(violation.getMessage());
			break;
		}
		return resultUtil.resultErro(HttpStatus.BAD_REQUEST, msgUsuario.toString());
	}

	@ExceptionHandler(ConversionNotSupportedException.class)
	public ResponseEntity<EntidadeResult> handleConversionNotSupported(ConversionNotSupportedException cnse) {
		log.error("handleConversionNotSupported", cnse);
		return resultUtil.resultErro(HttpStatus.INTERNAL_SERVER_ERROR, cnse, ResultUtil.MENSAGEM_ERRO);
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<EntidadeResult> handleDataAccess(DataAccessException dae) {
		return resultUtil.resultErro(HttpStatus.BAD_REQUEST, dae.getRootCause().getMessage(), ResultUtil.MENSAGEM_ERRO);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<EntidadeResult> handleDataIntegrityViolation(DataIntegrityViolationException dive) {
		return resultUtil.resultErro(HttpStatus.CONFLICT, dive.getRootCause().getMessage(), ResultUtil.MENSAGEM_ERRO);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<EntidadeResult> handleException(Exception ex) {
		log.error("handleException", ex);
		return resultUtil.resultErro(HttpStatus.INTERNAL_SERVER_ERROR, ex, ResultUtil.MENSAGEM_ERRO);
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<EntidadeResult> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException hmtnae) {
		log.error("handleHttpMediaTypeNotAcceptable", hmtnae);
		return resultUtil.resultErro(HttpStatus.INTERNAL_SERVER_ERROR, hmtnae, ResultUtil.MENSAGEM_ERRO);
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<EntidadeResult> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException hmtnse) {
		StringBuilder msgUsuario = new StringBuilder("O tipo de mídia ").append(hmtnse.getContentType()).append(" não é suportado. Tipo de mídia suportado: ")
			.append(hmtnse.getSupportedMediaTypes().get(0));
		return resultUtil.resultErro(HttpStatus.UNSUPPORTED_MEDIA_TYPE, msgUsuario.toString());
	}

	/**
	 * Captura exceções do RequestBody
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<EntidadeResult> handleHttpMessageNotReadable(HttpMessageNotReadableException hmnre, JsonMappingException jme) throws Throwable {
		StringBuilder msgUsuario = new StringBuilder(jme.getPath().get(0).getFieldName()).append(": ");
		
		try {
			throw hmnre.getRootCause();
		} catch (DateTimeException dte) {
			return resultUtil.resultErro(HttpStatus.BAD_REQUEST, msgUsuario.append("data inválida").toString());
		} catch (Exception ex) {
			return resultUtil.resultErro(HttpStatus.BAD_REQUEST, msgUsuario.append("valor inválido").toString());
		}
	}
	
	@ExceptionHandler(HttpMessageNotWritableException.class)
	public ResponseEntity<EntidadeResult> handleHttpMessageNotWritable(HttpMessageNotWritableException hmnwe) {
		log.error("handleHttpMessageNotWritable", hmnwe);
		return resultUtil.resultErro(HttpStatus.INTERNAL_SERVER_ERROR, hmnwe, ResultUtil.MENSAGEM_ERRO);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<EntidadeResult> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException hrmnse) {
		StringBuilder msgUsuario = new StringBuilder("O método ").append(hrmnse.getMethod()).append(" não é suportado para esta solicitação. Método suportado: ")
			.append(hrmnse.getSupportedMethods()[0]);
		return resultUtil.resultErro(HttpStatus.METHOD_NOT_ALLOWED, msgUsuario.toString());
	}
	
	/**
	 * Captura exceções do RequestBody
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<EntidadeResult> handleMethodArgumentNotValid(MethodArgumentNotValidException manve) {
		StringBuilder msgUsuario = new StringBuilder(manve.getBindingResult().getFieldError().getField()).append(": ")
			.append(manve.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
		return resultUtil.resultErro(HttpStatus.BAD_REQUEST, msgUsuario.toString());
	}

	/**
	 * Captura exceções do RequestParam 
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<EntidadeResult> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException matme) throws Throwable {
		StringBuilder msgUsuario = new StringBuilder(matme.getName()).append(": ");
		
		try {
			throw matme.getRootCause();
		} catch (DateTimeException dte) {
			return resultUtil.resultErro(HttpStatus.BAD_REQUEST, msgUsuario.append("data inválida").toString());
		} catch (Exception ex) {
			return resultUtil.resultErro(HttpStatus.BAD_REQUEST, msgUsuario.append("deve ser do tipo ").append(matme.getRequiredType().getName()).toString());
		}
	}

	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<EntidadeResult> handleMissingPathVariable(MissingPathVariableException mpve) {
		log.error("handleMissingPathVariable", mpve);
		return resultUtil.resultErro(HttpStatus.INTERNAL_SERVER_ERROR, mpve, ResultUtil.MENSAGEM_ERRO);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<EntidadeResult> handleMissingServletRequestParameter(MissingServletRequestParameterException msrpe) {
		return resultUtil.resultErro(HttpStatus.BAD_REQUEST, new StringBuilder(msrpe.getParameterName()).append(": parâmetro não encontrado").toString());
	}
	
	@ExceptionHandler(MissingServletRequestPartException.class)
	public ResponseEntity<EntidadeResult> handleMissingServletRequestPart(MissingServletRequestPartException msrpe) {
		log.error("handleMissingServletRequestPart", msrpe);
		return resultUtil.resultErro(HttpStatus.INTERNAL_SERVER_ERROR, msrpe, ResultUtil.MENSAGEM_ERRO);
	}
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<EntidadeResult> handleService(ServiceException se) {
		return resultUtil.resultErro(HttpStatus.BAD_REQUEST, se.getMessage());
	}

	@ExceptionHandler(ServletRequestBindingException.class)
	public ResponseEntity<EntidadeResult> handleServletRequestBinding(ServletRequestBindingException srbe) {
		log.error("handleServletRequestBinding", srbe);
		return resultUtil.resultErro(HttpStatus.INTERNAL_SERVER_ERROR, srbe, ResultUtil.MENSAGEM_ERRO);
	}

	@ExceptionHandler(TypeMismatchException.class)
	public ResponseEntity<EntidadeResult> handleTypeMismatch(TypeMismatchException tme) {
		log.error("handleTypeMismatch", tme);
		return resultUtil.resultErro(HttpStatus.INTERNAL_SERVER_ERROR, tme, ResultUtil.MENSAGEM_ERRO);
	}

	@ExceptionHandler(UndeclaredThrowableException.class)
	public ResponseEntity<EntidadeResult> handleUndeclaredThrowable(UndeclaredThrowableException ute) {
		return resultUtil.resultErro(HttpStatus.BAD_REQUEST, ute.getCause(), ResultUtil.MENSAGEM_ERRO);
	}

}