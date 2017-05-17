/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.test.logic;

import co.edu.uniandes.csw.musica.ejbs.CiudadLogic;
import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.CiudadPersistence;
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
public class CiudadLogicTest {

    public static final String DEPLOY = "PruebaLogicCiudad";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment()
    {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(CiudadEntity.class.getPackage())
                .addPackage(CiudadLogic.class.getPackage())
                .addPackage(CiudadPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private CiudadLogic ciudadLogic;

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
        em.createQuery("delete from CiudadEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<CiudadEntity> data = new ArrayList<CiudadEntity>();

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
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Ciudad.
     *
     * @generated
     */
    @Test
    public void createCiudadTest()
    {
        try
        {
            PodamFactory factory = new PodamFactoryImpl();
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);
            CiudadEntity result = ciudadLogic.createCiudad(entity);
            Assert.assertNotNull(result);
            Assert.assertEquals(result.getNombre(), entity.getNombre());
        }
        catch (BusinessLogicException ex)
        {
            Logger.getLogger(CiudadLogicTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail("No debe llegar acá");
        }
    }

    /**
     * Prueba para consultar la lista de Ciudades.
     *
     * @generated
     */
    @Test
    public void getCiudadesTest()
    {
        List<CiudadEntity> list = ciudadLogic.getCiudades();
        Assert.assertEquals(data.size(), list.size());
        for (CiudadEntity entity : list)
        {
            boolean found = false;
            for (CiudadEntity storedEntity : data)
            {
                if (entity.getId().equals(storedEntity.getId()))
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Ciudad.
     *
     * @generated
     */
    @Test
    public void getCiudadTest()
    {
        CiudadEntity entity = data.get(0);
        CiudadEntity resultEntity = ciudadLogic.getCiudad(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }

    /**
     * Prueba para eliminar un Ciudad.
     *
     * @generated
     */
    @Test
    public void deleteCiudadTest()
    {
        try
        {
            CiudadEntity entity = data.get(0);
            ciudadLogic.deleteCiudad(entity.getId());
            CiudadEntity deleted = em.find(CiudadEntity.class, entity.getId());
            Assert.assertNull(deleted);
        }
        catch (BusinessLogicException ex)
        {
            Logger.getLogger(CiudadLogicTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail("No debe llegar acá");
        }
    }

    /**
     * Prueba para actualizar un Ciudad.
     *
     * @generated
     */
    @Test
    public void updateCiudadTest()
    {
        try
        {
            CiudadEntity entity = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            CiudadEntity pojoEntity = factory.manufacturePojo(CiudadEntity.class);
            pojoEntity.setId(entity.getId());

            ciudadLogic.updateCiudad(pojoEntity);

            CiudadEntity resp = em.find(CiudadEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        }
        catch (BusinessLogicException ex)
        {
            Logger.getLogger(CiudadLogicTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail("No debe llegar acá");
        }
    }

}
