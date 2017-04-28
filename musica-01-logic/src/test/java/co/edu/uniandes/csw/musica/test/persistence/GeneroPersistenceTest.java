/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.test.persistence;

import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.persistence.GeneroPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jc.bustamante143
 */
@RunWith(Arquillian.class)
public class GeneroPersistenceTest {

    /**
     *
     */
    public static final String DEPLOY = "PruebaGenero";

    /**
     *
     * @return
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(GeneroEntity.class.getPackage())
                .addPackage(GeneroPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private GeneroPersistence generoPersistence;

    @PersistenceContext 
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<GeneroEntity> data = new ArrayList<GeneroEntity>();

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from GeneroEntity").executeUpdate();
    }

    

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            GeneroEntity entity = factory.manufacturePojo(GeneroEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Genero.
     */
    @Test
    public void createGeneroTest() {
        PodamFactory factory = new PodamFactoryImpl();
        GeneroEntity newEntity = factory.manufacturePojo(GeneroEntity.class);
        GeneroEntity result = generoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        GeneroEntity entity = em.find(GeneroEntity.class, result.getId());

        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Prueba para consultar la lista de Generos.
     */
    @Test
    public void getGenerosTest() {
        List<GeneroEntity> list = generoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (GeneroEntity ent : list) {
            boolean found = false;
            for (GeneroEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Genero.
     */
    @Test
    public void getGeneroTest() {
        GeneroEntity entity = data.get(0);
        GeneroEntity newEntity = generoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Prueba para eliminar un Genero.
     */
    @Test
    public void deleteGeneroTest() {
        GeneroEntity entity = data.get(0);
        generoPersistence.delete(entity.getId());
        GeneroEntity deleted = em.find(GeneroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Genero.
     */
    @Test
    public void updateGeneroTest() {
        GeneroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        GeneroEntity newEntity = factory.manufacturePojo(GeneroEntity.class);
        newEntity.setId(entity.getId());

        generoPersistence.update(newEntity);

        GeneroEntity resp = em.find(GeneroEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
}
