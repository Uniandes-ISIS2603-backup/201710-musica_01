/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.persistence;

/**
 *
 * @author pa.alvarado10
 */
import co.edu.uniandes.csw.musica.entities.AdministradorEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class AdministradorPersistence {
    
    @PersistenceContext(unitName="employeePU") 
    protected EntityManager em;
    
    public AdministradorEntity find(Long id)    {
        return em.find(AdministradorEntity.class, id);
    }
    
    public List<AdministradorEntity> findAll()    {
        TypedQuery<AdministradorEntity> q = em.createQuery("select u from AdministradorEntity u", AdministradorEntity.class);
        return q.getResultList();
    }
    
    public AdministradorEntity create(AdministradorEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }

    public AdministradorEntity update(AdministradorEntity entity) {
       
        return em.merge(entity);
    }

    public void delete(Long id) {
        
    	AdministradorEntity entity = em.find(AdministradorEntity.class, id);
        em.remove(entity);
    }
}

