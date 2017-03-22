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
        if (entity.getNombre() == null)
            throw new BusinessLogicException ("El lugar debe tener nombre.");
        return persistence.create(entity);
    }
    
    // TODO: revisar validaciones al momento de actualizar
    public LugarEntity updateLugar(LugarEntity entity){
        return persistence.update(entity);
    }
    
    // TODO: revisar validaciones al momento de eliminar
    public void deleteLugar(Long id){
        persistence.delete(id);
    }
    
}
