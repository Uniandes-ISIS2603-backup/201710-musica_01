/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.persistence;

import co.edu.uniandes.csw.musica.entities.MusicoEntity;
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
public class MusicoPersistence {

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
    public MusicoEntity find(Long id)    {
        return em.find(MusicoEntity.class, id);
    }
    
    /**
     *
     * @return
     */
    public List<MusicoEntity> findAll()    {
        TypedQuery<MusicoEntity> q = em.createQuery("select c from MusicoEntity c", MusicoEntity.class);
        return q.getResultList();
    }
    
    /**
     *
     * @param entity
     * @return
     */
    public MusicoEntity create(MusicoEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }

    /**
     *
     * @param entity
     * @return
     */
    public MusicoEntity update(MusicoEntity entity) {
       
        return em.merge(entity);
    }

    /**
     *
     * @param id
     */
    public void delete(Long id) {
        
        MusicoEntity entity = em.find(MusicoEntity.class, id);
        em.remove(entity);
    }
    
}
