package co.edu.uniandes.csw.musica.ejbs;


import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.CiudadPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author js.barbosa11
 */
@Stateless
public class CiudadLogic {
     
    @Inject private CiudadPersistence persistence;
    
    
    public List<CiudadEntity> getCiudades(){
        return persistence.findAll();
    }
    
    public CiudadEntity getCiudad(Long id){
        return persistence.find(id);
    }
   
    public CiudadEntity createCiudad(CiudadEntity entity) throws BusinessLogicException
    {
        if (entity.getNombre() == null)
        throw new BusinessLogicException ("La ciudad debe tener nombre.");    
        return persistence.create(entity);
    }
    
    // TODO: revisar validaciones al momento de actualizar
    public CiudadEntity updateCiudad(CiudadEntity entity){
        return persistence.update(entity);
    }
    
    // TODO: revisar validaciones al momento de borrar
    public void deleteCiudad(Long id) {
        persistence.delete(id);
    }
    
}
