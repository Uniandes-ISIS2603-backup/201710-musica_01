/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author pa.alvarado10
 */

@Entity
public class AdministradorEntity implements Serializable{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String nombre;
private String clave;
public ArrayList<FestivalEntity> festivales;
//private ArrayList<CiudadEntity> ciudades;
private ArrayList<GeneroEntity> generos;
private ArrayList<MusicoEntity> musicos;
public AdministradorEntity(){
	
}
public void setClave(String clave) {
	this.clave = clave;
}
public String getClave() {
	return clave;
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
public void setFestivales(ArrayList<FestivalEntity> festivales) {
	this.festivales = festivales;
}
public ArrayList<FestivalEntity> getFestivales() {
	return festivales;
}
//public ArrayList<CiudadEntity> getCiudades() {
//	return ciudades;
//}
//public void setCiudades(ArrayList<CiudadEntity> ciudades) {
//	this.ciudades = ciudades;
//}
public ArrayList<GeneroEntity> getGeneros() {
	return generos;
}
public void setGeneros(ArrayList<GeneroEntity> generos) {
	this.generos = generos;
}
}