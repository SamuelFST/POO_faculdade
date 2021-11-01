package faculdade.model.services;

public final class PaypalService implements PagamentoService {
	
	private static final double valorTaxa = 20.0;
	private static final double porcentagemCashback = 0.05;
	
	@Override
	public Double taxaPagamento(Double quantia) {
		return quantia + valorTaxa;
	}

	@Override
	public Double calcularCashback(Double quantia) {
		return quantia * porcentagemCashback;
	}
	
}
