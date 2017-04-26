/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que modela un usuario que puede ser cliente o administrador del
 * sistema de conciertos.
 */
@Entity
public class UsuarioEntity implements Serializable
{
    /**
     * Numero del ID del usuario en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PodamExclude
    private Long id;
    
    /**
     * Nombre del usuario.
     */
    private String nombre;
    
    /**
     * Clave del usuario.
     */
    private String clave;
    
    /**
     * Boolean que indica si el usuario es administrador o cliente,
     * si es true el usuario es administrador.
     */
    private boolean esAdmin;
    
    /**
     * Lista de festivales que maneja el administrador.
     */
    @ManyToMany
    @PodamExclude
    private List<FestivalEntity> festivalesUsuario;

    /**
     * Lista de boletas que pertenecen al usuario.
     */
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @PodamExclude
    private List<BoletaEntity> boletas;

    /**
     * Método constructor vacío.
     */
    public UsuarioEntity() {
    }

    /**
     * Método para definir la clave del usuario.
     * @param clave Clave del usuario.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Método para conseguir la clave del usuario.
     * @return Clave del usuario.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Método para definir el id del usuario.
     * @param id Id del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método para conseguir el id del usuario.
     * @return Id del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método para definir el nombre del usuario.
     * @param nombre Nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para conseguir el nombre del usuario.
     * @return Nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para definir la lista de festivales de los que esta encargado
     * el administrador.
     * @param festivales Lista de festivales.
     */
    public void setFestivales(List<FestivalEntity> festivales) {
        this.festivalesUsuario = festivales;
    }

    /**
     * Método para conseguir la lista de festivales de los que esta
     * encargado el administrador.
     * @return Lista de festivales.
     */
    public List<FestivalEntity> getFestivales() {
        return festivalesUsuario;
    }

    /**
     * Método para conseguir la lista de boletas del usuario.
     * @return Lista de boletas.
     */
    public List<BoletaEntity> getBoletas() {
        return boletas;
    }
    
    /**
     * Método para definir la lista de boletas del usuario.
     * @param boletas Lista de boletas. 
     */
    public void setBoletas(List<BoletaEntity> boletas) {
        this.boletas = boletas;
    }

    /**
     * Método para definir si el usuario es administrador o no.
     * @param esAdmin Boolean para determinar si el usuario es administrador
     * o no.
     */
    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    /**
     * Método para determinar si un usuario es administrador o no.
     * @return True si el usario es administrador y false de lo contrario.
     */
    public boolean getEsAdmin() {
        return esAdmin;
    }
}