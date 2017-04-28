/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.test.persistence;

import co.edu.uniandes.csw.musica.entities.LugarEntity;
import co.edu.uniandes.csw.musica.persistence.LugarPersistence;
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
public class LugarPersistenceTest {

    /**
     *
     */
    public static final String DEPLOY = "PruebaLugar";

    /**
     *
     * @return
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(LugarEntity.class.getPackage())
                .addPackage(LugarPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private LugarPersistence lugarPersistence;

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
        em.createQuery("delete from LugarEntity").executeUpdate();
    }

    private List<LugarEntity> data = new ArrayList<LugarEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            LugarEntity entity = factory.manufacturePojo(LugarEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Lugar.
     */
    @Test
    public void createLugarTest() {
        PodamFactory factory = new PodamFactoryImpl();
        LugarEntity newEntity = factory.manufacturePojo(LugarEntity.class);
        LugarEntity result = lugarPersistence.create(newEntity);

        Assert.assertNotNull(result);

        LugarEntity entity = em.find(LugarEntity.class, result.getId());

        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getAbierto(), newEntity.getAbierto());
        Assert.assertEquals(entity.getCapacidad(), newEntity.getCapacidad());
        Assert.assertEquals(entity.getCapacidadSonido(), newEntity.getCapacidadSonido());
        Assert.assertEquals(entity.getCostoEconomico(), newEntity.getCostoEconomico());
        Assert.assertEquals(entity.getCostoPreferencial(), newEntity.getCostoPreferencial());
    }

    /**
     * Prueba para consultar la lista de Lugares.
     */
    @Test
    public void getLugaresTest() {
        List<LugarEntity> list = lugarPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (LugarEntity ent : list) {
            boolean found = false;
            for (LugarEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Lugar.
     */
    @Test
    public void getLugarTest() {
        LugarEntity entity = data.get(0);
        LugarEntity newEntity = lugarPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getAbierto(), newEntity.getAbierto());
        Assert.assertEquals(entity.getCapacidad(), newEntity.getCapacidad());
        Assert.assertEquals(entity.getCapacidadSonido(), newEntity.getCapacidadSonido());
        Assert.assertEquals(entity.getCostoEconomico(), newEntity.getCostoEconomico());
        Assert.assertEquals(entity.getCostoPreferencial(), newEntity.getCostoPreferencial());
    }

    /**
     * Prueba para eliminar un Lugar.
     */
    @Test
    public void deleteLugarTest() {
        LugarEntity entity = data.get(0);
        lugarPersistence.delete(entity.getId());
        LugarEntity deleted = em.find(LugarEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Lugar.
     */
    @Test
    public void updateLugarTest() {
        LugarEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LugarEntity newEntity = factory.manufacturePojo(LugarEntity.class);
        newEntity.setId(entity.getId());

        lugarPersistence.update(newEntity);

        LugarEntity resp = em.find(LugarEntity.class, entity.getId());

        Assert.assertEquals(resp.getNombre(), newEntity.getNombre());
        Assert.assertEquals(resp.getAbierto(), newEntity.getAbierto());
        Assert.assertEquals(resp.getCapacidad(), newEntity.getCapacidad());
        Assert.assertEquals(resp.getCapacidadSonido(), newEntity.getCapacidadSonido());
        Assert.assertEquals(resp.getCostoEconomico(), newEntity.getCostoEconomico());
        Assert.assertEquals(resp.getCostoPreferencial(), newEntity.getCostoPreferencial());
    }
}
