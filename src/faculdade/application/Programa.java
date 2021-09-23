package faculdade.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
		List<Produto> listaProduto = new ArrayList<>();
		
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
				System.out.print("Nome do produto: ");
				String nomeProduto = sc.nextLine().toUpperCase();
				System.out.print("Preco do produto: ");
				Double preco = sc.nextDouble();
				System.out.print("Quantidade disponivel: ");
				int quantidade = sc.nextInt();
				Produto produto = new Produto(nomeProduto, preco, quantidade);
				listaProduto.add(produto);
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
			for(Produto produtos : listaProduto) {
				System.out.println("--------------------------------------------------------------");
				System.out.println("Fornecedor: "+fornecedor.getRazaoSocial()+" - CNPJ "+ fornecedor.getCnpj()+" - TEL "+fornecedor.getTelefone());
				System.out.println(produtos);
				System.out.println("--------------------------------------------------------------");
			}
			
			System.out.print("\nDigite o nome do produto que voce deseja comprar: ");
			String procuraNome= sc.nextLine().toUpperCase();
			System.out.print("Digite a quantidade: ");
			int quantidadeCompra= sc.nextInt();
			Produto buscaNome = buscaProduto(listaProduto, procuraNome);
			
			if(buscaNome != null) {
				if(quantidadeCompra > buscaNome.getQuantidade()) {
					System.out.println("Quantidade acima do disponivel para compra, digite um valor menor.");
				}
				else {
				System.out.println("\nVoce comprou "+quantidadeCompra+" unidades do produto "+buscaNome.getNome()+"!");
				System.out.println("\n************************************");
				System.out.println("Lista de compra: ");
				System.out.println(buscaNome.getNome()+", "+quantidadeCompra+" unidades, total: "+buscaNome.valorTotal(quantidadeCompra));
				System.out.println("************************************");
				}
			}
			else if(buscaNome == null) {
				System.out.println("Nome de produto invalido, nao existem produtos com esse nome.");
			}
		} 
		catch(RuntimeException e) {
			System.out.println("Erro inesperado");
		}
		sc.close();
	}
	
	public static Produto buscaProduto(List<Produto> listaProduto, String procuraNome) {
		for(Produto produto : listaProduto) {
			if(procuraNome.equals(produto.getNome())) {
				return produto;
			}
		}
		return null;
	}
}
