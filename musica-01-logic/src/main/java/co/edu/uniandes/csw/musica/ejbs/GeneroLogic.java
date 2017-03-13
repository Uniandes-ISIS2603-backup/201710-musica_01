/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.GeneroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GeneroLogic 
{

    @Inject
    private GeneroPersistence persistence;
    
    public GeneroEntity createGenero(GeneroEntity genero)throws BusinessLogicException
    {
        if (genero.getNombre() == null)
            throw new BusinessLogicException ("El género debe tener nombre.");
        return persistence.create(genero);
    }
    
    public List<GeneroEntity> getGeneros ()
    {
        return persistence.findAll();
    }
    
    public GeneroEntity getGenero (Long id)
    {
        return persistence.find(id);
    }
    
    public GeneroEntity updateGenero(GeneroEntity entity) 
    {
        return persistence.update(entity);
    }
    
    public void deleteGenero (Long id)
    {
        persistence.delete(id);
    }
        
}
