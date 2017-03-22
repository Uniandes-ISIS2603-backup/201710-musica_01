// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author la.herrera11
 */
@XmlRootElement
public class BoletaDTO implements Serializable {
    
    private Long id;
    private Integer precio;
   
    public BoletaDTO(BoletaEntity boleta)
    {
        if(boleta != null)
        {
            this.id = boleta.getId();
            this.precio = boleta.getPrecio();
        }
    }
    
    public BoletaEntity toEntity()
    {
        BoletaEntity boleta = new BoletaEntity();
        boleta.setId(id);
        boleta.setPrecio(precio);
        return boleta;
    }
    
    public BoletaDTO() {
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
}
