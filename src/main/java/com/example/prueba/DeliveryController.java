package com.example.prueba;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.prueba.MyApplicationControllerAdvice.ApiException;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * Clase encargada de controlar la interaccion con el modelo delivery
 * @author Migue
 *
 */
@RestController
@RequestMapping(value = "/delivery",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class DeliveryController {
	
	/**
	 * El repositorio creado para gestionar deliveries
	 */
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryController(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }
    
    /**
     * Un metodo post para agregar un nuevo delivery
     * @param delivery el nuevo delivery a agregar
     * @return el delivery agregado y un mensaje de ok
     * @throws ApiException una excepción arrojada si no logra crearse el delivery
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Delivery> post(@Valid @RequestBody Delivery delivery) throws ApiException {
        try {
            delivery = deliveryRepository.save(delivery);
        } catch (Exception e) {
            throw new ApiException();
        }
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }
    
    /**
     * Metodo creado para obtener un delivery segun su id
     * @param id identificaicon del delivery
     * @return el delivery segun su id
     * @throws ApiException si no se encuentra retorna una excepción de tipo notfound
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Delivery> get(@PathVariable long id) throws ApiException {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if (delivery.isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(delivery.get(), HttpStatus.OK);
    }
    
    /**
     * Request que retorna una pagina completa del dato solicitado
     * @param page la página deseada
     * @return el resultado de la pagina buscada
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<Delivery>> index(@RequestParam(required = false, defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 50);
        return new ResponseEntity<>(deliveryRepository.findAll(pageable), HttpStatus.OK);
    }
    
    public class ApiException extends Exception {
    }

    public class NotFoundException extends ApiException {
    }
    
}