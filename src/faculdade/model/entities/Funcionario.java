package faculdade.model.entities;

public final class Funcionario extends Pessoa {
	private Double ganhos;

	public Funcionario () {
	}
	
	public Funcionario(String nome, Double ganhos) {
		super(nome);
		this.ganhos = ganhos;
	}

	public Double getGanhos() {
		return ganhos;
	}

	public void setGanhos(Double ganhos) {
		this.ganhos = ganhos;
	}
}
