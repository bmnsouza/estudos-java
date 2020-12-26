package bmnsouza.database.fazendario.repository.custom;

import java.math.BigInteger;

import bmnsouza.database.fazendario.entity.dto.documentoArrecadacao.DocumentoArrecadacaoCadastrarDTO;

public interface DocumentoArrecadacaoRepositoryCustom {
	
	int cadastrar(BigInteger nrDAE, DocumentoArrecadacaoCadastrarDTO documentoArrecadacaoCadastrarDTO);

}