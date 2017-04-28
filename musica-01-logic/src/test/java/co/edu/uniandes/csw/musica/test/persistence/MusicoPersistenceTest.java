/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.test.persistence;

import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import co.edu.uniandes.csw.musica.persistence.MusicoPersistence;
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
public class MusicoPersistenceTest {

    /**
     *
     */
    public static final String DEPLOY = "PruebaMusico";

    /**
     *
     * @return
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(MusicoEntity.class.getPackage())
                .addPackage(MusicoPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private MusicoPersistence musicoPersistence;

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
        em.createQuery("delete from MusicoEntity").executeUpdate();
    }

    private List<MusicoEntity> data = new ArrayList<MusicoEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            MusicoEntity entity = factory.manufacturePojo(MusicoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Musico.
     */
    @Test
    public void createMusicoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MusicoEntity newEntity = factory.manufacturePojo(MusicoEntity.class);
        MusicoEntity result = musicoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MusicoEntity entity = em.find(MusicoEntity.class, result.getId());

        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getTrayectoria(), newEntity.getTrayectoria());
        Assert.assertEquals(entity.getRequerimientoCapacidad(), newEntity.getRequerimientoCapacidad());
        Assert.assertEquals(entity.getRequerimientoSonido(), newEntity.getRequerimientoSonido());
    }

    /**
     * Prueba para consultar la lista de Musicos.
     */
    @Test
    public void getMusicosTest() {
        List<MusicoEntity> list = musicoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MusicoEntity ent : list) {
            boolean found = false;
            for (MusicoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Musico.
     */
    @Test
    public void getMusicoTest() {
        MusicoEntity entity = data.get(0);
        MusicoEntity newEntity = musicoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getTrayectoria(), newEntity.getTrayectoria());
        Assert.assertEquals(entity.getRequerimientoCapacidad(), newEntity.getRequerimientoCapacidad());
        Assert.assertEquals(entity.getRequerimientoSonido(), newEntity.getRequerimientoSonido());
    }

    /**
     * Prueba para eliminar un Musico.
     */
    @Test
    public void deleteMusicoTest() {
        MusicoEntity entity = data.get(0);
        musicoPersistence.delete(entity.getId());
        MusicoEntity deleted = em.find(MusicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Musico.
     */
    @Test
    public void updateMusicoTest() {
        MusicoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MusicoEntity newEntity = factory.manufacturePojo(MusicoEntity.class);
        newEntity.setId(entity.getId());

        musicoPersistence.update(newEntity);

        MusicoEntity resp = em.find(MusicoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getTrayectoria(), resp.getTrayectoria());
        Assert.assertEquals(newEntity.getRequerimientoCapacidad(), resp.getRequerimientoCapacidad());
        Assert.assertEquals(newEntity.getRequerimientoSonido(), resp.getRequerimientoSonido());
    }
}
