/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.test.logic;

import co.edu.uniandes.csw.musica.ejbs.MusicoLogic;
import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.MusicoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
/**
 * @generated
 */
@RunWith(Arquillian.class)
public class MusicoLogicTest {

    public static final String DEPLOY = "PruebaLogicMusico";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() 
    {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(MusicoEntity.class.getPackage())
                .addPackage(MusicoLogic.class.getPackage())
                .addPackage(MusicoPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private MusicoLogic musicoLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() 
    {
        try 
        {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            try 
            {
                utx.rollback();
            } 
            catch (Exception e1) 
            {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from MusicoEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<MusicoEntity> data = new ArrayList<MusicoEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) 
        {
            PodamFactory factory = new PodamFactoryImpl();
            MusicoEntity entity = factory.manufacturePojo(MusicoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Musico.
     *
     * @generated
     */
    @Test
    public void createMusicoTest() 
    {
        try 
        {
            PodamFactory factory = new PodamFactoryImpl();
            MusicoEntity entity = factory.manufacturePojo(MusicoEntity.class);
            MusicoEntity result = musicoLogic.createMusico(entity);
            Assert.assertNotNull(result);
            Assert.assertEquals(entity.getNombre(), result.getNombre());
            Assert.assertEquals(entity.getTrayectoria(), result.getTrayectoria());
            Assert.assertEquals(entity.getRequerimientoCapacidad(), result.getRequerimientoCapacidad());
            Assert.assertEquals(entity.getRequerimientoSonido(), result.getRequerimientoSonido());
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(MusicoLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para consultar la lista de Musicos.
     *
     * @generated
     */
    @Test
    public void getMusicosTest() 
    {
        List<MusicoEntity> list = musicoLogic.getMusicos();
        Assert.assertEquals(data.size(), list.size());
        for (MusicoEntity entity : list) {
            boolean found = false;
            for (MusicoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Musico.
     *
     * @generated
     */
    @Test
    public void getMusicoTest() 
    {
        MusicoEntity entity = data.get(0);
        MusicoEntity resultEntity = musicoLogic.getMusico(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getTrayectoria(), resultEntity.getTrayectoria());
        Assert.assertEquals(entity.getRequerimientoCapacidad(), resultEntity.getRequerimientoCapacidad());
        Assert.assertEquals(entity.getRequerimientoSonido(), resultEntity.getRequerimientoSonido());
    }

    /**
     * Prueba para eliminar un Musico.
     *
     * @generated
     */
    @Test
    public void deleteMusicoTest() 
    {
        try 
        {
            MusicoEntity entity = data.get(0);
            musicoLogic.deleteMusico(entity.getId());
            MusicoEntity deleted = em.find(MusicoEntity.class, entity.getId());
            Assert.assertNull(deleted);
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(MusicoLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para actualizar un Musico.
     *
     * @generated
     */
    @Test
    public void updateMusicoTest() 
    {
        try 
        {
            MusicoEntity entity = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            MusicoEntity pojoEntity = factory.manufacturePojo(MusicoEntity.class);
            pojoEntity.setId(entity.getId());
            
            musicoLogic.updateMusico(pojoEntity);
            
            MusicoEntity resp = em.find(MusicoEntity.class, entity.getId());
            
            Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
            Assert.assertEquals(pojoEntity.getTrayectoria(), resp.getTrayectoria());
            Assert.assertEquals(pojoEntity.getRequerimientoCapacidad(), resp.getRequerimientoCapacidad());
            Assert.assertEquals(pojoEntity.getRequerimientoSonido(), resp.getRequerimientoSonido());
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(MusicoLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
