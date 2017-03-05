/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author jc.bustamante143
 */

@Entity
public class MusicoEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String trayectoria;
    private int requerimientoSonido;
    private int requerimientoCapacidad;

    public MusicoEntity() {
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
