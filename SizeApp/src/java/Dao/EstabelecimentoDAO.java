package Dao;

import Model.EstabelecimentoModel;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author Matheus Montenegro
 */
public class EstabelecimentoDAO {

    private Connection connection;

    public EstabelecimentoDAO() {
        connection = Conexao.getConnection();
    }

    public boolean insert(EstabelecimentoModel object) throws SQLException {
        String SQL = "INSERT INTO \n"
                + "	public.estabelecimento \n"
                + "	(nome, foto, endereco) \n"
                + "VALUES 	\n"
                + "	(?,?,?);\n"
                + "INSERT INTO\n"
                + "	public.horario_abertura \n"
                + "	(domingo, segunda, terca, quarta, quinta, sexta, sabado, idestabelecimento) \n"
                + "VALUES \n"
                + "	(?,?,?,?,?,?,?,(select LAST_INSERT_ID()));\n"
                + "INSERT INTO\n"
                + "	public.horario_fechamento \n"
                + "	(domingo, segunda, terca, quarta, quinta, sexta, sabado, idestabelecimento) \n"
                + "VALUES \n"
                + "	(?,?,?,?,?,?,?,(select LAST_INSERT_ID()))";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, object.getNome());
        stmt.setString(2, object.getFoto());
        stmt.setString(3, object.getEndereco());
        stmt.setTime(4, object.getDomAbertura());
        stmt.setTime(5, object.getSegAbertura());
        stmt.setTime(6, object.getTerAbertura());
        stmt.setTime(7, object.getQuaAbertura());
        stmt.setTime(8, object.getQuiAbertura());
        stmt.setTime(9, object.getSexAbertura());
        stmt.setTime(10, object.getSabAbertura());
        stmt.setTime(11, object.getDomFechamento());
        stmt.setTime(12, object.getSegFechamento());
        stmt.setTime(13, object.getTerFechamento());
        stmt.setTime(14, object.getQuaFechamento());
        stmt.setTime(15, object.getQuiFechamento());
        stmt.setTime(16, object.getSexFechamento());
        stmt.setTime(17, object.getSabFechamento());
        ResultSet rs = stmt.executeQuery();
        if(rs.rowInserted()){
            System.out.println(true);
            return true;
        }else{
            System.out.println(false);
            return false;
        }
    }
    
        public boolean remove (EstabelecimentoModel object){
    
         try{

       
         String sql = "update estabelencimento set status = 0 where id = ?";
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

        if (object.getNome() != null)  {
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
}