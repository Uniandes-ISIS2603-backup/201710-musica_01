/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.FuncionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class FuncionLogic {

     @Inject
    private FuncionPersistence persistence;

    public FuncionEntity createFuncion(FuncionEntity funcion)throws BusinessLogicException
    {
        validarFuncion(funcion);
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

    public FuncionEntity updateFuncion(FuncionEntity entity) throws BusinessLogicException
    {
        validarFuncion(entity);
        return persistence.update(entity);
    }

    public void deleteFuncion (Long id) throws BusinessLogicException
    {
        validarId(id);
        persistence.delete(id);
    }

    public void validarId( Long id) throws BusinessLogicException
    {
        FuncionEntity entity = persistence.find(id);
        if (entity == null)
        {
         throw new BusinessLogicException ("El id debe ser válido.");
        }
    }

    public void validarFuncion (FuncionEntity funcion) throws BusinessLogicException
    {
         if(funcion.getFechaInicio() == null || funcion.getFechafin() == null)
        {
            throw new BusinessLogicException("La función debe tener fechas definidas.");
        }
        if(funcion.getFechaInicio().after(funcion.getFechafin()))
        {
            throw new BusinessLogicException("La fecha de inicio debe ser antes de la fecha de fin.");
        }

        /*if(funcion.getLugar() == null)
        {
            throw new BusinessLogicException("La función debe tener un lugar asociado.");
        }*/

       
    }
}
