package executavel;

import model.dao.telefonia.EnderecoDAO;
import model.dao.telefonia.TelefoneDAO;
import model.vo.telefonia.Endereco;
import model.vo.telefonia.Telefone;

public class ExecutavelTelefonia {

	public static void main(String[] args) {
		
		Endereco endereco1 = new Endereco("88000123", "Nereu Ramos", "10", "Centro", "Florianópolis", "SC");
		EnderecoDAO dbaDeEnderecos = new EnderecoDAO();
		dbaDeEnderecos.inserir(endereco1);
		if(endereco1.getId() != null) {
			System.out.println("Novo endereço cadastrado");
		}else {
			System.out.println("Erro ao cadastrar endereço");
		}
		
		//Simulando consulta emdereço:
		Endereco enderecoQueJaExiste = dbaDeEnderecos.consultarPorId(1);
		boolean atualizou = dbaDeEnderecos.atualizar(enderecoQueJaExiste);
		if(atualizou) {
			System.out.println("Endereco atulizado com sucesso !");
		}else {
			System.out.println("Erro ao atualizar o endereco :( ");
		}
		
		Telefone telefone1 = new Telefone(1, "48", "99992-4899", true, true);
		TelefoneDAO dbaDeTelefone = new TelefoneDAO();
		dbaDeTelefone.inserir(telefone1);
		if(telefone1.getId() != null) {
			System.out.println("Novo telefone cadastrado");
		}else {
			System.out.println("Erro ao cadastrar telefone");
		}
		
		//Simulando consulta telefone:
		Telefone telefoneQueJaExiste = dbaDeTelefone.consultarPorId(1);
		boolean atualizouTel = dbaDeTelefone.atualizar(telefoneQueJaExiste);
		if(atualizouTel) {
			System.out.println("Telefone atulizado com sucesso !");
		}else {
			System.out.println("Erro ao atualizar o telefone :( ");
		}
		
//		List<Telefone> telefonesDoSocrates = new ArrayList<Telefone>();
//		Telefone telefone1 = new Telefone("48", "32328888", true, false);
//		telefonesDoSocrates.add(telefone1);
//		telefonesDoSocrates.add(new Telefone("48", "98881234", true, true));
//		
//		Cliente pele = new Cliente("Edson Arantes", "11122233344", null, true, endereco1);
//		Cliente socrates = new Cliente("Sócrates Brasileiro", "33322233344", telefonesDoSocrates, true, endereco1);
//		
//		List<Cliente> clientes = new ArrayList<Cliente>();
//		clientes.add(pele);
//		clientes.add(socrates);
//		
//		System.out.println("------------ Clientes da firma ------------");
//		for(Cliente c: clientes) {
//			System.out.println(c.toString());
//		}
//		
//		for(int i=0; i < clientes.size(); i++) {
//			Cliente c = clientes.get(i);
//			System.out.println(c.toString());
//		}
		
	}
}
