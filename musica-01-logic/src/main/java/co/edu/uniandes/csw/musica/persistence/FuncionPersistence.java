/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    @PersistenceContext(unitName="employeePU") 
    protected EntityManager em;
    
    public FuncionEntity find(Long id)    {
        return em.find(FuncionEntity.class, id);
    }
    
    public List<FuncionEntity> findAll()    {
        TypedQuery<FuncionEntity> q = em.createQuery("select c from FuncionEntity c", FuncionEntity.class);
        return q.getResultList();
    }
    
    public FuncionEntity create(FuncionEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }

    public FuncionEntity update(FuncionEntity entity) {
       
        return em.merge(entity);
    }

    public void delete(Long id) {
        
        FuncionEntity entity = em.find(FuncionEntity.class, id);
        em.remove(entity);
    }
}