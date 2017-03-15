/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.BoletaEntity;
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
    
    public BoletaEntity createBoleta(BoletaEntity boleta)throws BusinessLogicException
    {
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
    
    public BoletaEntity updateBoleta(BoletaEntity entity) 
    {
        return persistence.update(entity);
    }
    
    public void deleteBoleta (Long id)
    {
        persistence.delete(id);
    }

    
}
