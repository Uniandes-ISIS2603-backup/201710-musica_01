// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author c.potdevin10
 */
@Stateless
public class LugarPersistence {
    
    @PersistenceContext(unitName = "musicaPU")
    protected EntityManager em;
    
    public LugarEntity find(Long id) {
        return em.find(LugarEntity.class, id);
    }
    
    public List<LugarEntity> findAll() {
        TypedQuery<LugarEntity> q = em.createQuery(
                "SELECT l FROM LugarEntity l",
                LugarEntity.class);
        return q.getResultList();
    }
    
    public LugarEntity create(LugarEntity l) {
        em.persist(l);
        return l;
    }
    
    public LugarEntity update(LugarEntity l) {
        return em.merge(l);
    }
    
    public void delete(Long id) {
        LugarEntity l = em.find(LugarEntity.class, id);
        em.remove(l);
    }
}
