package com.example.prueba;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Clase encargada dar respuestas a errores de manera personalizada
 * @author Migue
 *
 */
@ControllerAdvice
public class MyApplicationControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleBadRequest() {
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFound() {
    }
    
    public class ApiException extends Exception {
    }

    public class NotFoundException extends ApiException {
    }
    
    /**
     * Se crea una ruta exception de prueba y se asocia un request mapping correspondiente al tipo de error esperado
     * @author Migue
     *
     */
    @RestController
    @RequestMapping(value = "/exception")
    public class ExceptionController {
    	
        @RequestMapping(value = "/404", method = RequestMethod.GET)
        public ResponseEntity<Map<String, String>> notFound() throws NotFoundException {
            throw new NotFoundException();
        }    

        @RequestMapping(value = "/400", method = RequestMethod.GET)
        public ResponseEntity<Map<String, String>> badRequest() throws ApiException {
            throw new ApiException();
        }

        @RequestMapping(value = "/500", method = RequestMethod.GET)
        public ResponseEntity<Map<String, String>> ise() throws Exception {
            throw new Exception();
        }
    }
}

