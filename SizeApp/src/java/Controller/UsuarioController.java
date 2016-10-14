/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.UsuarioDAO;
import Model.UsuarioModel;
import java.util.List;

/**
 *
 * @author Nilson França
 */
    public class UsuarioController {
    
     private UsuarioDAO usuarioDAO;
	
	public UsuarioController() {
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public boolean cadastrar(UsuarioDAO usuario) {
		boolean resultado = false;
		try {
			resultado = this.usuarioDAO.insert(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
    
    
    
    
    
    
        }
  
}
  
           
  
   
    

