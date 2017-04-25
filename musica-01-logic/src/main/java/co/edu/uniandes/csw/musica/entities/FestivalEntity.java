/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class FestivalEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    private Date fechafin;

    @ManyToMany(mappedBy = "festivalesUsuario", cascade = CascadeType.ALL)
    private List<UsuarioEntity> adminsFestival;

    @ManyToMany(mappedBy = "festivalesGenero", cascade = CascadeType.ALL)
    private List<GeneroEntity> generos;

    @ManyToMany(mappedBy = "festivalesCiudad", cascade = CascadeType.ALL)
    private List<CiudadEntity> ciudades;

    @OneToMany(mappedBy = "festival", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<FuncionEntity> funciones;

    public FestivalEntity(){

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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public List<UsuarioEntity> getAdmins() {
        return adminsFestival;
    }

    public void setAdmins(List<UsuarioEntity> admins) {
        this.adminsFestival = admins;
    }

    public List<GeneroEntity> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroEntity> generos) {
        this.generos = generos;
    }

    public List<CiudadEntity> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<CiudadEntity> ciudades) {
        this.ciudades = ciudades;
    }

    public List<FuncionEntity> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<FuncionEntity> funciones) {
        this.funciones = funciones;
    }
}
