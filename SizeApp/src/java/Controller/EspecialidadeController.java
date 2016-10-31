/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.EspecialidadeDAO;
import Model.EspecialidadeModel;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Douglas
 */
@ManagedBean(name = "EspecialidadeController")
@SessionScoped
public class EspecialidadeController {
    
    
     private final EspecialidadeDAO especialidadeDAO;
  public EspecialidadeModel especialidadeModel = new EspecialidadeModel();

  public EspecialidadeController() {
    this.especialidadeDAO = new EspecialidadeDAO();
  }

  public EspecialidadeModel getEspecialidadeModel() {
    return especialidadeModel;
  }

  public void setEspecialidadeModel(EspecialidadeModel especialidadeModel) {
    this.especialidadeModel = especialidadeModel;
  }

  public boolean cadastrar() {
    boolean resultado = false;
    try {
      resultado = this.especialidadeDAO.insert(especialidadeModel);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultado;

  }
    
    
    
    
}
