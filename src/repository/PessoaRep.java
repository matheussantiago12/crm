package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Pessoa;
import util.ConexaoBD;

public class PessoaRep {

    private static final String INSERT = "insert into pessoa (nome_pessoa, email_pessoa, tel_pessoa) values (?,?,?);";

    private static final String SELECT = "select id_pessoa, nome_pessoa, email_pessoa, tel_pessoa from pessoa";

    private static final String DELETE = "delete from pessoa where id_pessoa = ?";

    private static final String UPDATE = "update pessoa set nome_pessoa = ?,  email_pessoa = ?, tel_pessoa = ? where id_pessoa = ?";

    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;

    public int salvar(Pessoa pessoa) {
        int last_inserted_id = 0;

        try {
            pstm = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, pessoa.getNomePessoa());
            pstm.setString(2, pessoa.getEmailPessoa());
            pstm.setString(3, pessoa.getTelPessoa());
            pstm.executeUpdate();

            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getob
            }

            pstm.close();

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
        return last_inserted_id;
    }

    public void alterar(Pessoa pessoa) {

        try {
            pstm = connection.prepareStatement(UPDATE);
            pstm.setString(1, pessoa.getEmailPessoa());
            pstm.setString(2, pessoa.getNomePessoa());
            pstm.setString(3, pessoa.getTelPessoa());
            pstm.setInt(4, pessoa.getIdPessoa());
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar alterar: " + ex.getMessage());
        }
    }

    public void excluir(Pessoa pessoa) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setInt(1, pessoa.getIdPessoa());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar excluir: " + ex.getMessage());
        }

    }

    public List<Pessoa> listar() {
        List<Pessoa> pessoa = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();

            while (res.next()) {
                Pessoa p = new Pessoa();
                p.setIdPessoa(res.getInt("id_pessoa"));
                p.setNomePessoa(res.getString("nome_pessoa"));
                p.setEmailPessoa(res.getString("email_pessoa"));
                p.setTelPessoa(res.getString("tel_pessoa"));
                pessoa.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar as pessoas do banco: " + ex.getMessage());
        }
        return pessoa;
    }
}
