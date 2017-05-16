/* 
 * Copyright (c) 2017 la.herrera11.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.persistence;

import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author la.herrera11
 */
@Stateless
public class FuncionPersistence {
    @PersistenceContext(unitName="musicaPU") 
    protected EntityManager em;
    
    /**
     *
     * @param id
     * @return
     */
    public FuncionEntity find(Long id)    {
        return em.find(FuncionEntity.class, id);
    }
    
    /**
     *
     * @return
     */
    public List<FuncionEntity> findAll()    {
        TypedQuery<FuncionEntity> q = em.createQuery("select c from FuncionEntity c", FuncionEntity.class);
        return q.getResultList();
    }
    
    /**
     *
     * @param entity
     * @return
     */
    public FuncionEntity create(FuncionEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }

    /**
     *
     * @param entity
     * @return
     */
    public FuncionEntity update(FuncionEntity entity) {
       
        return em.merge(entity);
    }

    /**
     *
     * @param id
     */
    public void delete(Long id) {
        
        FuncionEntity entity = em.find(FuncionEntity.class, id);
        em.remove(entity);
    }
}
