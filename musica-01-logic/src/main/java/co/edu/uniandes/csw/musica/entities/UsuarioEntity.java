package co.edu.uniandes.csw.musica.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity

public class UsuarioEntity implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String clave;
    private boolean esAdmin;

    @ManyToMany
    private List<FestivalEntity> festivalesUsuario;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<BoletaEntity> boletas;

    public UsuarioEntity()
    {

    }

    public void setClave(String clave) 
    {
            this.clave = clave;
    }

    public String getClave() 
    {
            return clave;
    }

    public void setId(Long id) 
    {
            this.id = id;
    }

    public Long getId() 
    {
            return id;
    }

    public void setNombre(String nombre) 
    {
            this.nombre = nombre;
    }

    public String getNombre() 
    {
            return nombre;
    }

    public void setFestivales(List<FestivalEntity> festivales) 
    {
            this.festivalesUsuario = festivales;
    }

    public List<FestivalEntity> getFestivales() 
    {
            return festivalesUsuario;
    }

    public List<BoletaEntity> getBoletas() 
    {
            return boletas;
    }
    public void setBoletas(List<BoletaEntity> b) 
    {
            this.boletas = b;
    }

    public void setEsAdmin(boolean esAdmin) 
    {
            this.esAdmin = esAdmin;
    }

    public boolean getEsAdmin()
    {
        return esAdmin;
    }

}