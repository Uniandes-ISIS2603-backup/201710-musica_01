/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.MusicoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MusicoLogic 
{

    @Inject
    private MusicoPersistence persistence;
    
    public MusicoEntity createMusico(MusicoEntity musico)throws BusinessLogicException
    {
        if (musico.getNombre() == null)
            throw new BusinessLogicException ("El músico debe tener nombre.");
        return persistence.create(musico);
    }
    
    public List<MusicoEntity> getMusicos ()
    {
        return persistence.findAll();
    }
    
    public MusicoEntity getMusico (Long id)
    {
        return persistence.find(id);
    }
    
    public MusicoEntity updateMusico(MusicoEntity entity) 
    {
        return persistence.update(entity);
    }
    
    public void deleteMusico (Long id)
    {
        persistence.delete(id);
    }
    
}