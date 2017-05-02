/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.test.persistence;

import co.edu.uniandes.csw.musica.entities.FestivalEntity;
import co.edu.uniandes.csw.musica.persistence.FestivalPersistence;
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
 * @author pa.alvarado10
 */
@RunWith(Arquillian.class)
public class FestivalPersistenceTest {
   /**
    * Constante que define el deploy como prueba festival
    */
    public static final String DEPLOY = "PruebaFesitval";
   /**
    * 
    */
    @Deployment
    public static WebArchive createDeployment(){
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(FestivalEntity.class.getPackage())
                .addPackage(FestivalPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    private FestivalPersistence festivalPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    /**
     * Configuración inicual de la prueba.
     */
    @Before
    public void configTest(){
        try{
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch(Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
     /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from FestivalEntity").executeUpdate();
    }

    private List<FestivalEntity> data = new ArrayList<FestivalEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
        
            PodamFactory factory = new PodamFactoryImpl();
            FestivalEntity entity = factory.manufacturePojo(FestivalEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Festival.
     */
    @Test
    public void createFestivalTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FestivalEntity newEntity = factory.manufacturePojo(FestivalEntity.class);
        FestivalEntity result = festivalPersistence.create(newEntity);

        Assert.assertNotNull(result);

        FestivalEntity entity = em.find(FestivalEntity.class, result.getId());

       Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
       Assert.assertEquals(entity.getFechaInicio(), newEntity.getFechaInicio());
       Assert.assertEquals(entity.getFechafin() , newEntity.getFechafin() );
    }

    /**
     * Prueba para consultar la lista de Festivales.
     */
    @Test
    public void getFestivalesTest() {
        List<FestivalEntity> list = festivalPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FestivalEntity ent : list) {
            boolean found = false;
            for (FestivalEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Festival.
     */
    @Test
    public void getFestivalTest() {
        FestivalEntity entity = data.get(0);
        FestivalEntity newEntity = festivalPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getFechaInicio(), newEntity.getFechaInicio());
        Assert.assertEquals(entity.getFechafin() , newEntity.getFechafin() );
    }

    /**
     * Prueba para eliminar un Festival.
     */
    @Test
    public void deleteFestivalTest() {
        FestivalEntity entity = data.get(0);
        festivalPersistence.delete(entity.getId());
        FestivalEntity deleted = em.find(FestivalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Festival.
     */
    @Test
    public void updateFestivalTest() {
        FestivalEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FestivalEntity newEntity = factory.manufacturePojo(FestivalEntity.class);
        newEntity.setId(entity.getId());

        festivalPersistence.update(newEntity);

        FestivalEntity resp = em.find(FestivalEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(entity.getFechaInicio(), newEntity.getFechaInicio());
        Assert.assertEquals(entity.getFechafin() , newEntity.getFechafin() );
    }
    
    
}
