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
public class PlanoModel {
    
    private int idPlano;
    private String nome;
    private int status;

    /**
     * @return the idPlano
     */
    public int getIdPlano() {
        return idPlano;
    }

    /**
     * @param idPlano the idPlano to set
     */
    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
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
