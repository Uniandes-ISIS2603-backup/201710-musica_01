/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.LugarEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.LugarPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author js.barbosa11
 */
@Stateless
public class LugarLogic {

    @Inject private LugarPersistence persistence;


    public List<LugarEntity> getLugares(){
        return persistence.findAll();
    }

    public LugarEntity getLugar(Long id){
        return persistence.find(id);
    }

    public LugarEntity createLugar(LugarEntity entity) throws BusinessLogicException{
        validarLugar(entity);
        return persistence.create(entity);
    }

    public LugarEntity updateLugar(LugarEntity entity) throws BusinessLogicException{
        validarLugar(entity);
        return persistence.update(entity);
    }

    public void deleteLugar(Long id) throws BusinessLogicException{
        validarId(id);
        persistence.delete(id);
    }

    public void validarLugar (LugarEntity lugar) throws BusinessLogicException
    {
         if (lugar.getNombre() == null)
            throw new BusinessLogicException ("El lugar debe tener nombre.");

         if( lugar.getCapacidad() <=0 || lugar.getCapacidadSonido() <= 0)
            throw new BusinessLogicException ("La capacidad debe ser mayor a 0.");

         if( lugar.getCostoPreferencial()<=0 || lugar.getCostoEconomico() <=0)
            throw new BusinessLogicException ("El costo debe ser mayor a 0.");

         if( lugar.getCostoEconomico() >= lugar.getCostoPreferencial())
            throw new BusinessLogicException ("El costo económico debe ser menor al costo preferencial.");

        if(lugar.getCiudadLugar() == null)
            throw new BusinessLogicException("El lugar debe tener una ciudad.");
    }

    public void validarId( Long id) throws BusinessLogicException
    {
        LugarEntity entity = persistence.find(id);
        if (entity == null)
         throw new BusinessLogicException ("El id debe ser válido.");
    }

}
