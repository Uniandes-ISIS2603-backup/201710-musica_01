/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.persistence;

import co.edu.uniandes.csw.musica.entities.ClienteEntity;
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
public class ClientePersistence {
    @PersistenceContext(unitName="employeePU") 
    protected EntityManager em;
    
    public ClienteEntity find(Long id)    {
        return em.find(ClienteEntity.class, id);
    }
    
    public List<ClienteEntity> findAll()    {
        TypedQuery<ClienteEntity> q = em.createQuery("select c from ClienteEntity c", ClienteEntity.class);
        return q.getResultList();
    }
    
    public ClienteEntity create(ClienteEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }

    public ClienteEntity update(ClienteEntity entity) {
       
        return em.merge(entity);
    }

    public void delete(Long id) {
        
        ClienteEntity entity = em.find(ClienteEntity.class, id);
        em.remove(entity);
    }
}
