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
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.bustamante143
 */
@Entity
public class MusicoEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PodamExclude
    private Long id;
    
    private String nombre;
    private String trayectoria;
    private int requerimientoSonido;
    private int requerimientoCapacidad;
    
    @ManyToOne
    @PodamExclude
    private GeneroEntity generoMusico;
    
    @ManyToMany(mappedBy = "musicos", cascade = CascadeType.ALL)
    @PodamExclude
    private List<FuncionEntity> funcionesMusico;

    /**
     *
     */
    public MusicoEntity() {
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
    public String getTrayectoria() {
        return trayectoria;
    }

    /**
     *
     * @param trayectoria
     */
    public void setTrayectoria(String trayectoria) {
        this.trayectoria = trayectoria;
    }

    /**
     *
     * @return
     */
    public int getRequerimientoSonido() {
        return requerimientoSonido;
    }

    /**
     *
     * @param requerimientoSonido
     */
    public void setRequerimientoSonido(int requerimientoSonido) {
        this.requerimientoSonido = requerimientoSonido;
    }

    /**
     *
     * @return
     */
    public int getRequerimientoCapacidad() {
        return requerimientoCapacidad;
    }

    /**
     *
     * @param requerimientoCapacidad
     */
    public void setRequerimientoCapacidad(int requerimientoCapacidad) {
        this.requerimientoCapacidad = requerimientoCapacidad;
    }

    /**
     *
     * @return
     */
    public GeneroEntity getGeneroMusico() {
        return generoMusico;
    }

    /**
     *
     * @param generoMusico
     */
    public void setGeneroMusico(GeneroEntity generoMusico) {
        this.generoMusico = generoMusico;
    }

    /**
     *
     * @return
     */
    public List<FuncionEntity> getFuncionesMusico() {
        return funcionesMusico;
    }

    /**
     *
     * @param funcionesMusico
     */
    public void setFuncionesMusico(List<FuncionEntity> funcionesMusico) {
        this.funcionesMusico = funcionesMusico;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final MusicoEntity other = (MusicoEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    
    
}
