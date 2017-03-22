package co.edu.uniandes.csw.musica.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class LugarEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    private Boolean abierto;
    
    private Integer capacidad;
    
    private Integer capacidadSonido;
    
    private Integer costoPreferencial;
    
    private Integer costoEconomico;
     
    @OneToMany(mappedBy = "lugarFuncion", cascade = CascadeType.ALL)
    private List<FuncionEntity> funcionesLugar;
    
    @ManyToOne
    private CiudadEntity ciudadLugar;
    
    
    public LugarEntity() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAbierto() {
        return abierto;
    }

    public void setAbierto(Boolean abierto) {
        this.abierto = abierto;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getCapacidadSonido() {
        return capacidadSonido;
    }

    public void setCapacidadSonido(Integer capacidadSonido) {
        this.capacidadSonido = capacidadSonido;
    }

    public Integer getCostoPreferencial() {
        return costoPreferencial;
    }

    public void setCostoPreferencial(Integer costoPreferencial) {
        this.costoPreferencial = costoPreferencial;
    }

    public Integer getCostoEconomico() {
        return costoEconomico;
    }

    public void setCostoEconomico(Integer costoEconomico) {
        this.costoEconomico = costoEconomico;
    }

    public List<FuncionEntity> getFuncionesLugar() {
        return funcionesLugar;
    }

    public void setFuncionesLugar(List<FuncionEntity> funcionesLugar) {
        this.funcionesLugar = funcionesLugar;
    }

    public CiudadEntity getCiudadLugar() {
        return ciudadLugar;
    }

    public void setCiudadLugar(CiudadEntity ciudadLugar) {
        this.ciudadLugar = ciudadLugar;
    }
    
}
