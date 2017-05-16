/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.test.persistence;

import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import co.edu.uniandes.csw.musica.persistence.BoletaPersistence;
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
 * @author la.herrera11
 */
@RunWith(Arquillian.class)
public class BoletaPersistenceTest {

    /**
     *
     */
    public static final String DEPLOY = "PruebaBoleta";

    /**
     *
     * @return
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(BoletaEntity.class.getPackage())
                .addPackage(BoletaPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private BoletaPersistence boletaPersistence;
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
        em.createQuery("delete from BoletaEntity").executeUpdate();
    }

    private List<BoletaEntity> data = new ArrayList<BoletaEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            BoletaEntity entity = factory.manufacturePojo(BoletaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Boleta.
     */
    @Test
    public void createBoletaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        BoletaEntity newEntity = factory.manufacturePojo(BoletaEntity.class);
        BoletaEntity result = boletaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        BoletaEntity entity = em.find(BoletaEntity.class, result.getId());
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getCliente(), newEntity.getCliente());
        Assert.assertEquals(entity.getFuncion(), newEntity.getFuncion());
        Assert.assertEquals(entity.getPrecio(), newEntity.getPrecio());
    }

    /**
     * Prueba para consultar la lista de Boletas.
     */
    @Test
    public void getBoletasTest() {
        List<BoletaEntity> list = boletaPersistence.findAll();
       Assert.assertEquals(data.size(), list.size());
        for (BoletaEntity ent : list) {
            boolean found = false;
            for (BoletaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Boleta.
     */
    @Test
    public void getBoletaTest() {
        BoletaEntity entity = data.get(0);
        BoletaEntity newEntity = boletaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPrecio(), newEntity.getPrecio());
    }

    /**
     * Prueba para eliminar una Boleta.
     */
    @Test
    public void deleteBoletaTest() {
        BoletaEntity entity = data.get(0);
        boletaPersistence.delete(entity.getId());
        BoletaEntity deleted = em.find(BoletaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Boleta.
     */
    @Test
    public void updateCiudadTest() {
        BoletaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        BoletaEntity newEntity = factory.manufacturePojo(BoletaEntity.class);
        newEntity.setId(entity.getId());

        boletaPersistence.update(newEntity);

        BoletaEntity resp = em.find(BoletaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
}