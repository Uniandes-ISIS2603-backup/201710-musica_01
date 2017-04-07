package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.FestivalEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.FestivalPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author pa.alvarado10
 */
@Stateless
public class FestivalLogic
{
    @Inject
    private FestivalPersistence persistence;

    public FestivalEntity createFestival(FestivalEntity festival)throws BusinessLogicException
    {
        validarFestival(festival);
        return persistence.create(festival);
    }

    public List <FestivalEntity> getFestivales()
    {
        return persistence.findAll();
    }

    public FestivalEntity getFestival(Long id)
    {
        return persistence.find(id);
    }

    public FestivalEntity updateFestival(FestivalEntity entity) throws BusinessLogicException
    {
        validarFestival(entity);
        return persistence.update(entity);
    }

    public void deleteFestival(Long id) throws BusinessLogicException
    {
        validarId(id);
        persistence.delete(id);
    }

    public void validarFestival(FestivalEntity festival) throws BusinessLogicException
    {
        if(festival.getNombre()==null)
            throw new BusinessLogicException("El festival debe tener un nombre");

        if(festival.getFechaInicio().after(festival.getFechafin()))
            throw new BusinessLogicException("La fecha de inicio no puede ser despues de la de fin");

        //if(festival.getGeneros().isEmpty())
        //    throw new BusinessLogicException("El festival debe tener por lo menos un genero");

    }

    public void validarId( Long id) throws BusinessLogicException
    {
        FestivalEntity entity = persistence.find(id);
        if (entity == null)
         throw new BusinessLogicException ("El id debe ser válido.");
    }

}
