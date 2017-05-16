package co.edu.uniandes.csw.musica.test.logic;

import co.edu.uniandes.csw.musica.ejbs.BoletaLogic;
import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.BoletaPersistence;
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
 * @author la.herrera11
 */
@RunWith(Arquillian.class)
public class BoletaLogicTest {

    public static final String DEPLOY = "PruebaLogicBoleta";
    @Inject
    private BoletaLogic boletaLogic;
    @PersistenceContext
    private EntityManager em;
    private final PodamFactory factory = new PodamFactoryImpl(); 
    @Inject
    UserTransaction utx;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(BoletaEntity.class.getPackage())
                .addPackage(BoletaLogic.class.getPackage())
                .addPackage(BoletaPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

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
        em.createQuery("delete from BoletaEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<BoletaEntity> data = new ArrayList<BoletaEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            BoletaEntity entity = factory.manufacturePojo(BoletaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Boleta.
     *
     * @generated
     */
    @Test
    public void createBoletaTest() {
        try {
            PodamFactory factory = new PodamFactoryImpl();
            BoletaEntity entity = factory.manufacturePojo(BoletaEntity.class);
             BoletaEntity result = boletaLogic.createBoleta(entity);
            Assert.assertNotNull(result);
            Assert.assertEquals(result.getPrecio(), entity.getPrecio());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(BoletaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para consultar la lista de Boletas.
     *
     * @generated
     */
    @Test
    public void getBoletasTest() {
        List<BoletaEntity> list = boletaLogic.getBoletas();
        Assert.assertEquals(data.size(), list.size());
        for (BoletaEntity entity : list) {
            boolean found = false;
            for (BoletaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Boleta.
     *
     * @generated
     */
    @Test
    public void getBoletaTest() throws BusinessLogicException {
        BoletaEntity entity = data.get(0);
        BoletaEntity resultEntity = boletaLogic.getBoleta(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Boleta.
     *
     * @generated
     */
    @Test
    public void deleteBoletaTest() {
        try {
            BoletaEntity entity = data.get(0);
            boletaLogic.deleteBoleta(entity.getId());
            BoletaEntity deleted = em.find(BoletaEntity.class, entity.getId());
            Assert.assertNull(deleted);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(BoletaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para actualizar un Boleta.
     *
     * @generated
     */
    @Test
    public void updateBoletaTest() {
        try {
            BoletaEntity entity = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            BoletaEntity pojoEntity = factory.manufacturePojo(BoletaEntity.class);
            pojoEntity.setId(entity.getId());

            boletaLogic.updateBoleta(pojoEntity);

            BoletaEntity resp = em.find(BoletaEntity.class, entity.getId());
            Assert.assertEquals(pojoEntity.getId(), resp.getId());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(BoletaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
