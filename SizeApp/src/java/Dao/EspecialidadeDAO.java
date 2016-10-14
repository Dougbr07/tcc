/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.EspecialidadeModel;
import Model.EstabelecimentoModel;
import Model.PlanoModel;
import Model.UsuarioModel;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nilson França
 */
public class EspecialidadeDAO {
    
    private Connection connection;
    
    
    public EspecialidadeDAO() {
        connection = Conexao.getConnection();
    }
    
    
    public boolean insert (EspecialidadeModel object) {
    
      try{
           
         String sql = "insert into especialidade (nome) values(?)";
         
         PreparedStatement prep = connection.prepareStatement(sql);
         prep.setString(1, object.getNome());
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
           public boolean remove (EstabelecimentoModel object){
    
         try{

       
         String sql = "UPDATe especialidade set status = 0 where id = ?";
         PreparedStatement prep = connection.prepareStatement(sql);
         prep.setInt(1, object.getIdEstabelecimento());
         prep.executeUpdate();
         return true;

         }catch(SQLException e){
                e.printStackTrace();
                 System.out.println("Oops! Ocorreu um erro inesperado!");
         }
         return false;
    }
    
}
