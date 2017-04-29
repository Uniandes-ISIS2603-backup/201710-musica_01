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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.bustamante143
 */
@Entity
public class LugarEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PodamExclude
    private Long id;
    
    private String nombre;
    
    private Boolean abierto;
    
    private Integer capacidad;
    
    private Integer capacidadSonido;
    
    private Integer costoPreferencial;
    
    private Integer costoEconomico;
     
    @OneToMany(mappedBy = "lugarFuncion", cascade = CascadeType.ALL)
    @PodamExclude
    private List<FuncionEntity> funcionesLugar;
    
    @ManyToOne
    @PodamExclude
    private CiudadEntity ciudadLugar;
    
    /**
     *
     */
    public LugarEntity() {
        
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public Boolean getAbierto() {
        return abierto;
    }

    /**
     *
     * @param abierto
     */
    public void setAbierto(Boolean abierto) {
        this.abierto = abierto;
    }

    /**
     *
     * @return
     */
    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     *
     * @param capacidad
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    /**
     *
     * @return
     */
    public Integer getCapacidadSonido() {
        return capacidadSonido;
    }

    /**
     *
     * @param capacidadSonido
     */
    public void setCapacidadSonido(Integer capacidadSonido) {
        this.capacidadSonido = capacidadSonido;
    }

    /**
     *
     * @return
     */
    public Integer getCostoPreferencial() {
        return costoPreferencial;
    }

    /**
     *
     * @param costoPreferencial
     */
    public void setCostoPreferencial(Integer costoPreferencial) {
        this.costoPreferencial = costoPreferencial;
    }

    /**
     *
     * @return
     */
    public Integer getCostoEconomico() {
        return costoEconomico;
    }

    /**
     *
     * @param costoEconomico
     */
    public void setCostoEconomico(Integer costoEconomico) {
        this.costoEconomico = costoEconomico;
    }

    /**
     *
     * @return
     */
    public List<FuncionEntity> getFuncionesLugar() {
        return funcionesLugar;
    }

    /**
     *
     * @param funcionesLugar
     */
    public void setFuncionesLugar(List<FuncionEntity> funcionesLugar) {
        this.funcionesLugar = funcionesLugar;
    }

    /**
     *
     * @return
     */
    public CiudadEntity getCiudadLugar() {
        return ciudadLugar;
    }

    /**
     *
     * @param ciudadLugar
     */
    public void setCiudadLugar(CiudadEntity ciudadLugar) {
        this.ciudadLugar = ciudadLugar;
    }
    
}
