/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.PlanoModel;
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
public class PlanoDAO {
   
    private Connection connection;

    public PlanoDAO() {
        connection = Conexao.getConnection();
    }
    
    
    
    public boolean insert (PlanoModel object) {
    
      try{
           
         String sql = "insert into plano(nome) values(?)";
         
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
    
    
     public boolean remove (PlanoModel object) {
     try{

       
         String sql = "update plano set status = 0 where id = ?";
         PreparedStatement prep = connection.prepareStatement(sql);
         prep.setInt(1, object.getIdPlano());
         prep.executeUpdate();
         return true;

         }catch(SQLException e){
                e.printStackTrace();
                 System.out.println("Oops! Ocorreu um erro inesperado!");
         }
         return false;
    }
    
     
      public ArrayList<PlanoModel> show() {
    
        ArrayList<PlanoModel> planos = new ArrayList<>();

            try{

                String sql = "select * from plano";
                PreparedStatement prep = connection.prepareStatement(sql);
                ResultSet rs = prep.executeQuery();

                while(rs.next()){

                    PlanoModel plano = new PlanoModel();
                    plano.setNome(rs.getString("nome"));
                    plano.setIdPlano(rs.getInt("idplano"));
                    plano.setStatus(rs.getInt("status"));
                    planos.add(plano);

                }


            } catch (SQLException ex) {
                System.out.println("Erro" + ex);
            }

        return planos;
        
        }
      
}
