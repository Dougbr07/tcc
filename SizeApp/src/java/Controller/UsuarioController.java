package Controller;

import Dao.UsuarioDAO;
import Model.GoogleModel;
import Model.UsuarioModel;
import Util.SessionContext;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "UsuarioController", eager = true)
@SessionScoped
public class UsuarioController implements Serializable {

    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;
    public Object usuarioModel;
    private UsuarioModel loggedUser;

    public Object getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(Object usuarioModel) {
        this.usuarioModel = usuarioModel;
    }

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
        this.usuarioModel = new UsuarioModel();
        this.loggedUser = new UsuarioModel();
    }

    public void cadastrar() throws SQLException, IOException {
        if (usuarioModel != null) {
            if (usuarioDAO.isAvailable((UsuarioModel) usuarioModel)) {
                boolean resultado = this.usuarioDAO.insert((UsuarioModel) usuarioModel);
                FacesContext.getCurrentInstance().getExternalContext().redirect("../login/index.xhtml");
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Este e-mail já está sendo utilizado."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ops... Alguma coisa está errada!"));
        }
    }

    public void login() throws SQLException, IOException {
        boolean isAuthorized = false;
        if (usuarioModel instanceof GoogleModel) {
            isAuthorized = usuarioDAO.login(usuarioModel);
            if (!isAuthorized) {
                if (!(usuarioDAO.insert((GoogleModel) usuarioModel))) {
                    FacesContext.getCurrentInstance().addMessage("formx:email", new FacesMessage("Ops... Algo deu errado! Tente novamente mais tarde."));
                }else{
                    isAuthorized = usuarioDAO.login(usuarioModel);
                }
            }
        } else if (usuarioModel instanceof UsuarioModel) {
            isAuthorized = usuarioDAO.login(usuarioModel);
            if (!isAuthorized) {
                FacesContext.getCurrentInstance().addMessage("formx:email", new FacesMessage("Usuário e/ou senha inválidos."));
            }
        }
        if (isAuthorized) {
            usuarioDAO.verificarPerfil((UsuarioModel) usuarioModel);
            SessionContext.getInstance().setAttribute("usuario", usuarioDAO.getUser((UsuarioModel) usuarioModel));
            FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/SizeApp/main/index.xhtml");
        }
    }

    public void logout() throws IOException {
        SessionContext.getInstance().encerrarSessao();
        FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/SizeApp/");
    }

    public UsuarioModel getLoggedUser() {
        this.loggedUser = ((UsuarioModel) SessionContext.getInstance().getAttribute("usuario"));
        return loggedUser;
    }

    public void setLoggedUser(UsuarioModel loggedUser) {
        this.loggedUser = loggedUser;
    }

}
