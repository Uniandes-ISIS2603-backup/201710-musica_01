
package co.edu.uniandes.csw.musica.persistence;

import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class MusicoPersistence {

    @PersistenceContext(unitName="musicaPU") 
    protected EntityManager em;
    
    public MusicoEntity find(Long id)    {
        return em.find(MusicoEntity.class, id);
    }
    
    public List<MusicoEntity> findAll()    {
        TypedQuery<MusicoEntity> q = em.createQuery("select c from MusicoEntity c", MusicoEntity.class);
        return q.getResultList();
    }
    
    public MusicoEntity create(MusicoEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }

    public MusicoEntity update(MusicoEntity entity) {
       
        return em.merge(entity);
    }

    public void delete(Long id) {
        
        MusicoEntity entity = em.find(MusicoEntity.class, id);
        em.remove(entity);
    }
    
}
