package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.Visita;
import util.ConexaoBD;

public class VisitaRep {

    private static final String INSERT = "insert into visita (data_visita, id_funcionario, id_cliente, detalhes) values (?,?,?,?);";

    private static final String SELECT = "select id_visita, data_visita, concluido_visita, id_funcionario, id_cliente";

    private static final String DELETE = "delete from visita where id_visita = ?";

    private static final String UPDATE = "update visita set data_visita = ?, id_funcionario = ?, id_cliente = ? where id_visita = ?";

    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;

    public void salvar(Visita visita) {

        try {
            pstm = connection.prepareStatement(INSERT);
            pstm.setString(1, visita.getDataVisita());
            pstm.setInt(2, visita.getFuncionario().getIdFuncionario());
            pstm.setInt(3, visita.getCliente().getId());
            pstm.setString(4, visita.getDetalhes());
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }

    public void alterar(Visita visita) {
        try {
            pstm = connection.prepareStatement(UPDATE);
            pstm.setString(1, visita.getDataVisita());
            pstm.setInt(2, visita.getFuncionario().getIdFuncionario());
            pstm.setInt(3, visita.getCliente().getId());
            pstm.execute();
            pstm.close();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar dar update: " + e.getMessage());
        }
    }

    public void excluir(Visita visita) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setInt(1, visita.getId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar excluir: " + ex.getMessage());
        }

    }

    public List<Visita> listar() {
        List<Visita> visita = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();

            if (res.next()) {
                Visita v = new Visita();
                v.setId(res.getInt("id_visita"));
                v.setDataVisita(res.getString("data_visita"));
                visita.add(v);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar as visitas do banco: " + ex.getMessage());
        }
        return visita;
    }
}
