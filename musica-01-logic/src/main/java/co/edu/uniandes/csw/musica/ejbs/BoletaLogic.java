// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.BoletaEntity;
// TODO: eliminar import que no se usan
import co.edu.uniandes.csw.musica.entities.FuncionEntity;
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
    
    // TODO: implementar validaciones de negocio. p.ej. revisar las relaciones
    public BoletaEntity createBoleta(BoletaEntity boleta)throws BusinessLogicException
    {
        // TODO: revisar esta validación. Al parecer el ID es autogenerado en la entidad
        if (boleta.getId() == null)
            throw new BusinessLogicException ("La boleta debe tener id");
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
    
    // TODO: revisar validaciones al momento de actualizar
    public BoletaEntity updateBoleta(BoletaEntity entity) 
    {
        return persistence.update(entity);
    }
    
    // TODO: revisar validaciones al momento de eliminar
    public void deleteBoleta (Long id)
    {
        persistence.delete(id);
    }

    
}
