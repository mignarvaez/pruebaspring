package com.example.prueba;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Un repositorio usado para exponer el modelo e interactuar con el
 * Este repositorio usa el CrudRepository de spring inicialmente y finalmente el PagingAndSortingRepository para paginación
 * @author Migue
 *
 */
@Repository
public interface DeliveryRepository extends PagingAndSortingRepository 	<Delivery, Long> {
}