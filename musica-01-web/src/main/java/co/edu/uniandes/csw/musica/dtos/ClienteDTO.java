/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.ClienteEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author la.herrera11
 */
@XmlRootElement
public class ClienteDTO implements Serializable{
    
     private Long id;
    private String nombre;
    private String clave;
    private boolean registrado;
    
     public ClienteDTO(ClienteEntity cliente)
    {
        if(cliente != null)
        {
            this.id = cliente.getId();
            this.nombre = cliente.getNombre();
            this.clave = cliente.getClave();
            this.registrado = cliente.getRegistrado();
        }
    }
     
      public ClienteDTO() {
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
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    public boolean getRegistrado() {
        return registrado;
    }

    public void setRegistrado(boolean registrado) {
        this.registrado = registrado;
    }
}
