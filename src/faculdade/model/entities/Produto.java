package faculdade.model.entities;

public class Produto {
	private String nome;
	private Double preco;
	private int quantidade;
	private int idProduto;
	
	public Produto() {
	}

	public Produto(int idProduto, String nome, Double preco, int quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.idProduto = idProduto;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public double valorTotal(int quantidade) {
		return preco * quantidade;
	}

	@Override
	public String toString() {
		return "Produto: " + nome +", ID do produto: "+idProduto +", preco: " + preco + ", quantidade em estoque: " + quantidade;
	}
	
}
