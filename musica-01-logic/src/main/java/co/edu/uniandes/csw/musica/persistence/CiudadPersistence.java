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

/**
 *
 * @author jc.bustamante143
 */
@Stateless
public class CiudadPersistence {
    
    /**
     *
     */
    @PersistenceContext(unitName = "musicaPU")
    protected EntityManager em;
    
    /**
     *
     * @param id
     * @return
     */
    public CiudadEntity find(Long id) {
        return em.find(CiudadEntity.class, id);
    }
    
    /**
     *
     * @return
     */
    public List<CiudadEntity> findAll() {
        TypedQuery<CiudadEntity> q = em.createQuery(
                "SELECT c FROM CiudadEntity c",
                CiudadEntity.class);
        return q.getResultList();
    }
    
    /**
     *
     * @param c
     * @return
     */
    public CiudadEntity create(CiudadEntity c) {
        em.persist(c);
        return c;
    }
    
    /**
     *
     * @param c
     * @return
     */
    public CiudadEntity update(CiudadEntity c) {
        return em.merge(c);
    }
    
    /**
     *
     * @param id
     */
    public void delete(Long id) {
        CiudadEntity c = em.find(CiudadEntity.class, id);
        em.remove(c);
    }
}
