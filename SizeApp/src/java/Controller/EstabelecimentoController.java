package Controller;

import Dao.EstabelecimentoDAO;
import Model.EstabelecimentoModel;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
  public void loadEstabelecimento(){
    if(estabelecimentoModel != null){
      estabelecimentoModel.getIdEstabelecimento();
    }
  }
}
