/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.test.logic;
import co.edu.uniandes.csw.musica.ejbs.FestivalLogic;
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
import org.junit.Before;
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
        em.createQuery("delete from FestivalEntitt").executeUpdate();
    }
    
    private List<FestivalEntity> data = new ArrayList<FestivalEntity>;
    
    private void insertData(){
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            FestivalEntity entity = factory.manufacturePojo(FestivalEntity.class);

            em.persist(entity);
            data.add(entity);
            
        }
    }
    
}
