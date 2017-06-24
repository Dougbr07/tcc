package Dao;

import Model.GoogleModel;
import Model.UsuarioModel;
import Util.Conexao;
import Util.FileUpload;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

  private final Connection connection;

  public UsuarioDAO() {
    connection = Conexao.getConnection();
  }

  public int ultimoId() {
    int id = 0;
    try {
      String sql = "SELECT MAX(idusuario) FROM usuario";
      PreparedStatement prep = connection.prepareStatement(sql);
      ResultSet rs = prep.executeQuery();
      while (rs.next()) {
        id = rs.getInt("max");
      }
    } catch (SQLException ex) {
      System.out.println("Erro" + ex);
    }
    return id;
  }

  public boolean insert(UsuarioModel object) throws SQLException, IOException {
    boolean result = false;
    String PATH = "http://localhost:8080/SizeApp/WebContent/imagens/usuario/";
    if (object.getFotoUrl() == null) {
      if (object.getFoto() != null) {
//                String SQL = "INSERT INTO usuario (nome,senha,foto,email) values(?,?,?,?)";
        String SQL = "INSERT INTO usuario (nome,senha,\"fotoUrl\",email) values(?,?,"
                + "'" + PATH + "'||(SELECT currval ('usuario_idusuario_seq'))||'.png',?)";
        PreparedStatement prep = connection.prepareStatement(SQL);
        prep.setString(1, object.getNome());
        prep.setString(2, object.getSenha());
        prep.setString(3, object.getEmail());
        if (prep.executeUpdate() == 1) {
          result = true;
          FileUpload upload = new FileUpload();
          upload.uploadFile(object.getFoto(), "usuario", ultimoId() + ".png");
        }
      } else {
        String SQL = "INSERT INTO usuario (nome,senha,email,\"fotoUrl\") values(?,?,?,'http://localhost:8080/SizeApp/WebContent/imagens/usuario/avatar.png')";
        PreparedStatement prep = connection.prepareStatement(SQL);
        prep.setString(1, object.getNome());
        prep.setString(2, object.getSenha());
        prep.setString(3, object.getEmail());
        if (prep.executeUpdate() == 1) {
          result = true;
        }
      }
    } else {
      String SQL = "INSERT INTO usuario (nome,senha,email,\"fotoUrl\") values(?,?,?,?)";
      PreparedStatement prep = connection.prepareStatement(SQL);
      prep.setString(1, object.getNome());
      prep.setString(2, object.getSenha());
      prep.setString(3, object.getEmail());
      prep.setString(4, object.getFotoUrl());
      if (prep.executeUpdate() == 1) {
        result = true;
      }
    }
    return result;
  }

  public boolean remove(UsuarioModel object) {
    try {
      String sql = "update usuario set status = 0 where id = ?";
      PreparedStatement prep = connection.prepareStatement(sql);
      prep.setInt(1, object.getIdUsuario());
      prep.executeUpdate();
      return true;

    } catch (SQLException e) {
      System.out.println("Oops! Ocorreu um erro inesperado!");
    }
    return false;
  }

  public ArrayList<UsuarioModel> showAll() {

    ArrayList<UsuarioModel> usuarios = new ArrayList<>();

    try {

      String sql = "select * from usuario";
      PreparedStatement prep = connection.prepareStatement(sql);
      ResultSet rs = prep.executeQuery();

      while (rs.next()) {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setFotoUrl(rs.getString("foto"));
        usuario.setIdUsuario(rs.getInt("idusuario"));
        usuarios.add(usuario);
      }
    } catch (SQLException ex) {
      System.out.println("Erro" + ex);
    }
    return usuarios;
  }

  public boolean alterar(UsuarioModel usuarioData, UsuarioModel loggedUser, String param) throws SQLException, IOException {
    boolean result = false;
    String PATH = "http://localhost:8080/SizeApp/WebContent/imagens/usuario/";
    if (param == null) {
      return result;
    }
    if (param.equals("foto")) {
      param = "\"fotoUrl\"";
    }
    String sql = "UPDATE usuario\n"
            + "   SET " + param + " = ?\n"
            + " WHERE email = '" + loggedUser.getEmail() + "'";
    PreparedStatement prep = connection.prepareStatement(sql);
    switch (param) {
      case "\"fotoUrl\"":
        if (usuarioData.getFoto() != null) {
          prep.setString(1, PATH + loggedUser.getIdUsuario() + ".png");
        } else {
          prep.setString(1, PATH + "avatar.png");
        }
          break;
        
      case "nome":
        prep.setString(1, usuarioData.getNome());
        break;
      case "senha":
        prep.setString(1, usuarioData.getSenha());
        break;
      default:
        return result;
    }
    if (prep.executeUpdate() == 1) {
      if (param.equals("\"fotoUrl\"") && usuarioData.getFoto() != null) {
        FileUpload upload = new FileUpload();
        upload.uploadFile(usuarioData.getFoto(), "usuario", loggedUser.getIdUsuario() + ".png");
      }
      result = true;
    }
    return result;
  }

  public boolean login(Object model) throws SQLException {
    boolean result = false;
    String sql;
    if (model != null) {
      if (model instanceof GoogleModel) {
        sql = "SELECT * FROM usuario WHERE email = ?";
        PreparedStatement prep = connection.prepareStatement(sql);
        prep.setString(1, ((GoogleModel) model).getEmail());
        ResultSet rs = prep.executeQuery();
        if (rs.next()) {
          result = true;
          return result;
        }
      } else if (model instanceof UsuarioModel) {
        sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        PreparedStatement prep = connection.prepareStatement(sql);
        prep.setString(1, ((UsuarioModel) model).getEmail());
        prep.setString(2, ((UsuarioModel) model).getSenha());
        ResultSet rs = prep.executeQuery();
        if (rs.next()) {
          result = true;
          return result;
        }
      }
    }
    return result;
  }

  public void verificarPerfil(UsuarioModel usuario) throws SQLException {
    String sql = "SELECT perfil FROM usuario WHERE email = ?";
    PreparedStatement prep = connection.prepareStatement(sql);
    prep.setString(1, usuario.getEmail());
    ResultSet rs = prep.executeQuery();
    if (rs.next()) {
      usuario.setPerfil(rs.getInt("perfil"));
    }
  }

  public boolean isAvailable(UsuarioModel usuario) throws SQLException {
    boolean result = true;
    String SQL = "SELECT email from usuario WHERE email = ?";
    PreparedStatement prep = connection.prepareStatement(SQL);
    prep.setString(1, usuario.getEmail());
    ResultSet rs = prep.executeQuery();
    if (rs.next()) {
      result = false;
    }
    return result;
  }

  public UsuarioModel getUser(UsuarioModel usuario) throws SQLException {
    UsuarioModel user = new UsuarioModel();
    String SQL = "SELECT * from usuario where email = ?";
    PreparedStatement prep = connection.prepareStatement(SQL);
    prep.setString(1, usuario.getEmail());
    ResultSet rs = prep.executeQuery();
    if (rs.next()) {
      user.setIdUsuario(rs.getInt("idUsuario"));
      user.setEmail(rs.getString("email"));
      user.setNome(rs.getString("nome"));
      user.setStatus(rs.getInt("status"));
      user.setPerfil(rs.getInt("perfil"));
      user.setFotoUrl(rs.getString("fotoUrl"));
    }
    return user;
  }

}
