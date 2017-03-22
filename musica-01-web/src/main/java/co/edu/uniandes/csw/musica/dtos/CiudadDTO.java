// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    public CiudadDTO(CiudadEntity ciudad) {
        if (ciudad != null) {
            this.id = ciudad.getId();
            this.nombre = ciudad.getNombre();
        }
    }
    
    public CiudadEntity toEntity() {
        CiudadEntity ciudad = new CiudadEntity();
        ciudad.setId(this.id);
        ciudad.setNombre(this.nombre);
        return ciudad;
    }
    
    public CiudadDTO() {
        
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
