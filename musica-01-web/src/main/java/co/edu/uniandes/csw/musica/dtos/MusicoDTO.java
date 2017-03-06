/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class MusicoDTO implements Serializable
{

    private Long id;    
    private String nombre;
    private String trayectoria;
    private int requerimientoSonido;
    private int requerimientoCapacidad;
    
    public MusicoDTO(MusicoEntity musico)
    {
        if(musico !=null)
        {
            this.id = musico.getId();
            this.nombre = musico.getNombre();
            this.trayectoria = musico.getTrayectoria();
            this.requerimientoCapacidad = musico.getRequerimientoCapacidad();
            this.requerimientoSonido = musico.getRequerimientoSonido();
        }
    }
    
    public MusicoEntity toEntity()
    {
        MusicoEntity musico = new MusicoEntity();
        musico.setId(id);
        musico.setNombre(nombre);
        musico.setTrayectoria(trayectoria);
        musico.setRequerimientoCapacidad(requerimientoCapacidad);
        musico.setRequerimientoSonido(requerimientoSonido);
        return musico;
    }
    
    public MusicoDTO() {
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

    public String getTrayectoria() {
        return trayectoria;
    }

    public void setTrayectoria(String trayectoria) {
        this.trayectoria = trayectoria;
    }

    public int getRequerimientoSonido() {
        return requerimientoSonido;
    }

    public void setRequerimientoSonido(int requerimientoSonido) {
        this.requerimientoSonido = requerimientoSonido;
    }

    public int getRequerimientoCapacidad() {
        return requerimientoCapacidad;
    }

    public void setRequerimientoCapacidad(int requerimientoCapacidad) {
        this.requerimientoCapacidad = requerimientoCapacidad;
    }
    
    
    
}
