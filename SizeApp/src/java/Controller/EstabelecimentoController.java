package Controller;

import Dao.EstabelecimentoDAO;
import Model.EstabelecimentoModel;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ManagedBean(name = "EstabelecimentoController")
@SessionScoped
public class EstabelecimentoController {

  private final EstabelecimentoDAO estabelecimentoDAO;
  public EstabelecimentoModel estabelecimentoModel = new EstabelecimentoModel();
  public JSONArray estabelecimentoArray = new JSONArray();

  public EstabelecimentoController() {
    this.estabelecimentoDAO = new EstabelecimentoDAO();
  }

  public EstabelecimentoModel getEstabelecimentoModel() {
    return estabelecimentoModel;
  }

  public void setEstabelecimentoModel(EstabelecimentoModel estabelecimentoModel) {
    this.estabelecimentoModel = estabelecimentoModel;
  }

  public JSONArray getEstabelecimentoArray() {
    return estabelecimentoArray;
  }

  public void setEstabelecimentoArray(JSONArray estabelecimentoArray) {
    this.estabelecimentoArray = estabelecimentoArray;
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

  public void loadAllJson() throws SQLException, JSONException {
    HashMap<Integer, EstabelecimentoModel> map;
    map = estabelecimentoDAO.getToJson();
    JSONArray json = new JSONArray();
    for (HashMap.Entry<Integer, EstabelecimentoModel> entry : map.entrySet()) {
      Integer id = entry.getKey();
      EstabelecimentoModel model = entry.getValue();
      JSONObject object = new JSONObject();
      object.put("id", model.getIdEstabelecimento());
      object.put("nome", model.getNome());
      object.put("foto", model.getFoto());
      object.put("endereco", model.getEndereco());
      object.put("latitude", model.getLatitude());
      object.put("longitude", model.getLongitude());
      object.put("nota", model.getNotaTotal());
      object.put("telefone", model.getTelefone());
      json.put(object);
    }
    this.setEstabelecimentoArray(json);
  }

  public void loadEstabelecimento() {
    if (estabelecimentoModel != null) {
      estabelecimentoModel.getIdEstabelecimento();
    }
  }
}
