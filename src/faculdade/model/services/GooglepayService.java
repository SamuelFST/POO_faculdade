package faculdade.model.services;

public class GooglepayService implements PagamentoService {

	private static final double valorTaxa = 70.0;
	private static final double porcentagemCashback = 0.09;
	
	public Double getTaxaPagamento() {
		return valorTaxa;
	}
	
	public Double getPorcentagemCashback() {
		return porcentagemCashback;
	}
}
