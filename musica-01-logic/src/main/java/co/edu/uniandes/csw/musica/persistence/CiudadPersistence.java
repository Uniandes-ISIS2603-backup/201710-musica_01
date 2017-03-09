/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.persistence;

import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author c.potdevin10
 */
@Stateless
public class CiudadPersistence {
    
    @PersistenceContext(unitName = "employeePU")
    protected EntityManager em;
    
    public CiudadEntity find(Long id) {
        return em.find(CiudadEntity.class, id);
    }
    
    public List<CiudadEntity> findAll() {
        TypedQuery<CiudadEntity> q = em.createQuery(
                "SELECT c FROM CiudadEntity c",
                CiudadEntity.class);
        return q.getResultList();
    }
    
    public CiudadEntity create(CiudadEntity c) {
        em.persist(c);
        return c;
    }
    
    public CiudadEntity update(CiudadEntity c) {
        return em.merge(c);
    }
    
    public void delete(Long id) {
        CiudadEntity c = em.find(CiudadEntity.class, id);
        em.remove(c);
    }
}
