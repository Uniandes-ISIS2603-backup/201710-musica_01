/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.test.logic;


import co.edu.uniandes.csw.musica.ejbs.GeneroLogic;
import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.GeneroPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class GeneroLogicTest {

    public static final String DEPLOY = "PruebaLogicGenero";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() 
    {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(GeneroEntity.class.getPackage())
                .addPackage(GeneroLogic.class.getPackage())
                .addPackage(GeneroPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private GeneroLogic generoLogic;

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
        em.createQuery("delete from GeneroEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<GeneroEntity> data = new ArrayList<GeneroEntity>();

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
            GeneroEntity entity = factory.manufacturePojo(GeneroEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Genero.
     *
     * @generated
     */
    @Test
    public void createGeneroTest() 
    {
        try 
        {
            PodamFactory factory = new PodamFactoryImpl();
            GeneroEntity entity = factory.manufacturePojo(GeneroEntity.class);
            GeneroEntity result = generoLogic.createGenero(entity);
            Assert.assertNotNull(result);
            Assert.assertEquals(result.getNombre(), entity.getNombre());
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(GeneroLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para consultar la lista de Generos.
     *
     * @generated
     */
    @Test
    public void getGenerosTest() 
    {
        List<GeneroEntity> list = generoLogic.getGeneros();
        Assert.assertEquals(data.size(), list.size());
        for (GeneroEntity entity : list) {
            boolean found = false;
            for (GeneroEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Genero.
     *
     * @generated
     */
    @Test
    public void getGeneroTest() 
    {
        GeneroEntity entity = data.get(0);
        GeneroEntity resultEntity = generoLogic.getGenero(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }

    /**
     * Prueba para eliminar un Genero.
     *
     * @generated
     */
    @Test
    public void deleteGeneroTest() 
    {
        try 
        {
            GeneroEntity entity = data.get(0);
            generoLogic.deleteGenero(entity.getId());
            GeneroEntity deleted = em.find(GeneroEntity.class, entity.getId());
            Assert.assertNull(deleted);
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(GeneroLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para actualizar un Genero.
     *
     * @generated
     */
    @Test
    public void updateGeneroTest() 
    {
        try 
        {
            GeneroEntity entity = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            GeneroEntity pojoEntity = factory.manufacturePojo(GeneroEntity.class);
            pojoEntity.setId(entity.getId());
            
            generoLogic.updateGenero(pojoEntity);
            
            GeneroEntity resp = em.find(GeneroEntity.class, entity.getId());
            
            Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(GeneroLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
