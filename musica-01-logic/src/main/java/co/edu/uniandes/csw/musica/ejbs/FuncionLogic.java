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
        // TODO: revisar esta validación. el id es autogenerado en la entidad
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
    
    // TODO: revisar las validaciones al momento de actualizar
    public FuncionEntity updateFuncion(FuncionEntity entity) 
    {
        return persistence.update(entity);
    }
    
    // TODO: revisar las validaciones al momento de borrar
    public void deleteFuncion (Long id)
    {
        persistence.delete(id);
    }
        
}