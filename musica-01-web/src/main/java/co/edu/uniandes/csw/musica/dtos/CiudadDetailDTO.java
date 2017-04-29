/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
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
    
    /**
     *
     */
    public CiudadDetailDTO(){
        super();
    }

    /**
     *
     * @param entity
     */
    public CiudadDetailDTO(CiudadEntity entity){
       super(entity);
       if(entity != null)
       {
           this.festivalesCiudad = entity.getFestivalesCiudad();
           this.lugaresCiudad = entity.getLugaresCiudad();
       }
    }

    /**
     *
     * @return
     */
    public List<LugarEntity> getLugaresCiudad(){
        return lugaresCiudad;
    }

    /**
     *
     * @param lugaresCiudad
     */
    public void setLugaresCiudad(List<LugarEntity> lugaresCiudad){
        this.lugaresCiudad=lugaresCiudad;
    }

    /**
     *
     * @return
     */
    public List<FestivalEntity> getFestivalesCiudad(){
        return festivalesCiudad;
    }

    /**
     *
     * @param festivalesCiudad
     */
    public void setFestivalesCiudad(List<FestivalEntity> festivalesCiudad){
        this.festivalesCiudad=festivalesCiudad;
    }
    
    /**
     *
     * @return
     */
    @Override
    public CiudadEntity toEntity()
    {
        CiudadEntity entity = super.toEntity();
        entity.setFestivalesCiudad(festivalesCiudad);
        entity.setLugaresCiudad(lugaresCiudad);
        return entity;
    }
}
