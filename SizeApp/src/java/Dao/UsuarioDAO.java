package Dao;

import Model.GoogleModel;
import Model.UsuarioModel;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {
        connection = Conexao.getConnection();
    }

    public boolean insert(UsuarioModel object) throws SQLException {
        boolean result = false;
        String SQL = "INSERT INTO usuario (nome,senha,foto,email) values(?,?,?,?)";
        PreparedStatement prep = connection.prepareStatement(SQL);
        prep.setString(1, object.getNome());
        prep.setString(2, object.getSenha());
        prep.setString(3, object.getFoto());
        prep.setString(4, object.getEmail());
        if (prep.executeUpdate() == 1) {
            result = true;
        }
        return result;
    }

    public boolean remove(UsuarioModel object) {
        try {

            String sql = "update estabelencimento set status = 0 where id = ?";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, object.getIdUsuario());
            prep.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
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
                usuario.setFoto(rs.getString("foto"));
                usuario.setIdUsuario(rs.getInt("idusuario"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Erro" + ex);
        }
        return usuarios;
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
    
}
