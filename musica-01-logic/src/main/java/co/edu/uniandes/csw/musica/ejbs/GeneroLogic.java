/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.GeneroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.bustamante143
 */
@Stateless
public class GeneroLogic
{

    @Inject
    private GeneroPersistence persistence;

    /**
     *
     * @param genero
     * @return
     * @throws BusinessLogicException
     */
    public GeneroEntity createGenero(GeneroEntity genero) throws BusinessLogicException
    {
        validarNombre(genero);
        return persistence.create(genero);
    }

    /**
     *
     * @return
     */
    public List<GeneroEntity> getGeneros ()
    {
        return persistence.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public GeneroEntity getGenero (Long id)
    {
        return persistence.find(id);
    }

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public GeneroEntity updateGenero(GeneroEntity entity) throws BusinessLogicException
    {
        validarNombre(entity);
        return persistence.update(entity);
    }

    /**
     *
     * @param id
     * @throws BusinessLogicException
     */
    public void deleteGenero (Long id) throws BusinessLogicException
    {
        validarId(id);
        persistence.delete(id);
    }

    /**
     *
     * @param genero
     * @throws BusinessLogicException
     */
    public void validarNombre (GeneroEntity genero) throws BusinessLogicException
    {
         if (genero.getNombre() == null || genero.getNombre().equals(""))
         {
         throw new BusinessLogicException ("El género debe tener nombre.");
         }
    }

    /**
     *
     * @param id
     * @throws BusinessLogicException
     */
    public void validarId( Long id) throws BusinessLogicException
    {
        GeneroEntity entity = persistence.find(id);
        if (entity == null)
        {
            throw new BusinessLogicException ("El id debe ser válido.");
        }
    }

}
