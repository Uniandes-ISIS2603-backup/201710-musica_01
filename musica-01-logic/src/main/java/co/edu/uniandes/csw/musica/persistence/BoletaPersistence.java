/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.persistence;

import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class BoletaPersistence {
    
    @PersistenceContext(unitName="musicaPU") 
    protected EntityManager em;
    
    public BoletaEntity find(Long id)    {
        return em.find(BoletaEntity.class, id);
    }
    
    public List<BoletaEntity> findAll()    {
        TypedQuery<BoletaEntity> q = em.createQuery("select c from BoletaEntity c", BoletaEntity.class);
        return q.getResultList();
    }
    
    public BoletaEntity create(BoletaEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }

    public BoletaEntity update(BoletaEntity entity) {
       
        return em.merge(entity);
    }

    public void delete(Long id) {
        
        BoletaEntity entity = em.find(BoletaEntity.class, id);
        em.remove(entity);
    }

}