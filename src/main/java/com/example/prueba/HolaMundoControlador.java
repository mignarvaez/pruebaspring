
package com.example.prueba;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que representa el controlador de la aplicación
 * Se le asigna la ruta /hola, recibe cualquier MimeType y
 * genera una respuesta de tipo json
 * La ruta de la clase (/hola) la reciben las funciones de la clase
 * @author Migue
 *
 */
@RestController
@RequestMapping(value = "/hola",
consumes = MediaType.ALL_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
public class HolaMundoControlador {

	/**
	 * Se retorna un ResponseEntity al realizar la petición a la ruta /mundo
	 * Que contiene el mensaje y el codigo http en la respuesta
	 * La ruta /mundo se concatena con la de la clase (/hola)
	 * La petición que recibe es de tipo get
	 * @return El mensaje y codigo http de la respuesta
	 */
	@RequestMapping(value="/mundo", method = RequestMethod.GET)
	public ResponseEntity<Map<String,String>> index(){
		//Este método trabaja con HashMap que son una implementacion
		//de la interfaz map, la cuál es un tipo de colección que permite
		//almacenar valores en formato clave-valor
		HashMap<String, String> output = new HashMap<>();
		output.put("mensaje","Hola mundo");
		return new ResponseEntity<>(output, HttpStatus.OK);
	}
	
	/**
	 * Ejemplo no tipico de una petición de tipo post
	 * @param helloBody el tipo de body o cuerpo que se espera de la petición
	 * @return Una respuesta teniendo en cuenta el cuerpo de la petición
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> greetFromBody(@RequestBody HelloBody helloBody) {
		
		//Se trabaja con HashMap, se obtiene un atributo del objeto que representa el cuerpo de una petición POST
		//Y se  retorna una respuesta.
		HashMap<String, String> output = new HashMap<>();
		output.put("mensaje", "Hola " + helloBody.getName());
		return new ResponseEntity<>(output, HttpStatus.OK);
	}
	
	/**
	 * Gestiona la respuesta a una petición que usa una variable en su url y un parametro de consulta en la misma.
	 * Ejemplo petición: http://localhost:8080/hola/miguel?amount_exclamation=4
	 * @param name La variable de la url asociada al nombre de una persona
	 * @param amount_exclamation Un paramétro de consulta, no obligatorio y que tiene un valor por defecto de 0
	 * @return Una respuesta compuesta de un responseentity que usa un hashmap y un mensaje de status http.
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> greet(@PathVariable String name,
			@RequestParam(required = false,
			defaultValue = "0") int amount_exclamation) {
		
		//Se trabaja con hashmap
		HashMap<String, String> output = new HashMap<>();
		//StringBuilder es un clase similar a una String, pero de tipo mutable e indexada por cada caracter
		//Se debe inicializar con su constructor.
		StringBuilder b = new StringBuilder("Hola ");
		//Se le agrega el nombre que llega por parametro
		b.append(name);
		//Se agrega una cantidad de signos de exclamación según el parámetro de consulta indicado
		for (int i = 0; i < amount_exclamation; i++) {
			b.append("!");
		}
		output.put("mensaje", b.toString());
		return new ResponseEntity<>(output, HttpStatus.OK);
	}
}

/**
 * Clase que representa la estructura del body de ejemplo a usar
 * @author Migue
 *
 */
class HelloBody {
	String name;

	public HelloBody() {
		// Used by Jackson
	}

	public String getName() {
		return this.name;
	}
}
