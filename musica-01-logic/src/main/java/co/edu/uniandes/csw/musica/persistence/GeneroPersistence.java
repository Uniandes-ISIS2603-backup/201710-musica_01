/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.persistence;

import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class GeneroPersistence {
    
    @PersistenceContext(unitName="musicaPU") 
    protected EntityManager em;
    
    public GeneroEntity find(Long id)    {
        return em.find(GeneroEntity.class, id);
    }
    
    public List<GeneroEntity> findAll()    {
        TypedQuery<GeneroEntity> q = em.createQuery("select c from GeneroEntity c", GeneroEntity.class);
        return q.getResultList();
    }
    
    public GeneroEntity create(GeneroEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }

    public GeneroEntity update(GeneroEntity entity) {
       
        return em.merge(entity);
    }

    public void delete(Long id) {
        
        GeneroEntity entity = em.find(GeneroEntity.class, id);
        em.remove(entity);
    }
}
