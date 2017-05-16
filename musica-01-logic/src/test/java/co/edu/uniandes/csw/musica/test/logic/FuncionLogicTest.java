/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.test.logic;
import co.edu.uniandes.csw.musica.ejbs.FuncionLogic;
import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.FuncionPersistence;
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
public class FuncionLogicTest {
    
    public static final String DEPLOY = "PruebaLogicFuncion";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() 
    {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(FuncionEntity.class.getPackage())
                .addPackage(FuncionLogic.class.getPackage())
                .addPackage(FuncionPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private FuncionLogic funcionLogic;

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
        em.createQuery("delete from FuncionEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<FuncionEntity> data = new ArrayList<FuncionEntity>();

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
            FuncionEntity entity = factory.manufacturePojo(FuncionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Funcion.
     *
     * @generated
     */
    @Test
    public void createFuncionTest() 
    {
        try 
        {
            PodamFactory factory = new PodamFactoryImpl();
            FuncionEntity entity = factory.manufacturePojo(FuncionEntity.class);
            FuncionEntity result = funcionLogic.createFuncion(entity);
            Assert.assertNotNull(result);
            Assert.assertEquals(entity.getAprobada(), result.getAprobada());
            Assert.assertEquals(entity.getCalificacion(), result.getCalificacion());
            Assert.assertEquals(entity.getId(), result.getId());
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(FuncionLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para consultar un Funcion.
     *
     * @throws co.edu.uniandes.csw.musica.exceptions.BusinessLogicException
     * @generated
     */
    @Test
    public void getFuncionTest() throws BusinessLogicException 
    {
        FuncionEntity entity = data.get(0);
        FuncionEntity resultEntity = funcionLogic.getFuncion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getAprobada(), resultEntity.getAprobada());
        Assert.assertEquals(entity.getCalificacion(), resultEntity.getCalificacion());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para consultar la lista de las Funciones.
     *
     * @generated
     */
    @Test
    public void getFuncionesTest() 
    {
        List<FuncionEntity> list = funcionLogic.getFunciones();
        Assert.assertEquals(data.size(), list.size());
        for (FuncionEntity entity : list) {
            boolean found = false;
            for (FuncionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para eliminar un Funcion.
     *
     * @generated
     */
    @Test
    public void deleteFuncionTest() 
    {
        try 
        {
            FuncionEntity entity = data.get(0);
            funcionLogic.deleteFuncion(entity.getId());
            FuncionEntity deleted = em.find(FuncionEntity.class, entity.getId());
            Assert.assertNull(deleted);
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(FuncionLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para actualizar una Funcion.
     *
     * @generated
     */
    @Test
    public void updateFuncionTest() 
    {
        try 
        {
            FuncionEntity entity = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            FuncionEntity pojoEntity = factory.manufacturePojo(FuncionEntity.class);
            pojoEntity.setId(entity.getId());
            
            funcionLogic.updateFuncion(pojoEntity);
            
            FuncionEntity resp = em.find(FuncionEntity.class, entity.getId());
            
            Assert.assertEquals(entity.getAprobada(), resp.getAprobada());
            Assert.assertEquals(entity.getCalificacion(), resp.getCalificacion());
            Assert.assertEquals(entity.getId(), resp.getId());
            
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(FuncionLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
