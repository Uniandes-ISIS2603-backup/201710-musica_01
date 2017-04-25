/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.persistence;

import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CiudadPersistence {
    
    @PersistenceContext(unitName = "musicaPU")
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
