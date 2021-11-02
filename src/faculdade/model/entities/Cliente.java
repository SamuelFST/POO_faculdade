package faculdade.model.entities;

import java.util.Date;

public final class Cliente extends Pessoa {
	private String cpf;
	private Date dataNascimento;
	
	public Cliente(String nome, String cpf, Date dataNascimento) {
		super(nome);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
