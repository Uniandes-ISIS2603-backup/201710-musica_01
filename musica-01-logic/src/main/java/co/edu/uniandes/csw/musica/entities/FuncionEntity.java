/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author la.herrera11
 */
@Entity 
public class FuncionEntity implements Serializable  {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Temporal(TemporalType.DATE) 
 private Date fechaInicio;
 @Temporal(TemporalType.DATE) 
 private Date fechafin;
 private boolean aprobada;
 private Double calificacion; 
 //private Lugar lugar;
 private ArrayList<BoletaEntity> boletas;

public FuncionEntity(){
	 
 }

public Long getId() {
        return id;
}

public void setId(Long id) {
        this.id = id;
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
 public boolean getAprobada() {
	return aprobada;
}
 public void setAprobada(boolean aprobada) {
	this.aprobada = aprobada;
}
  public Double getCalificacion() {
	return calificacion;
}
 public void setCalificacion(Double calificacion) {
	this.calificacion = calificacion;
}

//public Lugar getLugar(){
//    return lugar;
// }
//public void setLugar(Lugar lugar) {    
//      this.lugar=lugar;
//}
 public ArrayList<BoletaEntity> getBoletas() {
	return boletas;
}
}
