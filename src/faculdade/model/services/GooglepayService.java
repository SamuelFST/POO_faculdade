package faculdade.model.services;

public class GooglepayService implements PagamentoService {

	private static final double valorTaxa = 70.0;
	private static final double porcentagemCashback = 0.09;
	
	@Override
	public Double taxaPagamento(Double quantia) {
		return quantia + valorTaxa;
	}

	@Override
	public Double calcularCashback(Double quantia) {
		return quantia * porcentagemCashback;
	}
}
