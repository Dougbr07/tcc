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

    public boolean insert(EstabelecimentoModel object) {
        String SQL = "";

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
    

