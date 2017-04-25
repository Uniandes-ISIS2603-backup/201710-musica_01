/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.ejbs;


import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
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
   
    public CiudadEntity createCiudad(CiudadEntity entity) throws BusinessLogicException
    {
        validarCiudad(entity);
        return persistence.create(entity);
    }
    
    public CiudadEntity updateCiudad(CiudadEntity entity) throws BusinessLogicException{
        validarCiudad(entity);
        return persistence.update(entity);
    }
    
    public void deleteCiudad(Long id) throws BusinessLogicException {
        validarId(id);
        persistence.delete(id);
    }
    
    public void validarCiudad(CiudadEntity ciudad) throws BusinessLogicException
    {
        if(ciudad.getNombre() == null)
            throw new BusinessLogicException ("La ciudad debe tener nombre."); 
    }
            
    public void validarId( Long id) throws BusinessLogicException
    {
        CiudadEntity entity = persistence.find(id);
        if (entity == null)
         throw new BusinessLogicException ("El id debe ser válido.");
    }         
}
