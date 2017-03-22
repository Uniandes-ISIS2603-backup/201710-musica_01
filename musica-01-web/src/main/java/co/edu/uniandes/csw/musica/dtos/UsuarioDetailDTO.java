// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
// TODO: eliminar los import que no son requeridos
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ana Lucy
 */
@XmlRootElement
public class UsuarioDetailDTO extends UsuarioDTO {
    private List<BoletaEntity> boletas=new ArrayList(); 
      //  @ManyToMany(mappedBy = "musicosFunciones", cascade = CascadeType.ALL)
    public UsuarioDetailDTO(UsuarioEntity usuario){
        super(usuario);
    }
public UsuarioDetailDTO(){
super();
}
public UsuarioEntity toEntity(){
UsuarioEntity entity = super.toEntity();
return entity;
}
public List getBoletas (){
return boletas;
}
public void setBoletas(BoletaEntity boleta){
boletas.add(boleta);
}
}
