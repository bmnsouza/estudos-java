package bmnsouza.database.nota.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.nota.entity.BloqueioResgate;
import bmnsouza.database.nota.repository.BloqueioResgateRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class BloqueioResgateService {

	@Autowired
	private BloqueioResgateRepository bloqueioResgateRepository;

	@Autowired
	private ResultUtil resultUtil;

	public ResponseEntity<EntidadeResult> buscar() {
		BloqueioResgate dados = bloqueioResgateRepository.buscar();
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> cadastrar(BloqueioResgate bloqueioResgate) {
		bloqueioResgateRepository.cadastrar(bloqueioResgate.getDataBloqueio(), bloqueioResgate.getMensagemBloqueio());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizar(BloqueioResgate bloqueioResgate) {
		bloqueioResgateRepository.atualizar(bloqueioResgate.getDataBloqueio(), bloqueioResgate.getMensagemBloqueio());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> remover(LocalDate dataBloqueio) {
		bloqueioResgateRepository.remover(dataBloqueio);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public BloqueioResgate verificar() {
		// Somente está bloqueado se a data do bloqueio for menor ou igual à atual
		BloqueioResgate dados = bloqueioResgateRepository.buscar();
		if (dados != null && (LocalDate.now().until(dados.getDataBloqueio(), ChronoUnit.DAYS) > 0)) {
			dados = null;
		}
		return dados;
	}

}