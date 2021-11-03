package faculdade.model.services;

public interface PagamentoService {
	
	Double getTaxaPagamento();
	Double getPorcentagemCashback();
	
	default Double taxaPagamento(Double quantia) {
		return quantia + getTaxaPagamento();
	}
	
	default Double calcularCashback(Double quantia) {
		return quantia * getPorcentagemCashback();
	}
}
