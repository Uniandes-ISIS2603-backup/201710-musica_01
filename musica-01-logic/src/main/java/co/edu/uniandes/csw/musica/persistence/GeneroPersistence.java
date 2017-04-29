/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.persistence;

import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jc.bustamante143
 */
@Stateless
public class GeneroPersistence {
    
    /**
     *
     */
    @PersistenceContext(unitName="musicaPU") 
    protected EntityManager em;
    
    /**
     *
     * @param id
     * @return
     */
    public GeneroEntity find(Long id)    {
        return em.find(GeneroEntity.class, id);
    }
    
    /**
     *
     * @return
     */
    public List<GeneroEntity> findAll()    {
        TypedQuery<GeneroEntity> q = em.createQuery("select c from GeneroEntity c", GeneroEntity.class);
        return q.getResultList();
    }
    
    /**
     *
     * @param entity
     * @return
     */
    public GeneroEntity create(GeneroEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }

    /**
     *
     * @param entity
     * @return
     */
    public GeneroEntity update(GeneroEntity entity) {
       
        return em.merge(entity);
    }

    /**
     *
     * @param id
     */
    public void delete(Long id) {
        
        GeneroEntity entity = em.find(GeneroEntity.class, id);
        em.remove(entity);
    }
}
