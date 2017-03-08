/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.AdministradorEntity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AdministradorDTO {
private Long id;
private String nombre;
private String clave;
public AdministradorDTO(AdministradorEntity administrador)
{
    if(administrador != null)
    {
        this.id = administrador.getId();
        this.nombre = administrador.getNombre();
    }
}

public AdministradorEntity toEntity()
{
	AdministradorEntity admon = new AdministradorEntity();
	admon.setId(id);
	admon.setNombre(nombre);
    return admon;
}

public AdministradorDTO() {
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
}
