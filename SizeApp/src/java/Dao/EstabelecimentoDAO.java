package Dao;

import Model.EstabelecimentoModel;
import Util.Conexao;
import Util.FileUpload;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.Part;

/**
 *
 * @author Matheus Montenegro
 */
public class EstabelecimentoDAO {

    private Connection connection;

    public EstabelecimentoDAO() {
        connection = Conexao.getConnection();
    }
    
      public int ultimoId(){
   
    int id = 0;

            try{

                String sql = "SELECT MAX(idestabelecimento) FROM estabelecimento";
                PreparedStatement prep = connection.prepareStatement(sql);
                ResultSet rs = prep.executeQuery();

                while(rs.next()){

                    id = rs.getInt("max");

                }


            } catch (SQLException ex) {
                System.out.println("Erro" + ex);
            }

        return id;
   
   
   }
 

    public boolean insert(EstabelecimentoModel object) throws SQLException, IOException {
        String SQL;
        String PATH = "WEB-INF\\imagens\\estabelecimento";
        SQL = "INSERT INTO public.estabelecimento\n"
                + "(\n"
                + "	nome, foto, endereco, coordenada\n"
                + ")\n"
                + "VALUES\n"
                + "(\n"
                + "	?,("+PATH+"||SELECT currval ('estabelecimento_idestabelecimento_seq')||'.png'),?,"
                + " point("+Float.valueOf(object.getLatitude())+","+Float.valueOf(object.getLongitude())+")\n"
                + ") ;\n"
                + "INSERT INTO public.horario_abertura \n"
                + "(\n"
                + "	domingo, segunda, terca, quarta, quinta, sexta, sabado, idestabelecimento\n"
                + ")\n"
                + "VALUES \n"
                + "(\n"
                + "	?,?,?,?,?,?,?,(SELECT currval('estabelecimento_idestabelecimento_seq'))\n"
                + ");\n"
                + "INSERT INTO public.horario_fechamento\n"
                + "(\n"
                + "	domingo, segunda, terca, quarta, quinta, sexta, sabado, idestabelecimento\n"
                + ")\n"
                + "VALUES \n"
                + "(\n"
                + "	?,?,?,?,?,?,?,(SELECT currval('estabelecimento_idestabelecimento_seq'))\n"
                + ")";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, object.getNome());
        stmt.setString(2, object.getEndereco());
        stmt.setString(3, object.getDomAbertura());
        stmt.setString(4, object.getSegAbertura());
        stmt.setString(5, object.getTerAbertura());
        stmt.setString(6, object.getQuaAbertura());
        stmt.setString(7, object.getQuiAbertura());
        stmt.setString(8, object.getSexAbertura());
        stmt.setString(9, object.getSabAbertura());
        stmt.setString(10, object.getDomFechamento());
        stmt.setString(11, object.getSegFechamento());
        stmt.setString(12, object.getTerFechamento());
        stmt.setString(13, object.getQuaFechamento());
        stmt.setString(14, object.getQuiFechamento());
        stmt.setString(15, object.getSexFechamento());
        stmt.setString(16, object.getSabFechamento());
       

        if (stmt.executeUpdate() > 0) {
            System.out.println("Cadastrou com sucesso!");
              
              
              FileUpload upload = new FileUpload();
              upload.uploadFile(object.getFile1(), "estabelecimento", ultimoId() + ".png");
        
            return true;
        } else {
            System.out.println("Faiô... :'(");
            return false;
        }
        
        
        
    }
    public boolean remove(EstabelecimentoModel object) {

        try {

            String sql = "UPDATe estabelencimento set status = 0 where id = ?";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, object.getIdEstabelecimento());
            prep.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Oops! Ocorreu um erro inesperado!");
        }
        return false;
    }

    public HashMap<Integer, EstabelecimentoModel> show(EstabelecimentoModel object) throws SQLException {
        int index = 1;
        EstabelecimentoModel estabelecimento = null;
        HashMap<Integer, EstabelecimentoModel> estabelecimentoMap = null;
        //Se não for passado parâmetro de consulta será executado um select geral ordenando por nota
        if (object == null) {
            String SQL = "SELECT\n"
                    + "  e.nome, e.foto, e.notaTotal\n"
                    + "FROM\n"
                    + "  public.estabelecimento e\n"
                    + "WHERE\n"
                    + "  status = 1"
                    + "ORDER by "
                    + "  notaTotal DESC";
            PreparedStatement stmt = connection.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                estabelecimento.setNome(rs.getString("nome"));
                estabelecimento.setFoto(rs.getString("foto"));
                estabelecimento.setNotaTotal(rs.getDouble("notaTotal"));
                estabelecimentoMap.put(index, estabelecimento);
                index++;
            }
            return estabelecimentoMap;
        }

        if (object.getNome() != null) {
            String SQL = "SELECT\n"
                    + "  e.nome, e.foto, e.notaTotal\n"
                    + "FROM\n"
                    + "public.estabelecimento e\n"
                    + "WHERE\n"
                    + "status = 1 AND nome like '%?%'\n"
                    + "ORDER by "
                    + "  notaTotal DESC";
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setString(1, object.getNome());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                estabelecimento.setNome(rs.getString("nome"));
                estabelecimento.setFoto(rs.getString("foto"));
                estabelecimento.setNotaTotal(rs.getDouble("notaTotal"));
                estabelecimentoMap.put(index, estabelecimento);
                index++;

            }
            return estabelecimentoMap;
        }
        return estabelecimentoMap;
    }

    public boolean insert(EstabelecimentoDAO estabelecimento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
