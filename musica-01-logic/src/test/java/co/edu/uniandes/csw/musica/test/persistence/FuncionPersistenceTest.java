/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.test.persistence;

import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.persistence.FuncionPersistence;
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
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author la.herrera11
 */
@RunWith(Arquillian.class)
public class FuncionPersistenceTest {

    /**
     *
     */
    public static final String DEPLOY = "PruebaFuncion";

    /**
     *
     * @return
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(FuncionEntity.class.getPackage())
                .addPackage(FuncionPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private FuncionPersistence funcionPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

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
        em.createQuery("delete from FuncionEntity").executeUpdate();
    }

    private List<FuncionEntity> data = new ArrayList<FuncionEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            FuncionEntity entity = factory.manufacturePojo(FuncionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Funcion.
     */
    @Test
    public void createFuncionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FuncionEntity newEntity = factory.manufacturePojo(FuncionEntity.class);
        FuncionEntity result = funcionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        FuncionEntity entity = em.find(FuncionEntity.class, result.getId());

        Assert.assertEquals(entity.getAprobada(), newEntity.getAprobada());
        Assert.assertEquals(entity.getCalificacion(), newEntity.getCalificacion());
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para consultar la lista de Funciones.
     */
    @Test
    public void getFuncionesTest() {
        List<FuncionEntity> list = funcionPersistence.findAll();
        Assert.assertEquals(list.size(),data.size());
        for (FuncionEntity ent : list) {
            boolean found = false;
            for (FuncionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Funcion.
     */
    @Test
    public void getFuncionTest() {
        FuncionEntity entity = data.get(0);
        FuncionEntity newEntity = funcionPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getAprobada(), newEntity.getAprobada());
        Assert.assertEquals(entity.getCalificacion(), newEntity.getCalificacion());
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para eliminar un Musico.
     */
    @Test
    public void deleteFuncionTest() {
        FuncionEntity entity = data.get(0);
        funcionPersistence.delete(entity.getId());
        FuncionEntity deleted = em.find(FuncionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Funcion.
     */
    @Test
    public void updateFuncionTest() {
        FuncionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FuncionEntity newEntity = factory.manufacturePojo(FuncionEntity.class);
        newEntity.setId(entity.getId());

        funcionPersistence.update(newEntity);

        FuncionEntity resp = em.find(FuncionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getAprobada(), resp.getAprobada());
        Assert.assertEquals(newEntity.getCalificacion(), resp.getCalificacion());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
}
