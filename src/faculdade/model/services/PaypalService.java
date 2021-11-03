package faculdade.model.services;

public final class PaypalService implements PagamentoService {
	
	private static final double valorTaxa = 20.0;
	private static final double porcentagemCashback = 0.05;
	
	public Double getTaxaPagamento() {
		return valorTaxa;
	}
	
	public Double getPorcentagemCashback() {
		return porcentagemCashback;
	}
}
