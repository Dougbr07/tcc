package Model;

import javax.servlet.http.Part;

/**
 *
 * @author Matheus Montenegro
 */
public class EstabelecimentoModel {
    private int IdEstabelecimento;
    private String nome;
    private int status;
    private String endereco;
    private String latitude;
    private String longitude;
    private String foto;
    private double notaTotal;
    private String domAbertura;
    private String domFechamento;
    private String segAbertura;
    private String segFechamento;
    private String terAbertura;
    private String terFechamento;
    private String quaAbertura;
    private String quaFechamento;
    private String quiAbertura;
    private String quiFechamento;
    private String sexAbertura;
    private String sexFechamento;
    private String sabAbertura;
    private String sabFechamento;
    private Part file1;
    
    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }
   

    public int getIdEstabelecimento() {
        return IdEstabelecimento;
    }

    public void setIdEstabelecimento(int IdEstabelecimento) {
        this.IdEstabelecimento = IdEstabelecimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getNotaTotal() {
        return notaTotal;
    }

    public void setNotaTotal(double notaTotal) {
        this.notaTotal = notaTotal;
    }

    public String getDomAbertura() {
        return domAbertura;
    }

    public void setDomAbertura(String domAbertura) {
        this.domAbertura = domAbertura;
    }

    public String getDomFechamento() {
        return domFechamento;
    }

    public void setDomFechamento(String domFechamento) {
        this.domFechamento = domFechamento;
    }

    public String getSegAbertura() {
        return segAbertura;
    }

    public void setSegAbertura(String segAbertura) {
        this.segAbertura = segAbertura;
    }

    public String getSegFechamento() {
        return segFechamento;
    }

    public void setSegFechamento(String segFechamento) {
        this.segFechamento = segFechamento;
    }

    public String getTerAbertura() {
        return terAbertura;
    }

    public void setTerAbertura(String terAbertura) {
        this.terAbertura = terAbertura;
    }

    public String getTerFechamento() {
        return terFechamento;
    }

    public void setTerFechamento(String terFechamento) {
        this.terFechamento = terFechamento;
    }

    public String getQuaAbertura() {
        return quaAbertura;
    }

    public void setQuaAbertura(String quaAbertura) {
        this.quaAbertura = quaAbertura;
    }

    public String getQuaFechamento() {
        return quaFechamento;
    }

    public void setQuaFechamento(String quaFechamento) {
        this.quaFechamento = quaFechamento;
    }

    public String getQuiAbertura() {
        return quiAbertura;
    }

    public void setQuiAbertura(String quiAbertura) {
        this.quiAbertura = quiAbertura;
    }

    public String getQuiFechamento() {
        return quiFechamento;
    }

    public void setQuiFechamento(String quiFechamento) {
        this.quiFechamento = quiFechamento;
    }

    public String getSexAbertura() {
        return sexAbertura;
    }

    public void setSexAbertura(String sexAbertura) {
        this.sexAbertura = sexAbertura;
    }

    public String getSexFechamento() {
        return sexFechamento;
    }

    public void setSexFechamento(String sexFechamento) {
        this.sexFechamento = sexFechamento;
    }

    public String getSabAbertura() {
        return sabAbertura;
    }

    public void setSabAbertura(String sabAbertura) {
        this.sabAbertura = sabAbertura;
    }

    public String getSabFechamento() {
        return sabFechamento;
    }

    public void setSabFechamento(String sabFechamento) {
        this.sabFechamento = sabFechamento;
    }

}
