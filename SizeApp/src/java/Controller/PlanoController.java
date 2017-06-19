/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.PlanoDAO;
import Model.EstabelecimentoModel;
import Model.PlanoModel;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Douglas
 */
@ManagedBean(name = "PlanoController")
@SessionScoped
public class PlanoController {
    
  private final PlanoDAO planoDAO;
  public PlanoModel planoModel = new PlanoModel();

  public PlanoController() {
    this.planoDAO = new PlanoDAO();
  }

  public PlanoModel getPlanoModel() {
    return planoModel;
  }

  public void setPlanoModel(EstabelecimentoModel estabelecimentoModel) {
    this.planoModel = planoModel;
  }

  public boolean cadastrar() {
    boolean resultado = false;
    try {
      resultado = this.planoDAO.insert(planoModel);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultado;

  }
  
  public ArrayList<PlanoModel> showAll(){
      ArrayList<PlanoModel> resultado = new ArrayList<>();
      try {
      resultado = this.planoDAO.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
      
      return resultado;
  }
    
    
    
}
