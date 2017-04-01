package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.MusicoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MusicoLogic 
{

    @Inject
    private MusicoPersistence persistence;
    
    public MusicoEntity createMusico(MusicoEntity musico)throws BusinessLogicException
    {
        validarMusico(musico);
        return persistence.create(musico);
    }
    
    public List<MusicoEntity> getMusicos ()
    {
        return persistence.findAll();
    }
    
    public MusicoEntity getMusico (Long id)
    {
        return persistence.find(id);
    }
    
    public MusicoEntity updateMusico(MusicoEntity entity) throws BusinessLogicException 
    {
        validarMusico(entity);
        return persistence.update(entity);
    }

    public void deleteMusico (Long id) throws BusinessLogicException
    {
        validarId(id);
        persistence.delete(id);
    }
    
    public void validarMusico (MusicoEntity musico) throws BusinessLogicException
    {
         if (musico.getNombre() == null)
            throw new BusinessLogicException ("El lugar debe tener nombre.");
         
         if(musico.getGeneroMusico() == null)
            throw new BusinessLogicException ("EL músico debe tener un género asociado.");
         
    }
    
    public void validarId( Long id) throws BusinessLogicException
    {
        MusicoEntity entity = persistence.find(id);
        if (entity == null)
         throw new BusinessLogicException ("El id debe ser válido.");
    }
    
}
