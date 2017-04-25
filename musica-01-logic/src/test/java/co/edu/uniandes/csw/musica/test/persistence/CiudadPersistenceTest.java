package co.edu.uniandes.csw.musica.test.persistence;

import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import co.edu.uniandes.csw.musica.persistence.CiudadPersistence;
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

@RunWith(Arquillian.class)
public class CiudadPersistenceTest {

    public static final String DEPLOY = "Prueba";

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(CiudadEntity.class.getPackage())
                .addPackage(CiudadPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private CiudadPersistence bookPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    /*
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

    /*
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from CiudadEntity").executeUpdate();
    }

    private List<CiudadEntity> data = new ArrayList<CiudadEntity>();

    /*
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /*
     * Prueba para crear un Ciudad.
     */
    @Test
    public void createCiudadTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);
        CiudadEntity result = bookPersistence.create(newEntity);

        Assert.assertNotNull(result);

        CiudadEntity entity = em.find(CiudadEntity.class, result.getId());

        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /*
     * Prueba para consultar la lista de Ciudades.
     */
    @Test
    public void getCiudadesTest() {
        List<CiudadEntity> list = bookPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CiudadEntity ent : list) {
            boolean found = false;
            for (CiudadEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /*
     * Prueba para consultar un Ciudad.
     */
    @Test
    public void getCiudadTest() {
        CiudadEntity entity = data.get(0);
        CiudadEntity newEntity = bookPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /*
     * Prueba para eliminar un Ciudad.
     */
    @Test
    public void deleteCiudadTest() {
        CiudadEntity entity = data.get(0);
        bookPersistence.delete(entity.getId());
        CiudadEntity deleted = em.find(CiudadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /*
     * Prueba para actualizar un Ciudad.
     */
    @Test
    public void updateCiudadTest() {
        CiudadEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);
        newEntity.setId(entity.getId());

        bookPersistence.update(newEntity);

        CiudadEntity resp = em.find(CiudadEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
}
