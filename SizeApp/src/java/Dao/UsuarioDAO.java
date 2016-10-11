/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.UsuarioModel;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nilson França
 */
public class UsuarioDAO {
    
    
    private Connection connection;

    public UsuarioDAO() {
        connection = Conexao.getConnection();
    }
    
    
    
    public boolean insert (UsuarioModel object) {
    
      try{
           
         String sql = "insert into usuario(nome,senha,foto,email) values(?,?,?,?)";
         
         PreparedStatement prep = connection.prepareStatement(sql);
         prep.setString(1, object.getNome());
         prep.setString(2, object.getSenha());
         prep.setString(3, object.getFoto());
         prep.setString(4, object.getEmail());
         prep.executeUpdate();
         return true;


         }catch(SQLException e){
                e.printStackTrace();
                 System.out.println("Oops! Ocorreu um erro inesperado!");
         } catch (Exception ex) {
            Logger.getLogger(UsuarioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
     return false;    
        
    }
    
    
     public boolean remove (UsuarioModel object) {
     try{

       
         String sql = "update estabelencimento set status = 0 where id = ?";
         PreparedStatement prep = connection.prepareStatement(sql);
         prep.setInt(1, object.getIdUsuario());
         prep.executeUpdate();
         return true;

         }catch(SQLException e){
                e.printStackTrace();
                 System.out.println("Oops! Ocorreu um erro inesperado!");
         }
         return false;
    }
    
     
      public ArrayList<UsuarioModel> show() {
    
        ArrayList<UsuarioModel> usuarios = new ArrayList<>();

            try{

                String sql = "select * from usuario";
                PreparedStatement prep = connection.prepareStatement(sql);
                ResultSet rs = prep.executeQuery();

                while(rs.next()){

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
}