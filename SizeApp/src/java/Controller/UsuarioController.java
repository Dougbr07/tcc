/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.UsuarioDAO;
import Model.GoogleModel;
import Model.UsuarioModel;
import java.sql.SQLException;
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

    public boolean cadastrar(UsuarioModel usuario) throws SQLException {
        boolean resultado;
        resultado = this.usuarioDAO.insert(usuario);
        return resultado;
    }

    public boolean login(UsuarioModel usuario) throws SQLException {
        if (usuario instanceof GoogleModel) {
            if (usuarioDAO.login(usuario)) {
                System.out.println("Logado");
                //criar sessão   pesquisar
            } else {
                System.out.println("Não logado ainda!");
                if (usuarioDAO.insert(usuario)) {
                    usuarioDAO.login(usuario);
                }
            }
        }
        return false;
    }

}
