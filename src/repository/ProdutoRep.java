package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;
import model.Pessoa;
import model.Produto;
import util.ConexaoBD;

public class ProdutoRep {

    private static final String INSERT = "insert into produto (id_fornecedor, nome_produto, qtde_estoque, preco_custo, preco_venda) values (?,?,?,?,?);";

    private static final String SELECT = "select id_produto, id_fornecedor, nome_produto, qtde_estoque, preco_custo, preco_venda from produto order by id_fornecedor";

    private static final String SELECT_FORNECEDOR = "select cnpj_fornecedor, nome_contato from fornecedor inner join produto on fornecedor.id_fornecedor = produto.id_fornecedor order by fornecedor.id_fornecedor";

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
            pstm.setInt(5, produto.getIdProduto());
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar alterar: " + ex.getMessage());
        }
    }

    public void excluir(Produto produto) {
       try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setInt(1, produto.getIdProduto());
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

    public List<Fornecedor> listarFornecedor() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT_FORNECEDOR);
            res = pstm.executeQuery();

            while (res.next()) {
                Fornecedor f = new Fornecedor();
                f.setCnpjFornecedor(res.getString("cnpj_fornecedor"));
                f.setNomeContato(res.getString("nome_contato"));

                fornecedores.add(f);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os Fornecedor do banco: " + ex.getMessage());
        }
        return fornecedores;
    }
}
