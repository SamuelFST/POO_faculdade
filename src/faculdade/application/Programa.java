package faculdade.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import faculdade.model.entities.Cliente;
import faculdade.model.entities.Fornecedor;
import faculdade.model.entities.Produto;

public class Programa {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Produto produto = new Produto();
		
		try {
			System.out.println("Dados do fornecedor");
			System.out.print("Digite sua razao social: ");
			String razaoSocial = sc.nextLine();
			System.out.print("Digite seu CNPJ: ");
			String cnpj = sc.nextLine();
			System.out.print("Digite seu telefone: ");
			String telefone = sc.nextLine();
			Fornecedor fornecedor = new Fornecedor(razaoSocial, cnpj, telefone);

			System.out.print("\nDigite a quantidade de produtos a serem cadastrados: ");
			int quantidadeCadastros = sc.nextInt();

			for (int i = 1; i <= quantidadeCadastros; i++) {
				System.out.println("\nDados do produto #" + i);
				sc.nextLine();
				System.out.print("Digite qual sera o id numerico do produto: ");
				int idProduto = sc.nextInt();
				sc.nextLine();
				System.out.print("Nome do produto: ");
				String nomeProduto = sc.nextLine().toUpperCase();
				System.out.print("Preco do produto: ");
				Double preco = sc.nextDouble();
				System.out.print("Quantidade disponivel: ");
				int quantidade = sc.nextInt();
				Produto prod = new Produto(idProduto, nomeProduto, preco, quantidade);
				produto.adicionarProduto(prod);
			}
			
			System.out.println("\n*** Produtos cadastrados ***");
			System.out.println();
			sc.nextLine();
			System.out.println("Dados do cliente: ");
			System.out.print("Nome: ");
			String nome = sc.nextLine();
			System.out.print("CPF: ");
			String cpf = sc.nextLine();
			System.out.print("Data de nascimento(DD/MM/AAAA): ");
			Date dataNascimento = sdf.parse(sc.nextLine());
			Cliente cliente = new Cliente(nome, cpf, dataNascimento);
			
			System.out.println("\nProdutos disponiveis para compra: ");
			for(Produto produtos : produto.getListaProduto()) {
				System.out.println("----------------------------------------------------------------------------");
				System.out.println("Fornecedor: "+fornecedor.getRazaoSocial()+" - CNPJ "+ fornecedor.getCnpj()+" - TEL "+fornecedor.getTelefone());
				System.out.println(produtos);
				System.out.println("----------------------------------------------------------------------------");
			}
			
			System.out.print("\nDigite a ID do produto que voce deseja comprar: ");
			int procuraID= sc.nextInt();
			System.out.print("Digite a quantidade de unidades para comprar: ");
			int quantidadeCompra= sc.nextInt();
			Produto buscaID = Produto.buscaProduto(produto.getListaProduto(), procuraID);
			
			if(buscaID != null) {
				if(quantidadeCompra > buscaID.getQuantidade()) {
					System.out.println("Quantidade acima do disponivel para compra, digite um valor menor.");
				}
				else {
				System.out.println("\nVoce comprou "+quantidadeCompra+" unidades do produto "+buscaID.getNome()+"!");
				System.out.println("\n***************************************");
				System.out.println("Lista de compra: ");
				System.out.println(buscaID.getNome()+", "+quantidadeCompra+" unidades, total: "+buscaID.valorTotal(quantidadeCompra));
				System.out.println("---------------------------------------");
				System.out.println("Cliente: "+cliente.getNome()+", CPF Num. "+cliente.getCpf());
				System.out.println("***************************************");
				}
			}
			else if(buscaID == null) {
				System.out.println("ID invalido, esse ID nao esta associado a um produto.");
			}
		} 
		catch(RuntimeException e) {
			System.out.println("Erro inesperado");
		}
		sc.close();
	}
	
	/*public static Produto buscaProduto(List<Produto> listaProduto, int procuraNome) {
		for(Produto produto : listaProduto) {
			if(procuraNome == produto.getIdProduto()) {
				return produto;
			}
		}
		return null;
	}*/
}
