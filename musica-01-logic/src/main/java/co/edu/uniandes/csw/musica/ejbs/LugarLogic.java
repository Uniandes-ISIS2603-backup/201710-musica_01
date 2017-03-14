/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.LugarEntity;
import co.edu.uniandes.csw.musica.persistence.LugarPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author js.barbosa11
 */
@Stateless
public class LugarLogic {
    
    @Inject private LugarPersistence persistence;
    
    
    public List<LugarEntity> getLugares(){
        return persistence.findAll();
    }
    
    public LugarEntity getLugar(Long id){
        return persistence.find(id);
    }
    
    public LugarEntity createLugar(LugarEntity entity){
        persistence.create(entity);
        return entity;
    }
    
    public LugarEntity updateLugar(LugarEntity entity){
        return persistence.update(entity);
    }
    
    public void deleteLugar(Long id){
        persistence.delete(id);
    }
    
}
