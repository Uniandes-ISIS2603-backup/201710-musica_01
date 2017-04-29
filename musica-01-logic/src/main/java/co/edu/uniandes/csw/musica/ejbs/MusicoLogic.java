/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.MusicoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.bustamante143
 */
@Stateless
public class MusicoLogic
{

    @Inject
    private MusicoPersistence persistence;

    /**
     *
     * @param musico
     * @return
     * @throws BusinessLogicException
     */
    public MusicoEntity createMusico(MusicoEntity musico)throws BusinessLogicException
    {
        validarMusico(musico);
        return persistence.create(musico);
    }

    /**
     *
     * @return
     */
    public List<MusicoEntity> getMusicos ()
    {
        return persistence.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public MusicoEntity getMusico (Long id)
    {
        return persistence.find(id);
    }

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public MusicoEntity updateMusico(MusicoEntity entity) throws BusinessLogicException
    {
        validarMusico(entity);
        return persistence.update(entity);
    }

    /**
     *
     * @param id
     * @throws BusinessLogicException
     */
    public void deleteMusico (Long id) throws BusinessLogicException
    {
        validarId(id);
        persistence.delete(id);
    }

    /**
     *
     * @param musico
     * @throws BusinessLogicException
     */
    public void validarMusico (MusicoEntity musico) throws BusinessLogicException
    {
         if (musico.getNombre() == null || musico.getNombre().equals(""))
         {
            throw new BusinessLogicException ("El lugar debe tener nombre.");
         }

         if(musico.getGeneroMusico() == null)
         {
            throw new BusinessLogicException ("EL músico debe tener un género asociado.");
         }

    }

    /**
     *
     * @param id
     * @throws BusinessLogicException
     */
    public void validarId( Long id) throws BusinessLogicException
    {
        MusicoEntity entity = persistence.find(id);
        if (entity == null)
        {
            throw new BusinessLogicException ("El id debe ser válido.");
        }
    }

}
