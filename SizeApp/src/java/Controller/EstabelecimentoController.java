/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.EstabelecimentoDAO;
import Model.EstabelecimentoModel;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Nilson França
 */
@ManagedBean(name = "EstabelecimentoController")
@SessionScoped
public class EstabelecimentoController{

  private final EstabelecimentoDAO estabelecimentoDAO;
  public EstabelecimentoModel estabelecimentoModel = new EstabelecimentoModel();

  public EstabelecimentoController() {
    this.estabelecimentoDAO = new EstabelecimentoDAO();
  }

  public EstabelecimentoModel getEstabelecimentoModel() {
    return estabelecimentoModel;
  }

  public void setEstabelecimentoModel(EstabelecimentoModel estabelecimentoModel) {
    this.estabelecimentoModel = estabelecimentoModel;
  }

  public boolean cadastrar() {
    boolean resultado = false;
    try {
      resultado = this.estabelecimentoDAO.insert(estabelecimentoModel);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultado;

  }
}
