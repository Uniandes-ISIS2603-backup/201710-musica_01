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

    public GeneroEntity createGenero(GeneroEntity genero) throws BusinessLogicException
    {
        validarNombre(genero);
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

    public GeneroEntity updateGenero(GeneroEntity entity) throws BusinessLogicException
    {
        validarNombre(entity);
        return persistence.update(entity);
    }

    public void deleteGenero (Long id) throws BusinessLogicException
    {
        validarId(id);
        persistence.delete(id);
    }

    public void validarNombre (GeneroEntity genero) throws BusinessLogicException
    {
         if (genero.getNombre() == null)
         throw new BusinessLogicException ("El género debe tener nombre.");
    }

    public void validarId( Long id) throws BusinessLogicException
    {
        GeneroEntity entity = persistence.find(id);
        if (entity == null)
         throw new BusinessLogicException ("El id debe ser válido.");
    }

}
