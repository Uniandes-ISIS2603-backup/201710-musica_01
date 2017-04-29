/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
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
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author c.potdevin10
 */

@Entity
public class CiudadEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PodamExclude
    private Long id;
    
    private String nombre;
    
    @OneToMany(mappedBy = "ciudadLugar", cascade = CascadeType.ALL)
    @PodamExclude
    private List<LugarEntity> lugaresCiudad;
    
    @ManyToMany
    @PodamExclude
    private List<FestivalEntity> festivalesCiudad;
    
    /**
     *
     */
    public CiudadEntity() {
        
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
    public List<LugarEntity> getLugaresCiudad() {
        return lugaresCiudad;
    }

    /**
     *
     * @param lugaresCiudad
     */
    public void setLugaresCiudad(List<LugarEntity> lugaresCiudad) {
        this.lugaresCiudad = lugaresCiudad;
    }

    /**
     *
     * @return
     */
    public List<FestivalEntity> getFestivalesCiudad() {
        return festivalesCiudad;
    }

    /**
     *
     * @param festivalesCiudad
     */
    public void setFestivalesCiudad(List<FestivalEntity> festivalesCiudad) {
        this.festivalesCiudad = festivalesCiudad;
    }
   
    
    
}
