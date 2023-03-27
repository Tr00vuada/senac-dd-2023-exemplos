package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.Banco;
import model.vo.telefonia.Endereco;
import model.vo.telefonia.Telefone;

public class TelefoneDAO {
	
	public Telefone inserir(Telefone novoTelefone) {
		
		Connection conexao = Banco.getConnection();
		String sql =  " INSERT INTO TELEFONE (IDCLIENTE, DDD, NUMERO, ATIVO, "
					+ " MOVEL) "
				    + " VALUES (?,?,?,?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			query.setInt(1, novoTelefone.getIdCliente() != null ? novoTelefone.getIdCliente(): 0);
			query.setString(2, novoTelefone.getDdd());
			query.setString(3, novoTelefone.getNumero());
			query.setBoolean(4, novoTelefone.isAtivo());
			query.setBoolean(5, novoTelefone.isMovel());
			query.execute();
			
			ResultSet resultado = query.getGeneratedKeys();
			if(resultado.next()) {
				novoTelefone.setId(resultado.getInt(1));
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir endereço. "
					+ "\nCausa: " + e.getMessage());
		}finally {
			//Fechar a conexão
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return novoTelefone;
		
	}
	
	public boolean atualizar(Telefone telefoneEditado) {

		boolean atualizou = false;
		
		Connection conexao = Banco.getConnection();
		String sql = "UPDATE TELEFONE "
				+ "	SET IDCLIENTE = ?, DDD = ?, NUMERO = ?, ATIVO = ?, MOVEL = ? "
				+ "	WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		try {
			query.setInt(1, telefoneEditado.getIdCliente());
			query.setString(2, telefoneEditado.getDdd());
			query.setString(3, telefoneEditado.getNumero());
			query.setBoolean(4, telefoneEditado.isAtivo());
			query.setBoolean(5, telefoneEditado.isMovel());
			query.setInt(6, telefoneEditado.getId());
			
			int quantidadeLinhasAtualizadas = query.executeUpdate();
			atualizou = quantidadeLinhasAtualizadas > 0;
			
		}catch(SQLException excecao) {
			System.out.println("Erro ao atualizar telefone. " + "\nCausa: " + excecao.getMessage());
			
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return atualizou;
	}
	
	public Telefone consultarPorId(int id) {
		Telefone telefoneConsultado = null;
		
		Connection conexao = Banco.getConnection();
		String sql = "SELECT * FROM TELEFONE "
				+ "	WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
				telefoneConsultado = new Telefone();
				telefoneConsultado.setId(resultado.getInt("id"));
				telefoneConsultado.setIdCliente(resultado.getInt("idcliente"));
				telefoneConsultado.setDdd(resultado.getString("ddd"));
				telefoneConsultado.setNumero(resultado.getString("numero"));
				telefoneConsultado.setAtivo(resultado.getBoolean("ativo"));
				telefoneConsultado.setMovel(resultado.getBoolean("movel"));
			}
			
			
		}catch(SQLException e){
			System.out.println("Erro ao buscar telefone com id: " + "\nCausa: " + e.getMessage());
			
		}
		
		return telefoneConsultado;
	}

	public boolean exluir(int id) {
		boolean excluiu = false;
		
		Connection conexao = Banco.getConnection();
		String sql = "DELETE FROM TELEFONE "
				+ "	WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		try {
			query.setInt(1, id);
	
			int quantidadeLinhasAtualizadas = query.executeUpdate();
			excluiu = quantidadeLinhasAtualizadas > 0;
			
		}catch(SQLException excecao) {
			System.out.println("Erro ao excluir telefone. " + "\nCausa: " + excecao.getMessage());
			
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return excluiu;
	}

}


