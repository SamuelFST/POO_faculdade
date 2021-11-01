package faculdade.model.services;

public final class PaypalService implements PagamentoService {
	
	private static final double porcentagemTaxa = 0.02;
	private static final double porcentagemCashback = 0.05;
	
	@Override
	public Double taxaPagamento(Double quantia) {
		return quantia * porcentagemTaxa;
	}

	@Override
	public Double calcularCashback(Double quantia) {
		return quantia * porcentagemCashback;
	}
	
}
