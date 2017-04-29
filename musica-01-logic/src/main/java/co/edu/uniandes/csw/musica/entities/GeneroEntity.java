/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
 * @author jc.bustamante143
 */
@Entity
public class GeneroEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PodamExclude
    private Long id;
    private String nombre;
    
    @ManyToMany
    @PodamExclude
    private List<FestivalEntity> festivalesGenero;
    
    @OneToMany(mappedBy = "generoMusico", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<MusicoEntity> musicosGenero;

    /**
     *
     */
    public GeneroEntity() {
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
    public List<FestivalEntity> getFestivalesGenero() {
        return festivalesGenero;
    }

    /**
     *
     * @param festivalesGenero
     */
    public void setFestivalesGenero(List<FestivalEntity> festivalesGenero) {
        this.festivalesGenero = festivalesGenero;
    }

    /**
     *
     * @return
     */
    public List<MusicoEntity> getMusicosGenero() {
        return musicosGenero;
    }

    /**
     *
     * @param musicosGenero
     */
    public void setMusicosGenero(List<MusicoEntity> musicosGenero) {
        this.musicosGenero = musicosGenero;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
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
        final GeneroEntity other = (GeneroEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

    
    
}
