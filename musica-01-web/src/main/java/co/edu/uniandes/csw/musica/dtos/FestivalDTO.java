/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import co.edu.uniandes.csw.musica.entities.FestivalEntity;
import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author pa.alvarado10
 */
@XmlRootElement
public class FestivalDTO implements Serializable {
    private Long id;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private List <FuncionEntity> funciones;
    private List <CiudadEntity> ciudades;
    private List <GeneroEntity> generos;
public FestivalDTO(FestivalEntity festival){
    	if(festival!=null){
    		this.id=festival.getId();
    		this.nombre=festival.getNombre();
    		this.fechaInicio=festival.getFechaInicio();
    		this.fechaFin=festival.getFechafin();
                this.ciudades=festival.getCiudades();
                this.funciones=festival.getFunciones();
                this.generos=festival.getGeneros();
               
    	}
}
public FestivalEntity toEntity(){
    	FestivalEntity festival = new FestivalEntity();
    	festival.setId(id);
    	festival.setNombre(nombre);
    	festival.setFechaInicio(fechaInicio);
    	festival.setFechafin(fechaFin);
        festival.setCiudades((ArrayList<CiudadEntity>) ciudades);
        festival.setFunciones((ArrayList<FuncionEntity>) funciones);
        festival.setGeneros((ArrayList<GeneroEntity>) generos);
        
    	return festival;
}
public FestivalDTO(){
    	
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
 public void setFechaInicio(Date inicio){
    	this.fechaInicio=(java.sql.Date) inicio;
    }
    public void setFechaFin(Date fin){
    	this.fechaFin=(java.sql.Date) fin;
    }
    public Date getFechaInicio(){
    	return this.fechaInicio;
    }
    public Date getFechaFin(){
    	return this.fechaFin;
    }
  
    public List<CiudadEntity> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<CiudadEntity> ciudades) {
        this.ciudades = ciudades;
    }

    public List<GeneroEntity> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroEntity> generos) {
        this.generos = generos;
    }

    public void setFunciones(List<FuncionEntity> funciones) {
        this.funciones = funciones;
    }

    public List<FuncionEntity> getFunciones() {
        return funciones;
    }
    
}
