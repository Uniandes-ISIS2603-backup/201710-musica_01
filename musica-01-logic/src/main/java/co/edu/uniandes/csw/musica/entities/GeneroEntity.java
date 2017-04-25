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

    public GeneroEntity() {
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

    public List<FestivalEntity> getFestivalesGenero() {
        return festivalesGenero;
    }

    public void setFestivalesGenero(List<FestivalEntity> festivalesGenero) {
        this.festivalesGenero = festivalesGenero;
    }

    public List<MusicoEntity> getMusicosGenero() {
        return musicosGenero;
    }

    public void setMusicosGenero(List<MusicoEntity> musicosGenero) {
        this.musicosGenero = musicosGenero;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final GeneroEntity other = (GeneroEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

    
    
}
