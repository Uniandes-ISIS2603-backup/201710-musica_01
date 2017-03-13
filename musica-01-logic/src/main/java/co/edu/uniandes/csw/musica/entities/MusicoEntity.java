/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    
    @ManyToOne
    private GeneroEntity generoMusico;

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

    public GeneroEntity getGenero() {
        return generoMusico;
    }

    public void setGenero(GeneroEntity genero) {
        this.generoMusico = genero;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MusicoEntity other = (MusicoEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    
    
}
