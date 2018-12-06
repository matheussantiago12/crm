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

    private static final String INSERT = "insert into cliente (cpf_cliente) values (?);";

    private static final String SELECT = "select id_cliente, cpf_cliente from cliente";

    private static final String DELETE = "delete from cliente where id_cliente = ?";

    private static final String UPDATE = "update cliente set cpf_cliente = ?, id_pessoa = ? where id_cliente = ?";

    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;

    EntityManagerFactory tx = Persistence.createEntityManagerFactory("crmPU");
    EntityManager em = tx.createEntityManager();

    public void salvar(Pessoa pessoa, Cliente cliente) {
        cliente.setPessoa(pessoa);    
        try{
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch(Exception ex){
            em.getTransaction().rollback();
        } finally {
            em.close();
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
                //Cliente c = new Cliente();
                //c.setIdCliente(res.getInt("id_cliente"));
                //c.setCpfCliente(res.getString("cpf_cliente"));
                //c.setNomeCliente(res.getString("nome_cliente"));
                //c.setEmailCliente(res.getString("email_cliente"));
                //c.setTelCliente(res.getString("tel_cliente"));
                //cliente.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os clientes do banco: " + ex.getMessage());
        }
        return cliente;
    }
}
