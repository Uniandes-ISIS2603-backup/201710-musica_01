/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    public LugarDTO() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAbierto() {
        return abierto;
    }

    public void setAbierto(Boolean abierto) {
        this.abierto = abierto;
    }

    public Integer getCostoPreferencial() {
        return costoPreferencial;
    }

    public void setCostoPreferencial(Integer costoPreferencial) {
        this.costoPreferencial = costoPreferencial;
    }

    public Integer getCostoEconomico() {
        return costoEconomico;
    }

    public void setCostoEconomico(Integer costoEconomico) {
        this.costoEconomico = costoEconomico;
    }

    public CiudadEntity getCiudadLugar() {
        return ciudadLugar;
    }

    public void setCiudadLugar(CiudadEntity ciudadLugar) {
        this.ciudadLugar = ciudadLugar;
    }
}
