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
    
   private List<LugarEntity> lugares;
   private List<FestivalEntity> festivales;
    
    public CiudadDetailDTO(){
        super();
    }

    public CiudadDetailDTO(CiudadEntity entity){
       super(entity);
       if(entity != null)
       {
           this.festivales = entity.getFestivales();
           this.lugares = entity.getLugares();
       }
    }
    public List<LugarEntity> getLugaresCiudad(){
        return lugares;
    }
    public void setLugaresCiudad(List<LugarEntity> lugaresCiudad){
        this.lugares=lugaresCiudad;
    }
    public List<FestivalEntity> getFestivalesCiudad(){
        return festivales;
    }
    public void setFestivalesCiudad(List<FestivalEntity> festivalesCiudad){
        this.festivales=festivalesCiudad;
    }
    
    @Override
    public CiudadEntity toEntity(){
        CiudadEntity entity = super.toEntity();
        entity.setFestivales(festivales);
        entity.setLugares(lugares);
        return entity;
    }
}
