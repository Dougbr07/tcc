/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Nilson França
 */
public class EspecialidadeModel {
    
    private int idEspecialidade;
    private String nome;
    private int status;

    /**
     * @return the idEspecialidade
     */
    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    /**
     * @param idEspecialidade the idEspecialidade to set
     */
    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
}
