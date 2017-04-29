/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import co.edu.uniandes.csw.musica.entities.LugarEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author c.potdevin10
 */
@XmlRootElement
public class LugarDTO implements Serializable{
    
    private Long id;
    private String nombre;
    private Boolean abierto;
    private Integer costoPreferencial;
    private Integer costoEconomico;
    private CiudadEntity ciudadLugar;
    
    /**
     *
     * @param lugar
     */
    public LugarDTO(LugarEntity lugar) {
        if (lugar != null) {
            this.id = lugar.getId();
            this.nombre = lugar.getNombre();
            this.abierto = lugar.getAbierto();
            this.costoPreferencial = lugar.getCostoPreferencial();
            this.costoEconomico = lugar.getCostoEconomico();
            this.ciudadLugar = lugar.getCiudadLugar();
        }
    }
    
    /**
     *
     * @return
     */
    public LugarEntity toEntity() {
        LugarEntity lugar = new LugarEntity();
        lugar.setId(this.id);
        lugar.setNombre(this.nombre);
        lugar.setAbierto(this.abierto);
        lugar.setCostoPreferencial(this.costoPreferencial);
        lugar.setCostoEconomico(this.costoEconomico);
        lugar.setCiudadLugar(ciudadLugar);
        return lugar;
    }
    
    /**
     *
     */
    public LugarDTO() {
        
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
