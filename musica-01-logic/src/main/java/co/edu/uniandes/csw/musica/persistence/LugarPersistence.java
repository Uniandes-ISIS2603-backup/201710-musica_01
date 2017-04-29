/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.persistence;

import co.edu.uniandes.csw.musica.entities.LugarEntity;
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
public class LugarPersistence {
    
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
    public LugarEntity find(Long id) {
        return em.find(LugarEntity.class, id);
    }
    
    /**
     *
     * @return
     */
    public List<LugarEntity> findAll() {
        TypedQuery<LugarEntity> q = em.createQuery(
                "SELECT l FROM LugarEntity l",
                LugarEntity.class);
        return q.getResultList();
    }
    
    /**
     *
     * @param l
     * @return
     */
    public LugarEntity create(LugarEntity l) {
        em.persist(l);
        return l;
    }
    
    /**
     *
     * @param l
     * @return
     */
    public LugarEntity update(LugarEntity l) {
        return em.merge(l);
    }
    
    /**
     *
     * @param id
     */
    public void delete(Long id) {
        LugarEntity l = em.find(LugarEntity.class, id);
        em.remove(l);
    }
}
