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
import java.sql.Date;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 private ArrayList<GeneroEntity> generos;
 //private ArrayList<CiudadEntity> ciudades;
 private ArrayList<FuncionEntity> funciones;
 public FestivalEntity(){
	 
 }
 public void setFechafin(Date fechafin) {
	this.fechafin = fechafin;
}
 public Date getFechafin() {
	return fechafin;
}
 public void setFechaInicio(Date fechaInicio) {
	this.fechaInicio = fechaInicio;
}
 public Date getFechaInicio() {
	return fechaInicio;
}
 public void setId(Long id) {
	this.id = id;
}
 public Long getId() {
	return id;
}
 public void setNombre(String nombre) {
	this.nombre = nombre;
}
 public String getNombre() {
	return nombre;
}
// public ArrayList<CiudadEntity> getCiudades() {
//	return ciudades;
//}
 public ArrayList<FuncionEntity> getFunciones() {
	return funciones;
}
 public ArrayList<GeneroEntity> getGeneros() {
	return generos;
}
 //public void setCiudades(ArrayList<CiudadEntity> ciudades) {
//	this.ciudades = ciudades;
//}
}