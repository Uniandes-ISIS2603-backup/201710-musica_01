/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jc.bustamante143
 */
@XmlRootElement

public class MusicoDTO implements Serializable
{

    private Long id;    
    private String nombre;

    private GeneroEntity generoMusico;
    
    /**
     *
     * @param musico
     */
    public MusicoDTO(MusicoEntity musico)
    {
        if(musico !=null)
        {
            this.id = musico.getId();
            this.nombre = musico.getNombre();
           
            this.generoMusico = musico.getGeneroMusico();
        }
    }
    
    /**
     *
     * @return
     */
    public MusicoEntity toEntity()
    {
        MusicoEntity musico = new MusicoEntity();
        musico.setId(id);
        musico.setNombre(nombre);

        musico.setGeneroMusico(generoMusico);
        return musico;
    }
    
    /**
     *
     */
    public MusicoDTO() {
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
    public GeneroEntity getGenero() {
        return generoMusico;
    }

    /**
     *
     * @param genero
     */
    public void setGenero(GeneroEntity genero) {
        this.generoMusico = genero;
    }
    
}
