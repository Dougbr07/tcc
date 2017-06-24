package Controller;

import Dao.GoogleDAO;
import Model.GoogleModel;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "GoogleController")
@SessionScoped
public class GoogleController {

    private final GoogleDAO googleDAO;
    public GoogleModel googleModel = new GoogleModel();
    
    public GoogleController(){
        this.googleDAO = new GoogleDAO();
    }

    public GoogleModel getGoogleModel() {
        return googleModel;
    }

    public void setGoogleModel(GoogleModel googleModel) {
        this.googleModel = googleModel;
    }
    
    public void autenticar() throws SQLException, GeneralSecurityException, IOException{
        if(googleDAO.validateIdToken(googleModel)){
            UsuarioController usuarioController = new UsuarioController();
            usuarioController.usuarioModel = googleModel;
            usuarioController.login();
        }else{
            FacesContext.getCurrentInstance().addMessage("formx:email", new FacesMessage("idToken Inválido!"));
        }
        
    }
}
