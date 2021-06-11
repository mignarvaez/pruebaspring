package com.example.prueba;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Clase delivery que representa un modelo
 * Todos los atributos publicos se convierten automaticamente en JSON
 * @author Migue
 *
 */
@Entity
@Table(name = "delivery")
public class Delivery {
	
	/**
	 * ID Autogenerada
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    
    /**
     * El estado de la entrega
     */
    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    DeliveryState state;
    
    /**
     * La localización de la entrega
     */
    @Column(nullable = false)
    @NotNull
    String location;

    public Delivery() {
        // Used by Jackson2
    }
    
    /**
     * Constructor de la clase
     * @param state estado del delivery
     * @param location localizacion del delivery
     */
    public Delivery(@NotNull DeliveryState state, @NotNull String location) {
        this.state = state;
        this.location = location;
    }
    
    /**
     * Retorna la id del delivery
     * @return la id del delivery
     */
    public long getId() {
        return id;
    }

    /**
     * Retorna el estado del delivery
     * @return el estado del delivery
     */
    public DeliveryState getState() {
        return state;
    }
    
    /**
     * Establece el estado del delivery
     * @param state el nuevo estado del delivery
     */
    public void setState(DeliveryState state) {
        this.state = state;
    }
    
    /**
     * Retorna la localizacion del delivery
     * @return la localizacion del delivery
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * Establece la localización del delivery
     * @param location la nueva localización del delivery
     */
    public void setLocation(String location) {
        this.location = location;
    }
}

/**
 * Los posibles estados del delivery en un enum
 * @author Migue
 *
 */
enum DeliveryState {
    PENDING, DELIVERING, WAITING_AT_ARRIVAL, RETURNING, RETURNED, PICKED_UP;
}