/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class BoletaEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PodamExclude
    private Long id;
        @Min(1)
        @Max(100000) 
        @PodamExclude
    private Integer precio;
        @ManyToOne
        @PodamExclude
    private UsuarioEntity cliente;
        @ManyToOne
        @PodamExclude
    private FuncionEntity funcion;
    

    public BoletaEntity() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrecio() {
        return precio;
    }
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
    
    public UsuarioEntity getCliente(){
    return cliente;
    }
    public void setCliente(UsuarioEntity cliente){
    this.cliente= cliente;
    }
    
    public FuncionEntity getFuncion(){
    return funcion;
    }
    public void setFuncion(FuncionEntity funcion){
    this.funcion= funcion;
    }
}
