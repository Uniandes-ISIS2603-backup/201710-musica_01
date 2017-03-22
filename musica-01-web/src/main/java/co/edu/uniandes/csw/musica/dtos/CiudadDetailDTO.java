// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import co.edu.uniandes.csw.musica.entities.FestivalEntity;
import co.edu.uniandes.csw.musica.entities.LugarEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author js.barbosa11
 */
@XmlRootElement
public class CiudadDetailDTO extends CiudadDTO {
    
   private List<LugarEntity> lugaresCiudad;
   private List<FestivalEntity> festivalesCiudad;
    
    public CiudadDetailDTO(){
        super();
    }

    public CiudadDetailDTO(CiudadEntity entity){
       super(entity);
       if(entity != null)
       {
           this.festivalesCiudad = entity.getFestivalesCiudad();
           this.lugaresCiudad = entity.getLugaresCiudad();
       }
    }
    public List<LugarEntity> getLugaresCiudad(){
        return lugaresCiudad;
    }
    public void setLugaresCiudad(List<LugarEntity> lugaresCiudad){
        this.lugaresCiudad=lugaresCiudad;
    }
    public List<FestivalEntity> getFestivalesCiudad(){
        return festivalesCiudad;
    }
    public void setFestivalesCiudad(List<FestivalEntity> festivalesCiudad){
        this.festivalesCiudad=festivalesCiudad;
    }
    
    @Override
    public CiudadEntity toEntity()
    {
        CiudadEntity entity = super.toEntity();
        entity.setFestivalesCiudad(festivalesCiudad);
        entity.setLugaresCiudad(lugaresCiudad);
        return entity;
    }
}
