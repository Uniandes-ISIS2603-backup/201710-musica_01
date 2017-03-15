/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.entities;

/**
 *
 * @author pa.alvarado10
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    private ArrayList<UsuarioEntity> admins;

    @ManyToMany(mappedBy = "festivalesGenero", cascade = CascadeType.ALL)
    private ArrayList<GeneroEntity> generos;

    @ManyToMany(mappedBy = "festivalesCiudad", cascade = CascadeType.ALL)
    private ArrayList<CiudadEntity> ciudades;

    @OneToMany(mappedBy = "festivalFuncion", cascade = CascadeType.ALL, orphanRemoval = false)
    private ArrayList<FuncionEntity> funciones;

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

    public ArrayList<UsuarioEntity> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<UsuarioEntity> admins) {
        this.admins = admins;
    }

    public ArrayList<GeneroEntity> getGeneros() {
        return generos;
    }

    public void setGeneros(ArrayList<GeneroEntity> generos) {
        this.generos = generos;
    }

    public ArrayList<CiudadEntity> getCiudades() {
        return ciudades;
    }

    public void setCiudades(ArrayList<CiudadEntity> ciudades) {
        this.ciudades = ciudades;
    }

    public ArrayList<FuncionEntity> getFunciones() {
        return funciones;
    }

    public void setFunciones(ArrayList<FuncionEntity> funciones) {
        this.funciones = funciones;
    }
}