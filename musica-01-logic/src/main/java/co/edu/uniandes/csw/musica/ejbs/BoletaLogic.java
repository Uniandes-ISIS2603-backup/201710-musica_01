/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.BoletaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BoletaLogic {
    @Inject
    private BoletaPersistence persistence;

    public BoletaEntity createBoleta(BoletaEntity boleta)throws BusinessLogicException
    {
        validarBoleta(boleta);
        return persistence.create(boleta);
    }

    public List<BoletaEntity> getBoletas ()
    {
        return persistence.findAll();
    }

    public BoletaEntity getBoleta (Long id)
    {
        return persistence.find(id);
    }

    public BoletaEntity updateBoleta(BoletaEntity entity) throws BusinessLogicException
    {
        validarBoleta(entity);
        return persistence.update(entity);
    }

    public void deleteBoleta (Long id) throws BusinessLogicException
    {
        validarId(id);
        persistence.delete(id);
    }

    public void validarId( Long id) throws BusinessLogicException
    {
        BoletaEntity entity = persistence.find(id);
        if (entity == null)
         throw new BusinessLogicException ("El id debe ser válido.");
    }

    public void validarBoleta (BoletaEntity boleta) throws BusinessLogicException
    {
        if(boleta.getPrecio() <= 0)
            throw new BusinessLogicException("El precio debe ser mayor a 0");

        //if(boleta.getFuncion() == null)
        //    throw new BusinessLogicException("La boleta debe estar asociada a una función");
    }
}
