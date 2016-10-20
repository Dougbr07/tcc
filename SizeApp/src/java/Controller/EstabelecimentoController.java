/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.EstabelecimentoDAO;
import Model.EstabelecimentoModel;

/**
 *
 * @author Nilson França
 */
public class EstabelecimentoController {

  private EstabelecimentoDAO estabelecimentoDAO;

  public EstabelecimentoController() {
    this.estabelecimentoDAO = new EstabelecimentoDAO();
  }

  public boolean cadastrar(EstabelecimentoModel object) {
    boolean resultado = false;
    try {
      resultado = this.estabelecimentoDAO.insert(object);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultado;

  }
}
