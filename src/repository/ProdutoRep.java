package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;
import model.Produto;
import util.ConexaoBD;

public class ProdutoRep {

    private static final String INSERT = "insert into produto (id_fornecedor, nome_produto, qtde_estoque, preco_custo, preco_venda) values (?,?,?,?,?);";

    private static final String SELECT = "select id_produto, id_fornecedor, nome_produto, qtde_estoque, preco_custo, preco_venda from produto";

    private static final String DELETE = "delete from produto where id_produto = ?";

    private static final String UPDATE = "update produto set nome_produto = ?, qtde_estoque = ?, preco_custo = ?, preco_venda = ? where id_produto = ?";

    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;

    public void salvar(Produto produto) {

        try {
            pstm = connection.prepareStatement(INSERT);
            pstm.setInt(1, (produto.getFornecedor().getIdFornecedor()));
            pstm.setString(2, produto.getNomeProduto());
            pstm.setInt(3, produto.getQtdeEstoque());
            pstm.setDouble(4, produto.getPrecoCusto());
            pstm.setDouble(5, produto.getPrecoVenda());

            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    
    public void alterar(Produto produto) {

        try {
            pstm = connection.prepareStatement(UPDATE);
            pstm.setString(1, produto.getNomeProduto());
            pstm.setInt(2, produto.getQtdeEstoque());
            pstm.setDouble(3, produto.getPrecoCusto());
            pstm.setDouble(4, produto.getPrecoVenda());
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar alterar: " + ex.getMessage());
        }
    }

    public void excluir(Produto produto) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setString(1, produto.getNomeProduto());
            pstm.setInt(2, produto.getQtdeEstoque());
            pstm.setDouble(3, produto.getPrecoCusto());
            pstm.setDouble(4, produto.getPrecoVenda());
            pstm.setInt(5, produto.getIdProduto());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar excluir: " + ex.getMessage());
        }

    }

    public List<Produto> listar() {
        List<Produto> produto = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();

            while (res.next()) {
                Produto p = new Produto();
                Fornecedor f = new Fornecedor();
                p.setIdProduto(res.getInt("id_produto"));
                p.setNomeProduto(res.getString("nome_produto"));
                p.setQtdeEstoque(res.getInt("qtde_estoque"));
                p.setPrecoVenda(res.getDouble("preco_venda"));
                p.setPrecoCusto(res.getDouble("preco_custo"));
                produto.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os produtos do banco: " + ex.getMessage());
        }
        return produto;
    }
}
