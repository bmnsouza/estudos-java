package bmnsouza.constant;

public class TransferenciaConstant {

	// Status transferência
	public static final int CONTA_INVALIDA = -1;
	public static final int AGUARDANDO_CONFERENCIA = 0;
	public static final int CONFERENCIA_BANCO = 1;
	public static final int RESGATE_CONCLUIDO = 2;
	public static final int CARGA_IGESP = 3;

	// Tipo conta
	public static final int CONTA_CORRENTE = 1;
	public static final int POUPANCA = 2;

	// Banco
	public static final String BANESE = "047";

	// Mensagem transferência
	public static final String MENSAGEM_TRANSFERENCIA = new StringBuilder("A pessoa que estiver inadimplente com o Estado de Sergipe, em relação a obrigações pecuniárias ")
		.append("de natureza tributária ou não tributária, estará impedida de receber, utilizar, transferir ou solicitar o depósito de seus créditos, enquanto permanecer ")
		.append("nessa situação, conf. determina o §5º do art. 4º do Decreto nº 28.022/2011.").toString();

}