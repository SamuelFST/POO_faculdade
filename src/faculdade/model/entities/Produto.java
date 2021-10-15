package faculdade.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Produto {
	private String nome;
	private Double preco;
	private int quantidade;
	private int idProduto;
	List<Produto> listaProduto = new ArrayList<>();
	
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
	
	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public double valorTotal(int quantidade) {
		return preco * quantidade;
	}

	public void atualizarEstoque(Produto produto, int quantidade) {
		if(produto.quantidade > 0) {
			produto.quantidade = produto.quantidade - quantidade;
		}
		if(produto.quantidade == 0) {
			listaProduto.remove(produto);
		}
	}
	
	public void adicionarProduto(Produto produto) {
		listaProduto.add(produto);
	}
	
	public static Produto buscaProduto(List<Produto> listaProduto, int procuraID) {
		for(Produto produto : listaProduto) {
			if(procuraID == produto.getIdProduto()) {
				return produto;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "Produto: " + nome +", ID do produto: "+idProduto +", preco: " + preco + ", quantidade em estoque: " + quantidade;
	}
}
