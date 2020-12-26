package bmnsouza.database.nota.entity.dto.transferencia;

import java.math.BigDecimal;

public interface TransferenciaResgateDTO {

	String getCpf();

	String getNome();

	String getLogradouro();

	String getNumero();

	String getBairro();

	String getCep();

	String getMunicipio();

	String getUf();

	String getCodBanco();

	String getNomeBanco();

	String getAgencia();

	String getDigitoAgencia();

	String getConta();

	String getDigitoConta();

	String getTipoConta();

	BigDecimal getValorCredito();

	BigDecimal getValorCreditoBruto();

	BigDecimal getValorDescontoIR();

	String getOperacao();

	int getStatus();

}