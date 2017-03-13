/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.FuncionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author la.herrera11
 */

@Stateless
public class FuncionLogic {
    
     @Inject
    private FuncionPersistence persistence;
    
    public FuncionEntity createFuncion(FuncionEntity funcion)throws BusinessLogicException
    {
        if (funcion.getId() == null)
            throw new BusinessLogicException ("La función debe tener id");
        return persistence.create(funcion);
    }
    
    public List<FuncionEntity> getFunciones ()
    {
        return persistence.findAll();
    }
    
    public FuncionEntity getFuncion (Long id)
    {
        return persistence.find(id);
    }
    
    public FuncionEntity updateFuncion(FuncionEntity entity) 
    {
        return persistence.update(entity);
    }
    
    public void deleteFuncion (Long id)
    {
        persistence.delete(id);
    }
        
}