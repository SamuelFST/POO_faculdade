package faculdade.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import faculdade.model.entities.Cliente;
import faculdade.model.entities.Fornecedor;
import faculdade.model.entities.Funcionario;
import faculdade.model.entities.Produto;
import faculdade.model.services.GooglepayService;
import faculdade.model.services.PagamentoService;
import faculdade.model.services.PaypalService;
import faculdade.model.services.PicpayService;

public class Programa {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Produto produto = new Produto();
		Fornecedor fornecedor = new Fornecedor();
		Funcionario funcionario = new Funcionario();
		PagamentoService pagamento = null;

		try {
			menu(sc, produto, fornecedor, funcionario, pagamento);
		} 
		catch (RuntimeException e) {
			System.out.println("Erro inesperado");
		} 
		finally {
			sc.close();
		}
	}

	public static void menu(Scanner sc, Produto produto, Fornecedor fornecedor, Funcionario funcionario, PagamentoService pagamento) throws ParseException {
		int gerarID = 1;
		int opcao = 0;
		
		while (opcao != 3) {
			System.out.println("\nDigite a opcao que deseja: ");
			System.out.println("1 - Cadastrar fornecedor e seus produtos");
			System.out.println("2 - Login como cliente para comprar produtos");
			System.out.println("3 - Sair do programa");
			System.out.print("Sua opcao: ");
			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {

			case 1:
				System.out.println("\nDados do fornecedor");
				System.out.print("Digite sua razao social: ");
				String razaoSocial = sc.nextLine();
				System.out.print("Digite seu CNPJ: ");
				String cnpj = sc.nextLine();
				System.out.print("Digite seu telefone: ");
				String telefone = sc.nextLine();
				fornecedor = new Fornecedor(razaoSocial, cnpj, telefone);

				System.out.print("\nDigite a quantidade de produtos a serem cadastrados: ");
				int quantidadeCadastros = sc.nextInt();
				
				for (int i = 1; i <= quantidadeCadastros; i++) {
					System.out.println("\nDados do produto #" + i);
					sc.nextLine();
					System.out.println("ID numerico do produto: "+gerarID);
					System.out.print("Nome do produto: ");
					String nomeProduto = sc.nextLine().toUpperCase();
					System.out.print("Preco do produto: ");
					Double preco = sc.nextDouble();
					System.out.print("Quantidade disponivel: ");
					int quantidade = sc.nextInt();
					sc.nextLine();
					System.out.print("Nome do funcionario responsavel pelo produto: ");
					String nomeFuncionario = sc.nextLine().toUpperCase();
					System.out.print("Valor que o funcionario vai receber por produto vendido: ");
					double ganhoPorProduto = sc.nextDouble();
					funcionario = new Funcionario(nomeFuncionario, ganhoPorProduto);
					Produto prod = new Produto(fornecedor, funcionario, gerarID, nomeProduto, preco, quantidade);
					produto.adicionarProduto(prod);
					gerarID++;
				}
				System.out.println("\n*** Produtos cadastrados ***");
				System.out.println();
				sc.nextLine();
				break;

			case 2:
				System.out.println("\nDados do cliente: ");
				System.out.print("Nome: ");
				String nome = sc.nextLine();
				System.out.print("CPF: ");
				String cpf = sc.nextLine();
				System.out.print("Data de nascimento(DD/MM/AAAA): ");
				Date dataNascimento = sdf.parse(sc.nextLine());
				Cliente cliente = new Cliente(nome, cpf, dataNascimento);

				if (produto.getListaProduto().isEmpty()) {
					System.out.println("Nao ha produtos cadastrados no momento, entre como fornecedor para cadastrar");
				} else {
					System.out.println("\nProdutos disponiveis para compra: ");
					for (Produto produtos : produto.getListaProduto()) {
						System.out.println(
								"----------------------------------------------------------------------------");
						System.out.println("Fornecedor: " + produtos.getFornecedor().getRazaoSocial() + " - CNPJ "
								+ produtos.getFornecedor().getCnpj() + " - TEL "
								+ produtos.getFornecedor().getTelefone());
						System.out.println(
								"Funcionario responsavel pelo produto: " + produtos.getFuncionario().getNome());
						System.out.println(produtos);
						System.out.println(
								"----------------------------------------------------------------------------");
					}

					System.out.print("\nDigite a ID do produto que voce deseja comprar: ");
					int procuraID = sc.nextInt();
					System.out.print("Digite a quantidade de unidades para comprar: ");
					int quantidadeCompra = sc.nextInt();
					Produto buscaID = Produto.buscaProduto(produto.getListaProduto(), procuraID);

					if (buscaID != null) {
						if (quantidadeCompra > buscaID.getQuantidade()) {
							System.out.println("Quantidade acima do disponivel para compra, digite um valor menor.");
						} else {
							System.out.println("\nEscolha uma opcao de pagamento: ");
							System.out.println("1 - PayPal (Taxa de R$20 e cashback de 5%)");
							System.out.println("2 - PicPay (Taxa de R$50 e cashback de 7%)");
							System.out.println("3 - GooglePay (Taxa de R$70 e cashback de 9%)");
							int opcaoPagamento = sc.nextInt();
							if (opcaoPagamento == 1) {
								pagamento = new PaypalService();
							} else if (opcaoPagamento == 2) {
								pagamento = new PicpayService();
							} else if (opcaoPagamento == 3) {
								pagamento = new GooglepayService();
							} else {
								System.out.println("Opcao de pagamento nao reconhecida");
								break;
							}

							System.out.println("\nVoce comprou " + quantidadeCompra + " unidades do produto "
									+ buscaID.getNome() + "!");
							System.out.println("\n***************************************");
							System.out.println("Lista de compra: ");
							System.out.println(buscaID.getNome() + ", " + quantidadeCompra + " unidades, total: R$ "
									+ pagamento.taxaPagamento(buscaID.valorTotal(quantidadeCompra)));
							System.out.println("---------------------------------------");
							System.out.println("Compra realizada em: " + sdf2.format(new Date()));
							System.out.println("Cashback ganho com a compra: R$ " + String.format("%.2f",
									pagamento.calcularCashback(buscaID.valorTotal(quantidadeCompra))));
							System.out.println("Cliente: " + cliente.getNome() + ", CPF Num. " + cliente.getCpf());
							System.out.println("---------------------------------------");
							System.out.println("O(a) vendedor(a) " + buscaID.getFuncionario().getNome()
									+ " recebeu uma comissao \nde R$ "
									+ (buscaID.getFuncionario().getGanhoPorProduto() * quantidadeCompra)
									+ " por essa venda");
							System.out.println("***************************************");
							produto.atualizarEstoque(buscaID, quantidadeCompra);
						}
					} else if (buscaID == null) {
						System.out.println("ID invalido, esse ID nao esta associado a um produto.");
					}
				}
				break;

			case 3:
				System.out.println("\nPrograma encerrado");
				break;
				
			default:
				System.out.println("Opcao nao reconhecida");
			}

		}
	}
}

