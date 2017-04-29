/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.test.logic;

import co.edu.uniandes.csw.musica.ejbs.LugarLogic;
import co.edu.uniandes.csw.musica.entities.LugarEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.LugarPersistence;
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
public class LugarLogicTest {

    public static final String DEPLOY = "PruebaLogicLugar";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() 
    {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(LugarEntity.class.getPackage())
                .addPackage(LugarLogic.class.getPackage())
                .addPackage(LugarPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private LugarLogic lugarLogic;

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
        em.createQuery("delete from LugarEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<LugarEntity> data = new ArrayList<LugarEntity>();

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
            LugarEntity entity = factory.manufacturePojo(LugarEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Lugar.
     *
     * @generated
     */
    @Test
    public void createLugarTest() 
    {
        try 
        {
            PodamFactory factory = new PodamFactoryImpl();
            LugarEntity entity = factory.manufacturePojo(LugarEntity.class);
            LugarEntity result = lugarLogic.createLugar(entity);
            Assert.assertNotNull(result);
            Assert.assertEquals(entity.getNombre(), result.getNombre());
            Assert.assertEquals(entity.getAbierto(), result.getAbierto());
            Assert.assertEquals(entity.getCapacidad(), result.getCapacidad());
            Assert.assertEquals(entity.getCapacidadSonido(), result.getCapacidadSonido());
            Assert.assertEquals(entity.getCostoEconomico(), result.getCostoEconomico());
            Assert.assertEquals(entity.getCostoPreferencial(), result.getCostoPreferencial());
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(LugarLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para consultar la lista de Lugares.
     *
     * @generated
     */
    @Test
    public void getLugaresTest() 
    {
        List<LugarEntity> list = lugarLogic.getLugares();
        Assert.assertEquals(data.size(), list.size());
        for (LugarEntity entity : list) {
            boolean found = false;
            for (LugarEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Lugar.
     *
     * @generated
     */
    @Test
    public void getLugarTest() 
    {
        LugarEntity entity = data.get(0);
        LugarEntity resultEntity = lugarLogic.getLugar(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getAbierto(), resultEntity.getAbierto());
        Assert.assertEquals(entity.getCapacidad(), resultEntity.getCapacidad());
        Assert.assertEquals(entity.getCapacidadSonido(), resultEntity.getCapacidadSonido());
        Assert.assertEquals(entity.getCostoEconomico(), resultEntity.getCostoEconomico());
        Assert.assertEquals(entity.getCostoPreferencial(), resultEntity.getCostoPreferencial());
    }

    /**
     * Prueba para eliminar un Lugar.
     *
     * @generated
     */
    @Test
    public void deleteLugarTest() 
    {
        try 
        {
            LugarEntity entity = data.get(0);
            lugarLogic.deleteLugar(entity.getId());
            LugarEntity deleted = em.find(LugarEntity.class, entity.getId());
            Assert.assertNull(deleted);
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(LugarLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para actualizar un Lugar.
     *
     * @generated
     */
    @Test
    public void updateLugarTest() 
    {
        try 
        {
            LugarEntity entity = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            LugarEntity pojoEntity = factory.manufacturePojo(LugarEntity.class);
            pojoEntity.setId(entity.getId());
            
            lugarLogic.updateLugar(pojoEntity);
            
            LugarEntity resp = em.find(LugarEntity.class, entity.getId());
            
            Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
            Assert.assertEquals(pojoEntity.getAbierto(), resp.getAbierto());
            Assert.assertEquals(pojoEntity.getCapacidad(), resp.getCapacidad());
            Assert.assertEquals(pojoEntity.getCapacidadSonido(), resp.getCapacidadSonido());
            Assert.assertEquals(pojoEntity.getCostoEconomico(), resp.getCostoEconomico());
            Assert.assertEquals(pojoEntity.getCostoPreferencial(), resp.getCostoPreferencial());
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(LugarLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

