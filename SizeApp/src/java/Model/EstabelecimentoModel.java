package Model;
import java.sql.Time;
/**
 *
 * @author Matheus Montenegro
 */
public class EstabelecimentoModel {
    private int IdEstabelecimento;
    private String nome;
    private int status;
    private String endereco;
    private int latitude;
    private int longitude;
    private String foto;
    private double notaTotal;
    private Time domAbertura;
    private Time domFechamento;
    private Time segAbertura;
    private Time segFechamento;
    private Time terAbertura;
    private Time terFechamento;
    private Time quaAbertura;
    private Time quaFechamento;
    private Time quiAbertura;
    private Time quiFechamento;
    private Time sexAbertura;
    private Time sexFechamento;
    private Time sabAbertura;

    public double getNotaTotal() {
        return notaTotal;
    }

    public void setNotaTotal(double notaTotal) {
        this.notaTotal = notaTotal;
    }
    
    public EstabelecimentoModel(){
        
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

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Time getDomAbertura() {
        return domAbertura;
    }

    public void setDomAbertura(Time domAbertura) {
        this.domAbertura = domAbertura;
    }

    public Time getDomFechamento() {
        return domFechamento;
    }

    public void setDomFechamento(Time domFechamento) {
        this.domFechamento = domFechamento;
    }

    public Time getSegAbertura() {
        return segAbertura;
    }

    public void setSegAbertura(Time segAbertura) {
        this.segAbertura = segAbertura;
    }

    public Time getSegFechamento() {
        return segFechamento;
    }

    public void setSegFechamento(Time segFechamento) {
        this.segFechamento = segFechamento;
    }

    public Time getTerAbertura() {
        return terAbertura;
    }

    public void setTerAbertura(Time terAbertura) {
        this.terAbertura = terAbertura;
    }

    public Time getTerFechamento() {
        return terFechamento;
    }

    public void setTerFechamento(Time terFechamento) {
        this.terFechamento = terFechamento;
    }

    public Time getQuaAbertura() {
        return quaAbertura;
    }

    public void setQuaAbertura(Time quaAbertura) {
        this.quaAbertura = quaAbertura;
    }

    public Time getQuaFechamento() {
        return quaFechamento;
    }

    public void setQuaFechamento(Time quaFechamento) {
        this.quaFechamento = quaFechamento;
    }

    public Time getQuiAbertura() {
        return quiAbertura;
    }

    public void setQuiAbertura(Time quiAbertura) {
        this.quiAbertura = quiAbertura;
    }

    public Time getQuiFechamento() {
        return quiFechamento;
    }

    public void setQuiFechamento(Time quiFechamento) {
        this.quiFechamento = quiFechamento;
    }

    public Time getSexAbertura() {
        return sexAbertura;
    }

    public void setSexAbertura(Time sexAbertura) {
        this.sexAbertura = sexAbertura;
    }

    public Time getSexFechamento() {
        return sexFechamento;
    }

    public void setSexFechamento(Time sexFechamento) {
        this.sexFechamento = sexFechamento;
    }

    public Time getSabAbertura() {
        return sabAbertura;
    }

    public void setSabAbertura(Time sabAbertura) {
        this.sabAbertura = sabAbertura;
    }

    public Time getSabFechamento() {
        return sabFechamento;
    }

    public void setSabFechamento(Time sabFechamento) {
        this.sabFechamento = sabFechamento;
    }
    private Time sabFechamento;
}
