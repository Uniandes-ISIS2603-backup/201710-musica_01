/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.persistence;


import co.edu.uniandes.csw.musica.entities.FestivalEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class FestivalPersistence {
    
    @PersistenceContext(unitName="musicaPU") 
    protected EntityManager em;
    
    public FestivalEntity find(Long id)    {
        return em.find(FestivalEntity.class, id);
    }
    
    public List<FestivalEntity> findAll()    {
        TypedQuery<FestivalEntity> q = em.createQuery("select u from FestivalEntity u", FestivalEntity.class);
        return q.getResultList();
    }
    
    public FestivalEntity create(FestivalEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }

    public FestivalEntity update(FestivalEntity entity) {
       
        return em.merge(entity);
    }

    public void delete(Long id) {
        
    	FestivalEntity entity = em.find(FestivalEntity.class, id);
        em.remove(entity);
    }
}
