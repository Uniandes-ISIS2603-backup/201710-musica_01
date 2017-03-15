/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


/**
 *
 * @author c.potdevin10
 */

@Entity
public class CiudadEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    @OneToMany(mappedBy = "ciudadLugar", cascade = CascadeType.ALL)
    private List<LugarEntity> lugaresCiudad;
    
    @ManyToMany(mappedBy = "ciudadesFestival", cascade = CascadeType.ALL)
    private List<FestivalEntity> festivalesCiudad;
    
    
    
    public CiudadEntity() {
        
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

    public List<LugarEntity> getLugaresCiudad() {
        return lugaresCiudad;
    }

    public void setLugaresCiudad(List<LugarEntity> lugaresCiudad) {
        this.lugaresCiudad = lugaresCiudad;
    }

    public List<FestivalEntity> getFestivalesCiudad() {
        return festivalesCiudad;
    }

    public void setFestivalesCiudad(List<FestivalEntity> festivalesCiudad) {
        this.festivalesCiudad = festivalesCiudad;
    }
   
    
    
}
