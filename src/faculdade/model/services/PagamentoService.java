package faculdade.model.services;

public interface PagamentoService {
	Double taxaPagamento(Double quantia);
	Double calcularCashback(Double quantia);
}
