/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.persistence;

import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UsuarioPersistence {

    @PersistenceContext(unitName = "musicaPU")
    protected EntityManager em;

    public UsuarioEntity find(Long id) {
        return em.find(UsuarioEntity.class, id);
    }

    public List<UsuarioEntity> findAll() {
        TypedQuery<UsuarioEntity> q = em.createQuery("select u from UsuarioEntity u", UsuarioEntity.class);
        return q.getResultList();
    }

    public UsuarioEntity create(UsuarioEntity entity) {
        em.persist(entity);
        return entity;
    }

    public UsuarioEntity update(UsuarioEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        UsuarioEntity entity = em.find(UsuarioEntity.class, id);
        em.remove(entity);
    }
}
