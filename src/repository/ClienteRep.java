package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import util.ConexaoBD;

public class ClienteRep {

    private static final String INSERT = "insert into cliente (cpf_cliente, nome_cliente, email_cliente, tel_cliente) values (?,?,?,?);";

    private static final String SELECT = "select id_cliente, cpf_cliente, nome_cliente, email_cliente, tel_cliente from cliente";

    private static final String DELETE = "delete from cliente where id_cliente = ?";

    private static final String UPDATE = "update cliente set cpf_cliente = ?, nome_cliente = ?, email_cliente = ?, tel_cliente = ? where id_cliente = ?";

    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;

    public void salvar(Cliente cliente) {

        try {
            if (cliente.getIdCliente()>0) {
                pstm = connection.prepareStatement(UPDATE);
                pstm.setString(1, cliente.getCpfCliente());
                pstm.setString(2, cliente.getNomeCliente());
                pstm.setString(3, cliente.getEmailCliente());
                pstm.setString(4, cliente.getTelCliente());
                pstm.setInt(5, cliente.getIdCliente());
            } else {
                pstm = connection.prepareStatement(INSERT);
                pstm.setString(1, cliente.getCpfCliente());
                pstm.setString(2, cliente.getNomeCliente());
                pstm.setString(3, cliente.getEmailCliente());
                pstm.setString(4, cliente.getTelCliente());
            }
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }

    public void excluir(Cliente cliente) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setInt(1, cliente.getIdCliente());
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

            while(res.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(res.getInt("id_cliente"));
                c.setCpfCliente(res.getString("cpf_cliente"));
                c.setNomeCliente(res.getString("nome_cliente"));
                c.setEmailCliente(res.getString("email_cliente"));
                c.setTelCliente(res.getString("tel_cliente"));
                cliente.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os clientes do banco: " + ex.getMessage());
        }
        return cliente;
    }
}
