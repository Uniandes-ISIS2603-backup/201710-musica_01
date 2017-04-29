/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jc.bustamante143
 */
@XmlRootElement

public class GeneroDTO implements Serializable
{

    private Long id;
    private String nombre;
    
    /**
     *
     * @param genero
     */
    public GeneroDTO(GeneroEntity genero)
    {
        if(genero != null)
        {
            this.id = genero.getId();
            this.nombre = genero.getNombre();
        }
    }
    
    /**
     *
     * @return
     */
    public GeneroEntity toEntity()
    {
        GeneroEntity genero = new GeneroEntity();
        genero.setId(id);
        genero.setNombre(nombre);
        return genero;
    }
    
    /**
     *
     */
    public GeneroDTO() {
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
