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

@Entity
public class BoletaEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer precio;
        @ManyToOne
    private UsuarioEntity cliente;
        @ManyToOne
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
