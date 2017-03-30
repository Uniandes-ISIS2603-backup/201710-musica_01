
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioDTO implements Serializable {
private Long id;
private String nombre;
public UsuarioDTO(UsuarioEntity administrador)
{
    if(administrador != null)
    {
        this.id = administrador.getId();
        this.nombre = administrador.getNombre();
    }
}

public UsuarioEntity toEntity()
{
	UsuarioEntity admon = new UsuarioEntity();
	admon.setId(id);
	admon.setNombre(nombre);
    return admon;
}

public UsuarioDTO() {
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
