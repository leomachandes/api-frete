package br.com.trabalhofinal.trabalhofinal.controller;

import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

import br.com.trabalhofinal.trabalhofinal.exception.AtributoObrigatorioException;
import br.com.trabalhofinal.trabalhofinal.exception.ConverterException;
import br.com.trabalhofinal.trabalhofinal.exception.RegistroNaoEncontradoException;

@ControllerAdvice
@RestControllerAdvice
public class HandlerErrorDefault {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, Object> handleMsgNotReadableExceptions(){
		return criarMapDeErro(HttpStatus.UNPROCESSABLE_ENTITY, 
				"O corpo (body) da requisição possui erros ou não existe");
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AtributoObrigatorioException.class)
	public Map<String, Object> handleAttrObrigatorioExceptions(AtributoObrigatorioException aoe){
		return criarMapDeErro(HttpStatus.UNPROCESSABLE_ENTITY, aoe.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidDefinitionException.class)
	public Map<String, Object> handleInvalidDefExceptions(InvalidDefinitionException ide){

		JSONObject erros = new JSONObject();

		JSONArray msgs = new JSONArray();

		String atributo = ide.getPath().get(ide.getPath().size() - 1).getFieldName();

		msgs.put(new JSONObject("{ atributo: " + atributo 
        		+ ", mensagem: \"Formato do atributo inválido\" }"));

	    erros.put("codigo", HttpStatus.UNPROCESSABLE_ENTITY.value());

	    erros.put("erros", msgs);

	    return erros.toMap();

	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public Map<String, Object> handleMethodArgumentTypeMismatchExceptions(
			MethodArgumentTypeMismatchException matme){
		
		JSONObject erros = new JSONObject();

		JSONArray msgs = new JSONArray();

		String atributo = matme.getParameter().getParameterName();

		msgs.put(new JSONObject("{ atributo: " + atributo 
        		+ ", mensagem: \"Tipo do atributo diferente do esperado\" }"));

	    erros.put("codigo", HttpStatus.UNPROCESSABLE_ENTITY.value());

	    erros.put("erros", msgs);

	    return erros.toMap();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingPathVariableException.class)
	public Map<String, Object> handleMissingPathVariableExceptions(
			MissingPathVariableException mpve){
		
		JSONObject erros = new JSONObject();

		JSONArray msgs = new JSONArray();

		String atributo = mpve.getParameter().getParameterName();

		msgs.put(new JSONObject("{ atributo: " + atributo 
        		+ ", mensagem: \"Parâmetro ausente da url\" }"));

	    erros.put("codigo", HttpStatus.PRECONDITION_FAILED.value());

	    erros.put("erros", msgs);

	    return erros.toMap();
	}	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, Object> handleConstraintExceptions(ConstraintViolationException cve){

		JSONArray msgs = new JSONArray();
		
		if (cve.getConstraintViolations() != null && !cve.getConstraintViolations().isEmpty()) {
			
			cve.getConstraintViolations().forEach((error) -> {
				
				String[] paths = error.getPropertyPath().toString().split("\\.");
				String atributo = paths[paths.length - 1];
				
				String errorMessage = error.getMessage();
				
				JSONObject erroJson = new JSONObject();
				erroJson.put("atributo", atributo);
				erroJson.put("mensagem", errorMessage);
				
				msgs.put(erroJson);
				
			});
			
			JSONObject erros = new JSONObject();
		    erros.put("codigo", HttpStatus.UNPROCESSABLE_ENTITY.value());
			erros.put("erros", msgs);

		    return erros.toMap();
			
		}else {
			
			return criarMapDeErro(HttpStatus.UNPROCESSABLE_ENTITY, cve.getMessage());
			
		}	  

	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IllegalArgumentException.class)
	public Map<String, Object> handleIllegalArgumentExceptions(
			IllegalArgumentException iae){
	    return criarMapDeErro(HttpStatus.INTERNAL_SERVER_ERROR, iae.getMessage());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public Map<String, Object> handlePSQLExceptions(
			DataIntegrityViolationException dve){
	    return criarMapDeErro(HttpStatus.INTERNAL_SERVER_ERROR, 
	    		"Ocorreu um erro de integridade na base de dados");
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public Map<String, Object> handleNullPointerExceptions(
			NullPointerException npe){
	    return criarMapDeErro(HttpStatus.INTERNAL_SERVER_ERROR, 
	    		"Ocorreu um erro de ponteiro nulo no processamento do servidor");
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RegistroNaoEncontradoException.class)
	public Map<String, Object> handleRegNaoEncontradoExceptions(RegistroNaoEncontradoException rnee){
		return criarMapDeErro(HttpStatus.NOT_FOUND, rnee.getMessage());
	}		

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConverterException.class)
	public Map<String, Object> handleConverterExceptions(ConverterException ce){		
		return criarMapDeErro(HttpStatus.UNPROCESSABLE_ENTITY, ce.getMessage());
	}

	private Map<String, Object> criarMapDeErro(HttpStatus status, String msgDeErro){
		
		JSONObject erro = new JSONObject();
		erro.put("codigo", status.value());
		
		JSONObject detalhe = new JSONObject();
		detalhe.put("mensagem", msgDeErro);
		
		
		JSONArray detalhes = new JSONArray();
		detalhes.put(detalhe);
		
		erro.put("erros", detalhes);
		
		return erro.toMap();
		
	}
	
}
