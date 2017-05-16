/* 
 * Copyright (c) 2017 la.herrera11.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.BoletaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author la.herrera11
 */
@Stateless
public class BoletaLogic {
    
    @Inject
    private BoletaPersistence persistence;
    
    /**
     *
     * @param boleta
     * @return 
     * @throws BusinessLogicException
     */
    public BoletaEntity createBoleta(BoletaEntity boleta) throws BusinessLogicException
    {
        validarBoleta(boleta);
        return persistence.create(boleta);
    }
    
    /**
     *
     * @return
     */
    public List<BoletaEntity> getBoletas ()
    {
        return persistence.findAll();
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessLogicException
     */
    public BoletaEntity getBoleta (Long id) throws BusinessLogicException
    {
        validarId(id);
        return persistence.find(id);
    }

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public BoletaEntity updateBoleta(BoletaEntity entity) throws BusinessLogicException
    {
        validarBoleta(entity);
        return persistence.update(entity);
    }

    /**
     *
     * @param id
     * @throws BusinessLogicException
     */
    public void deleteBoleta (Long id) throws BusinessLogicException
    {
        validarId(id);
        persistence.delete(id);
    }

    /**
     *
     * @param id
     * @throws BusinessLogicException
     */
    public void validarId( Long id) throws BusinessLogicException
    {
        BoletaEntity entity = persistence.find(id);
        if (entity == null)
        {
         throw new BusinessLogicException ("El id debe ser válido.");
        }
    }
    
    /**
     *
     * @param boleta
     * @throws BusinessLogicException
     */
    public void validarBoleta (BoletaEntity boleta) throws BusinessLogicException
    {
        if(boleta.getFuncion() == null)
        {
            throw new BusinessLogicException("La boleta debe estar asociada a una función");
        }
        if(boleta.getPrecio() <= 0)
        {
            throw new BusinessLogicException("El precio debe ser mayor a 0");
        }
        if(boleta.getCliente() == null)
        {
            throw new BusinessLogicException("La boleta debe estar asociada a un cliente");
        }
    }
}
