package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.GeneroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GeneroLogic 
{

    @Inject
    private GeneroPersistence persistence;
    
    public GeneroEntity createGenero(GeneroEntity genero)throws BusinessLogicException
    {
        if (genero.getNombre() == null)
            throw new BusinessLogicException ("El género debe tener nombre.");
        return persistence.create(genero);
    }
    
    public List<GeneroEntity> getGeneros ()
    {
        return persistence.findAll();
    }
    
    public GeneroEntity getGenero (Long id)
    {
        return persistence.find(id);
    }
    
    // TODO: revisar validaciones al momento de actualizar
    public GeneroEntity updateGenero(GeneroEntity entity) 
    {
        return persistence.update(entity);
    }
    
    // TODO: revisar validaciones al momento de eliminar
    public void deleteGenero (Long id)
    {
        persistence.delete(id);
    }
        
}
