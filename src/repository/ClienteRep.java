package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Cliente;
import model.Pessoa;
import util.ConexaoBD;

public class ClienteRep {

    private static final String INSERT = "insert into cliente (cpf_cliente, id_pessoa) values (?, ?);";

    private static final String SELECT = "select id, cpf_cliente from cliente";

    private static final String DELETE = "delete from cliente where id = ?";

    private static final String UPDATE = "update cliente set cpf_cliente = ?, id_pessoa = ? where id = ?";

    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;

    EntityManagerFactory tx = Persistence.createEntityManagerFactory("crmPU");
    EntityManager em = tx.createEntityManager();

    public void salvar(Cliente cliente) {
        try {
            pstm = connection.prepareStatement(INSERT);
            pstm.setString(1, cliente.getCpfCliente());
            pstm.setInt(2, cliente.getPessoa().getIdPessoa());
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }

    public void alterar(Cliente cliente) {
        try {
            pstm = connection.prepareStatement(UPDATE);
            pstm.setString(1, cliente.getCpfCliente());
            pstm.setInt(2, cliente.getPessoa().getIdPessoa());
            pstm.setInt(3, cliente.getId());
            pstm.execute();
            pstm.close();
        } catch (Exception e) {

        }
    }

    public void excluir(Cliente cliente) {
        try {
            pstm = connection.prepareStatement(DELETE);
            //pstm.setInt(1, cliente.getIdCliente());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar excluir: " + ex.getMessage());
        }

    }

    public List<Cliente> listar() {
        List<Cliente> cliente = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();

            while (res.next()) {
                Cliente c = new Cliente();
                c.setId(res.getInt("id"));
                c.setCpfCliente(res.getString("cpf_cliente"));
                cliente.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os clientes do banco: " + ex.getMessage());
        }
        return cliente;
    }
}
