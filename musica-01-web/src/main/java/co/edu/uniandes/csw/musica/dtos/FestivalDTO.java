/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.FestivalEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pa.alvarado10
 */
@XmlRootElement
public class FestivalDTO implements Serializable {
    private Long id;
    private String nombre;
    private java.sql.Date fechaInicio;
    private java.sql.Date fechaFin;
    public FestivalDTO(FestivalEntity festival){
    	if(festival!=null){
    		this.id=festival.getId();
    		this.nombre=festival.getNombre();
    		this.fechaInicio=festival.getFechaInicio();
    		this.fechaFin=festival.getFechafin();
    	}
    }
    public FestivalEntity toEntity(){
    	FestivalEntity festival = new FestivalEntity();
    	festival.setId(id);
    	festival.setNombre(nombre);
    	festival.setFechaInicio(fechaInicio);
    	festival.setFechafin(fechaFin);
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
    
}
