package faculdade.model.services;

public class PicpayService implements PagamentoService {

	private static final double valorTaxa = 50.0;
	private static final double porcentagemCashback = 0.07;
	
	@Override
	public Double taxaPagamento(Double quantia) {
		return quantia + valorTaxa;
	}

	@Override
	public Double calcularCashback(Double quantia) {
		return quantia * porcentagemCashback;
	}
	
}
