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
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author js.barbosa11
 */
@XmlRootElement
public class CiudadDetailDTO extends CiudadDTO {
    
    @ManyToMany(mappedBy = "ciudadesFestivales", cascade = CascadeType.ALL)
    private List<FestivalEntity> festivalesCiudad;
    
    
    @OneToMany(mappedBy = "ciudadLugar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LugarEntity> lugaresCiudad;
    
    public CiudadDetailDTO(){
        super();
    }

    public CiudadDetailDTO(CiudadEntity entity){
       super(entity);
    }
    
    @Override
    public CiudadEntity toEntity(){
        CiudadEntity entity = super.toEntity();
        return entity;
    }
}
