/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.test.logic;
import co.edu.uniandes.csw.musica.ejbs.FestivalLogic;
import co.edu.uniandes.csw.musica.entities.FestivalEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.FestivalPersistence;
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
 * @author pa.alvarado10
 */
@RunWith(Arquillian.class)
public class FestivalLogicTest {
    
    public static final String DEPLOY ="PruebaLogicFestival";
    
    @Deployment
    public static WebArchive createDeployment()
    {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(FestivalEntity.class.getPackage())
                .addPackage(FestivalLogic.class.getPackage())
                .addPackage(FestivalPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    
    }
    
    @Inject 
    private FestivalLogic festivalLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    
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
    
    private void clearData(){
        em.createQuery("delete from FestivalEntity").executeUpdate();
    }
    
    private List<FestivalEntity> data = new ArrayList<FestivalEntity>();
    
    private void insertData(){
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            FestivalEntity entity = factory.manufacturePojo(FestivalEntity.class);

            em.persist(entity);
            data.add(entity);
            
        }
    }
    
    @Test
    public void createFestivalTest() 
    {
        try 
        {
            PodamFactory factory = new PodamFactoryImpl();
            FestivalEntity entity = factory.manufacturePojo(FestivalEntity.class);
            FestivalEntity result = festivalLogic.createFestival(entity);
            Assert.assertNotNull(result);
            Assert.assertEquals(entity.getNombre(), result.getNombre());
            Assert.assertEquals(entity.getFechaInicio(), result.getFechaInicio());
            Assert.assertEquals(entity.getFechafin() , result.getFechafin() );
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(FestivalLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para consultar la lista de Festivales.
     *
     * @generated
     */
    @Test
    public void getFestivalesTest() 
    {
        List<FestivalEntity> list = festivalLogic.getFestivales();
        Assert.assertEquals(data.size(), list.size());
        for (FestivalEntity entity : list) {
            boolean found = false;
            for (FestivalEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /** 
     * Prueba para consultar un Festival.
     *
     * @generated
     */
    @Test
    public void getFestivalTest() 
    {
        FestivalEntity entity = data.get(0);
        FestivalEntity resultEntity = festivalLogic.getFestival(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getFechaInicio(), resultEntity.getFechaInicio());
        Assert.assertEquals(entity.getFechafin() , resultEntity.getFechafin() );
    }

    /**
     * Prueba para eliminar un Festival.
     *
     * @generated
     */
    @Test
    public void deleteFestivalTest() 
    {
        try 
        {
            FestivalEntity entity = data.get(0);
            festivalLogic.deleteFestival(entity.getId());
            FestivalEntity deleted = em.find(FestivalEntity.class, entity.getId());
            Assert.assertNull(deleted);
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(FestivalLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para actualizar un Festival.
     *
     * @generated
     */
    @Test
    public void updateFestivalTest() 
    {
        try 
        {
            FestivalEntity entity = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            FestivalEntity pojoEntity = factory.manufacturePojo(FestivalEntity.class);
            pojoEntity.setId(entity.getId());
            
            festivalLogic.updateFestival(pojoEntity);
            
            FestivalEntity resp = em.find(FestivalEntity.class, entity.getId());
            
            Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
            Assert.assertEquals(entity.getFechaInicio(), resp.getFechaInicio());
            Assert.assertEquals(entity.getFechafin() , resp.getFechafin() );
            
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(MusicoLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
