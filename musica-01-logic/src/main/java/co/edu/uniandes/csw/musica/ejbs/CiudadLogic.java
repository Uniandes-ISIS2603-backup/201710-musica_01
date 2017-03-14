/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.ejbs;


import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import co.edu.uniandes.csw.musica.persistence.CiudadPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author js.barbosa11
 */
@Stateless
public class CiudadLogic {
     
    @Inject private CiudadPersistence persistence;
    
    
    public List<CiudadEntity> getCiudades(){
        return persistence.findAll();
    }
    
    public CiudadEntity getCiudad(Long id){
        return persistence.find(id);
    }
   
    public CiudadEntity createCiudad(CiudadEntity entity){
        persistence.create(entity);
        return entity;
    }
    
    public CiudadEntity updateCiudad(CiudadEntity entity){
        return persistence.update(entity);
    }
    
    public void deleteCiudad(Long id) {
        persistence.delete(id);
    }
    
}
