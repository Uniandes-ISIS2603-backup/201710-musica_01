// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class GeneroDTO implements Serializable
{

    private Long id;
    private String nombre;
    
    public GeneroDTO(GeneroEntity genero)
    {
        if(genero != null)
        {
            this.id = genero.getId();
            this.nombre = genero.getNombre();
        }
    }
    
    public GeneroEntity toEntity()
    {
        GeneroEntity genero = new GeneroEntity();
        genero.setId(id);
        genero.setNombre(nombre);
        return genero;
    }
    
    public GeneroDTO() {
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
    
}
