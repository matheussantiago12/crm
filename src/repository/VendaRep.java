package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Pessoa;
import model.Produto;
import model.Venda;
import util.ConexaoBD;

public class VendaRep {

    private static final String INSERT = "insert into venda (valor_venda, id_cliente, id_produto) values (?,?,?);";

    private static final String SELECT = "select id_venda, id_produto, id_cliente, valor_venda from venda order by id_venda";
    
    private static final String SELECT_CLIENTE = "select id, cpf_cliente from cliente inner join venda on cliente.id = venda.id_cliente order by venda.id_venda";
    
    private static final String SELECT_PRODUTO = "select nome_produto, qtde_estoque, preco_custo, preco_venda from produto inner join venda on produto.id_produto = venda.id_produto";

    private static final String DELETE = "delete from venda where id_venda = ?";

    private static final String UPDATE = "update venda set valor_venda where id_venda = ?";

    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;

    public void salvar(Venda venda) {

        try {
            pstm = connection.prepareStatement(INSERT);
            pstm.setDouble(1, venda.getValorVenda());
            pstm.setInt(2, venda.getCliente().getId());
            pstm.setInt(3, venda.getProduto().getIdProduto());
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }

    public void alterar(Venda venda) {

        try {
            pstm = connection.prepareStatement(UPDATE);
            pstm.setDouble(1, venda.getValorVenda());
            pstm.setInt(2, venda.getId());
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar alterar: " + ex.getMessage());
        }
    }

    public void excluir(Venda venda) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setInt(1, venda.getId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar excluir: " + ex.getMessage());
        }

    }

    public List<Venda> listar() {
        List<Venda> venda = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();

            while (res.next()) {
                Venda v = new Venda();
                v.setId(res.getInt("id_venda"));
                v.setValorVenda(res.getDouble("valor_venda"));
                venda.add(v);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os produtos do banco: " + ex.getMessage());
        }
        return venda;
    }
    
    public List<Produto> listarProduto() {
        List<Produto> produtos = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT_PRODUTO);
            res = pstm.executeQuery();

            while (res.next()) {
                Produto p = new Produto();
                
                p.setNomeProduto(res.getString("nome_produto"));
                p.setQtdeEstoque(res.getInt("qtde_estoque"));
                p.setPrecoCusto(res.getDouble("preco_custo"));
                p.setPrecoVenda(res.getDouble("preco_venda"));
                
                produtos.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os Produtos do banco: " + ex.getMessage());
        }
        return produtos;
    }
    
    public List<Cliente> listarCliente() {
        List<Cliente> clientes = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT_CLIENTE);
            res = pstm.executeQuery();

            while (res.next()) {
                Cliente c = new Cliente();
                
                c.setId(res.getInt("id"));
                c.setCpfCliente(res.getString("cpf_cliente"));
                
                clientes.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os Clientes do banco: " + ex.getMessage());
        }
        return clientes;
    }
}
