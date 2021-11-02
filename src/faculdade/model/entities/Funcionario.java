package faculdade.model.entities;

public final class Funcionario extends Pessoa {
	private Double ganhoPorProduto;

	public Funcionario () {
	}
	
	public Funcionario(String nome, Double ganhoPorProduto) {
		super(nome);
		this.ganhoPorProduto = ganhoPorProduto;
	}

	public Double getGanhoPorProduto() {
		return ganhoPorProduto;
	}

	public void setGanhoPorProduto(Double ganhoPorProduto) {
		this.ganhoPorProduto = ganhoPorProduto;
	}
}
