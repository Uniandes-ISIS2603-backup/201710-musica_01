/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.entities.LugarEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author js.barbosa11
 */
@XmlRootElement
public class LugarDetailDTO extends LugarDTO {
    
    private List<FuncionEntity> funcionesLugar;
    private CiudadEntity ciudadEntity;
    
    public LugarDetailDTO(){
        super();
    }
    
    public LugarDetailDTO(LugarEntity entity){
        super(entity);
        
        if(entity != null){
            
            this.ciudadEntity = entity.getCiudad();
            this.funcionesLugar = entity.getFunciones();
        }
    }
    
    public List<FuncionEntity> getFuncionesLugar(){
        return funcionesLugar;
    }
    public void setFuncionesLugar(List<FuncionEntity> funcionesLugar){
        this.funcionesLugar=funcionesLugar;
    }
    
    public CiudadEntity getCiudad(){
        return ciudadEntity;
    }
    public void setCiudad(CiudadEntity ciudad){
        this.ciudadEntity = ciudad;
    }
    
    @Override
    public LugarEntity toEntity(){
        LugarEntity entity = super.toEntity();
        return entity;
    }
    
}
