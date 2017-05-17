/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.test.logic;

import co.edu.uniandes.csw.musica.ejbs.GeneroLogic;
import co.edu.uniandes.csw.musica.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import co.edu.uniandes.csw.musica.entities.FestivalEntity;
import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.BoletaPersistence;
import co.edu.uniandes.csw.musica.persistence.FestivalPersistence;
import co.edu.uniandes.csw.musica.persistence.GeneroPersistence;
import co.edu.uniandes.csw.musica.persistence.UsuarioPersistence;
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
 * @author c.potdevin10
 */
@RunWith(Arquillian.class)
public class UsuarioLogicTest {
    
    public static final String DEPLOY = "PruebaLogicUsuario";
    
    @Inject
    UsuarioLogic usuarioLogic;
    
    @PersistenceContext(unitName = "musicaPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    @Deployment
    public static WebArchive createDeployment() 
    {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configTest() {
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
    
    private void clearData() {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from BoletaEntity").executeUpdate();
        em.createQuery("delete from FestivalEntity").executeUpdate();
    }
    
    private List<BoletaEntity> boletasEnBaseDatos = new ArrayList<>();
    private List<FestivalEntity> festivalesEnBaseDatos = new ArrayList<>();
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        BoletaPersistence boletaPersistence = new BoletaPersistence();
        FestivalPersistence festivalPersistence = new FestivalPersistence();
        
        for (int i = 0; i < 3; i++) {
            BoletaEntity entity = factory.manufacturePojo(BoletaEntity.class);
            boletaPersistence.create(entity);
            boletasEnBaseDatos.add(entity);
        }
        
        for (int i = 0; i < 3; i++) {
            FestivalEntity entity = factory.manufacturePojo(FestivalEntity.class);
            festivalPersistence.create(entity);
            festivalesEnBaseDatos.add(entity);
        }
    }
    
    @Test
    public void agregaUsuarioNoAdmin() {
        
        PodamFactory factory = new PodamFactoryImpl();
        
        try {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            entity.setEsAdmin(false);
            entity.setBoletas(boletasEnBaseDatos);
            UsuarioEntity result = usuarioLogic.createUsuario(entity);
            Assert.assertEquals(entity.getId(), result.getId());
            Assert.assertEquals(entity.getNombre(), result.getNombre());
            Assert.assertEquals(entity.getClave(), result.getClave());
            Assert.assertEquals(entity.isAdmin(), result.isAdmin());
            Assert.assertEquals(entity.getBoletas(), result.getBoletas());
            Assert.assertEquals(entity.getFestivales(), result.getFestivales());
        } catch (BusinessLogicException ble) {
            Assert.fail("Excepcion no esperada : " + ble.getMessage());
        }
    }
    
    @Test
    public void agregaUsuarioNoAdminConFestivales() {
        
        PodamFactory factory = new PodamFactoryImpl();
        
        try {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            entity.setEsAdmin(false);
            entity.setBoletas(boletasEnBaseDatos);
            entity.setFestivales(festivalesEnBaseDatos);
            UsuarioEntity result = usuarioLogic.createUsuario(entity);
            Assert.fail("No se puede agregar un usuario no admin con festivales.");
        } catch (BusinessLogicException ble) {
            
        }
    }
    
    @Test
    public void agregaUsuarioAdmin() {
        
        PodamFactory factory = new PodamFactoryImpl();
        
        try {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            entity.setEsAdmin(true);
            entity.setBoletas(boletasEnBaseDatos);
            entity.setFestivales(festivalesEnBaseDatos);
            UsuarioEntity result = usuarioLogic.createUsuario(entity);
            Assert.assertEquals(entity.getId(), result.getId());
            Assert.assertEquals(entity.getNombre(), result.getNombre());
            Assert.assertEquals(entity.getClave(), result.getClave());
            Assert.assertEquals(entity.isAdmin(), result.isAdmin());
            Assert.assertEquals(entity.getBoletas(), result.getBoletas());
            Assert.assertEquals(entity.getFestivales(), result.getFestivales());
        } catch (BusinessLogicException ble) {
            Assert.fail("Excepcion no esperada : " + ble.getMessage());
        }
    }
    
    @Test
    public void agregaUsuarioConNombreEnNull() {
        
        PodamFactory factory = new PodamFactoryImpl();
        
        try {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            entity.setNombre(null);
            entity.setEsAdmin(false);
            entity.setBoletas(boletasEnBaseDatos);
            UsuarioEntity result = usuarioLogic.createUsuario(entity);
            Assert.fail("El nombre de un usuario no puede ser null.");
        } catch (BusinessLogicException ble) {
            
        }
    }
    
    @Test
    public void agregaUsuarioConNombreVacio() {
        
        PodamFactory factory = new PodamFactoryImpl();
        
        try {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            entity.setNombre("");
            entity.setEsAdmin(false);
            entity.setBoletas(boletasEnBaseDatos);
            UsuarioEntity result = usuarioLogic.createUsuario(entity);
            Assert.fail("El nombre de un usuario no puede estar vacio.");
        } catch (BusinessLogicException ble) {
            
        }
    }
    
    @Test
    public void agregaUsuarioConClaveEnNull() {
        
        PodamFactory factory = new PodamFactoryImpl();
        
        try {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            entity.setClave(null);
            entity.setEsAdmin(false);
            entity.setBoletas(boletasEnBaseDatos);
            UsuarioEntity result = usuarioLogic.createUsuario(entity);
            Assert.fail("La clave de un usuario no puede ser null.");
        } catch (BusinessLogicException ble) {
            
        }
    }
    
    @Test
    public void agregaUsuarioConClaveVacia() {
        
        PodamFactory factory = new PodamFactoryImpl();
        
        try {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            entity.setClave("");
            entity.setEsAdmin(false);
            entity.setBoletas(boletasEnBaseDatos);
            UsuarioEntity result = usuarioLogic.createUsuario(entity);
            Assert.fail("El nombre de un usuario no puede estar vacio.");
        } catch (BusinessLogicException ble) {
            
        }
    }
    
    @Test
    public void agregaUsuarioConBoletasQueNoExisten() {
        
        PodamFactory factory = new PodamFactoryImpl();
        
        try {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            ArrayList<BoletaEntity> boletas = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                BoletaEntity boleta = factory.manufacturePojo(BoletaEntity.class);
                boleta.setId(new Long(i));
                boletas.add(boleta);
            }
            entity.setBoletas(boletas);
            UsuarioEntity result = usuarioLogic.createUsuario(entity);
            Assert.fail("Las boletas de un usuario deben existir");
        } catch (BusinessLogicException ble) {
            
        }
    }
    
    @Test
    public void agregaUsuarioConFestivalesQueNoExisten() {
        
        PodamFactory factory = new PodamFactoryImpl();
        
        try {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            entity.setEsAdmin(true);
            ArrayList<FestivalEntity> festivales = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                FestivalEntity festival = factory.manufacturePojo(FestivalEntity.class);
                festival.setId(new Long(i));
                festivales.add(festival);
            }
            entity.setFestivales(festivales);
            UsuarioEntity result = usuarioLogic.createUsuario(entity);
            Assert.fail("Los festivales de un usuario admin deben existir");
        } catch (BusinessLogicException ble) {
            
        }
    }
}
