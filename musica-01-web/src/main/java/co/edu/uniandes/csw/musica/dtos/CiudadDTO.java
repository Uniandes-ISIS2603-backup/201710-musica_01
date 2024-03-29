/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author c.potdevin10
 */
@XmlRootElement
public class CiudadDTO implements Serializable{
    
    private Long id;
    private String nombre;
    
    /**
     *
     * @param ciudad
     */
    public CiudadDTO(CiudadEntity ciudad) {
        if (ciudad != null) {
            this.id = ciudad.getId();
            this.nombre = ciudad.getNombre();
        }
    }
    
    /**
     *
     * @return
     */
    public CiudadEntity toEntity() {
        CiudadEntity ciudad = new CiudadEntity();
        ciudad.setId(this.id);
        ciudad.setNombre(this.nombre);
        return ciudad;
    }
    
    /**
     *
     */
    public CiudadDTO() {
        
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
}
