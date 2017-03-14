/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

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
    
    @OneToMany(mappedBy = "funcionesLugar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FuncionEntity> funcionesLugar;
    
    
    
    public LugarDetailDTO(){
        super();
    }
    
    public LugarDetailDTO(LugarEntity entity){
        super(entity);
    }
    
    @Override
    public LugarEntity toEntity(){
        LugarEntity entity = super.toEntity();
        return entity;
    }
    
}
