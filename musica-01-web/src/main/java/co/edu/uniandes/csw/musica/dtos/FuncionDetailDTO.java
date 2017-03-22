// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author la.herrera11
 */

@XmlRootElement
public class FuncionDetailDTO extends FuncionDTO{
    private List<BoletaEntity> boletas;
    private List<MusicoEntity> musicos;
    
    public FuncionDetailDTO(FuncionEntity funcion) {
        super(funcion);
        
        if(funcion != null)
        {
            this.boletas=funcion.getBoletas();
            this.musicos= funcion.getMusicos();
        }
    }

    public FuncionDetailDTO() 
    {
        super();
    }

    public List<BoletaEntity> getBoletas(){
    return boletas;
    }
    public void setBoletas(List<BoletaEntity> boletas){
    this.boletas=  boletas;
    }
    
    public List<MusicoEntity> getMusicos(){
    return musicos;
    }
    public void setMusicos(List<MusicoEntity> musicos){
    this.musicos=  musicos;
    }
    
    /**
     *Convierte FuncionDetailDTO a FuncionEntity incluyendo a FuncionDTO
     * 
     * @return Nuevo objeto FuncionEntity.
     */
    @Override
    public FuncionEntity toEntity()
    {
        FuncionEntity entity = super.toEntity();
        entity.setBoletas(boletas);
        entity.setMusicos(musicos);
        return entity;
    }
}
