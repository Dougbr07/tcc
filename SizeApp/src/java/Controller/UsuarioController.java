package Controller;

import Dao.UsuarioDAO;
import Model.GoogleModel;
import Model.UsuarioModel;
import Util.SessionContext;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "UsuarioController")
@SessionScoped
public class UsuarioController {

    private UsuarioDAO usuarioDAO;
    public Object usuarioModel;

    public Object getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(Object usuarioModel) {
        this.usuarioModel = usuarioModel;
    }

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
        this.usuarioModel = new UsuarioModel();
    }

    public void cadastrar(UsuarioModel usuario) throws SQLException, IOException {
        if (usuario != null) {
            boolean resultado = this.usuarioDAO.insert(usuario);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../Login/login.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ops... Alguma coisa está errada!"));
        }
    }

    public void login() throws SQLException {
        boolean isAuthorized = false;
        if (usuarioModel instanceof GoogleModel) {
            isAuthorized = usuarioDAO.login(usuarioModel);
            if (!isAuthorized) {
                if (!(usuarioDAO.insert((GoogleModel) usuarioModel))) {
                    FacesContext.getCurrentInstance().addMessage("formx:email", new FacesMessage("Ops... Algo deu errado! Tente novamente mais tarde."));
                } 
            }
        } else if (usuarioModel instanceof UsuarioModel) {
            System.out.println("entrou");
            isAuthorized = usuarioDAO.login(usuarioModel);
            if (!isAuthorized) {
                FacesContext.getCurrentInstance().addMessage("formx:email", new FacesMessage("Usuário e/ou senha inválidos."));
            }
        }
        if (isAuthorized) {
            usuarioDAO.verificarPerfil((UsuarioModel) usuarioModel);
            SessionContext.getInstance().setAttribute("usuario", usuarioModel);
        }
    }

    public void logout() {
        SessionContext.getInstance().encerrarSessao();
    }
}
