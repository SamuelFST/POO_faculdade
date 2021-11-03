package faculdade.model.services;

public class PicpayService implements PagamentoService {

	private static final double valorTaxa = 50.0;
	private static final double porcentagemCashback = 0.07;
	
	public Double getTaxaPagamento() {
		return valorTaxa;
	}
	
	public Double getPorcentagemCashback() {
		return porcentagemCashback;
	}
}
