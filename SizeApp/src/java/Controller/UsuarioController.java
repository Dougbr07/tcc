package Controller;

import Dao.UsuarioDAO;
import Model.GoogleModel;
import Model.UsuarioModel;
import Util.Message;
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
  public Message mensagem;
  
  public Object getUsuarioModel() {
    return usuarioModel;
  }

  public void setUsuarioModel(Object usuarioModel) {
    this.usuarioModel = usuarioModel;
  }

  public UsuarioModel getLoggedUser() {
    this.loggedUser = ((UsuarioModel) SessionContext.getInstance().getAttribute("usuario"));
    return loggedUser;
  }

  public void setLoggedUser(UsuarioModel loggedUser) {
    this.loggedUser = loggedUser;
  }

  public Message getMessage() {
    return mensagem;
  }

  public void setMessage(Message mensagem) {
    this.mensagem = mensagem;
  }
  
  public UsuarioController() {
    this.usuarioDAO = new UsuarioDAO();
    this.usuarioModel = new UsuarioModel();
    this.loggedUser = new UsuarioModel();
    if (getLoggedUser() != null) {
      this.loggedUser = getLoggedUser();
    }
  }
//    *
//    Cadastro de Usuário
//    *

  public void cadastrar() throws SQLException, IOException {
    if (usuarioModel != null) {
      if (usuarioDAO.isAvailable((UsuarioModel) usuarioModel)) {
        boolean resultado = this.usuarioDAO.insert((UsuarioModel) usuarioModel);
        FacesContext.getCurrentInstance().getExternalContext().redirect("../login/index.xhtml");
      } else {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Este e-mail já está sendo utilizado."));
      }
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ops... Alguma coisa está errada!"));
    }
  }
//    *
//    Login de Usuário (Usuário padrão e Google)
//    *

  public void login() throws SQLException, IOException {
    boolean isAuthorized = false;
    if (usuarioModel instanceof GoogleModel) {
      isAuthorized = usuarioDAO.login(usuarioModel);
      if (!isAuthorized) {
        if (!(usuarioDAO.insert((GoogleModel) usuarioModel))) {
          FacesContext.getCurrentInstance().addMessage("formx:email", new FacesMessage("Ops... Algo deu errado! Tente novamente mais tarde."));
        } else {
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
//  *
//  Logout qualquer usuário
//  *

  public void logout() throws IOException {
    SessionContext.getInstance().encerrarSessao();
    FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/SizeApp/");
  }
//    *
//    Alteração de dados de usuário
//    *

  public void alterar(String param) throws SQLException, IOException {
    loggedUser = getLoggedUser();
    String message = "";
    String msgField = "";
    switch (param) {
      case "nome":
        message = "Nome alterado com sucesso!";
        msgField = "msg_nomeField";
        if (((UsuarioModel) usuarioModel).getNome() == null) {
          FacesContext.getCurrentInstance().addMessage(msgField, new FacesMessage("Por favor, insira um nome para poder fazer a alteração."));
          return;
        }
        break;
      case "foto":
        message = "Nome alterado com sucesso!";
        msgField = "imgField";
//        if (((UsuarioModel) usuarioModel).getFoto() == null) {
//          FacesContext.getCurrentInstance().addMessage(msgField, new FacesMessage("Por favor, escolha uma foto para poder fazer a alteração."));
//          return;
//        }
        break;
      case "senha":
        message = "Senha alterada com sucesso!";
        msgField = "msg_senhaField";
        if (((UsuarioModel) usuarioModel).getSenha() == null) {
          FacesContext.getCurrentInstance().addMessage(msgField, new FacesMessage("Por favor, insira uma senha para poder fazer a alteração."));
          return;
        }
        break;
    }
    if (usuarioModel != null && loggedUser != null) {
      if (usuarioDAO.alterar((UsuarioModel) usuarioModel, loggedUser, param)) {
        loggedUser = usuarioDAO.getUser(loggedUser);
        SessionContext.getInstance().setAttribute("usuario", loggedUser);
        mensagem = new Message("success", "Alteração salva.");
        mensagem.sendMessage();
      } else {
        mensagem = new Message("danger", "Algo está errado, tente novamente.");
        mensagem.sendMessage();
      }
    }
  }
  public void showMessage(){
    if(this.mensagem != null && this.mensagem.alreadyShowed == 0){
      this.mensagem.setAlreadyShowed(1);
    }else{
      this.mensagem = new Message();
    }
  }
}
