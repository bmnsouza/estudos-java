package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.VencimentoIcms;
import bmnsouza.database.fazendario.entity.dto.vencimentoIcms.VencimentoIcmsAtualizarDTO;
import bmnsouza.database.fazendario.entity.dto.vencimentoIcms.VencimentoIcmsCadastrarDTO;
import bmnsouza.database.fazendario.repository.VencimentoIcmsRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class VencimentoIcmsService {

	@Autowired
	private VencimentoIcmsRepository vencimentoIcmsRepository;
	
	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;
	
	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<VencimentoIcms> dados = vencimentoIcmsRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> buscarPorId(Integer nrSequencialVencimentoIcms, Integer nrAno) {
		VencimentoIcms dados = vencimentoIcmsRepository.buscarPorId(nrSequencialVencimentoIcms, nrAno);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> cadastrar(VencimentoIcmsCadastrarDTO vencimentoIcmsCadastrarDTO) {
		vencimentoIcmsRepository.cadastrar(vencimentoIcmsCadastrarDTO.getNrAno(),
				vencimentoIcmsCadastrarDTO.getTpContribuinte(), vencimentoIcmsCadastrarDTO.getNrFormaRecolhimentoIcms(),
				vencimentoIcmsCadastrarDTO.getNrObjetoReferencia(), vencimentoIcmsCadastrarDTO.getCdCategoriaAtvEcon(),
				vencimentoIcmsCadastrarDTO.getCdSubgrupo(), vencimentoIcmsCadastrarDTO.getCdAtvMacro(),
				vencimentoIcmsCadastrarDTO.getCdCnae(), vencimentoIcmsCadastrarDTO.getCdCnaef(),
				vencimentoIcmsCadastrarDTO.getNrParcelaApuracao(), vencimentoIcmsCadastrarDTO.getFlMesVencimentoSubsequente(),
				vencimentoIcmsCadastrarDTO.getNrDiaVencimento(), vencimentoIcmsCadastrarDTO.getNrPortaria(),
				vencimentoIcmsCadastrarDTO.getNrAnoPortaria(), vencimentoIcmsCadastrarDTO.getNrPrioridadePortaria(),
				vencimentoIcmsCadastrarDTO.getNrMesRefInicial(), vencimentoIcmsCadastrarDTO.getNrAnoRefInicial(),
				vencimentoIcmsCadastrarDTO.getNrMesRefFinal(), vencimentoIcmsCadastrarDTO.getNrAnoRefFinal(),
				vencimentoIcmsCadastrarDTO.getCdPessoa(), vencimentoIcmsCadastrarDTO.getCdProduto(),
				vencimentoIcmsCadastrarDTO.getTpProduto(), vencimentoIcmsCadastrarDTO.getNrQtdMesSubsequente());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> atualizar(VencimentoIcmsAtualizarDTO vencimentoIcmsAtualizarDTO) {
		vencimentoIcmsRepository.atualizar(vencimentoIcmsAtualizarDTO.getNrSequencialVencimentoIcms(),
				vencimentoIcmsAtualizarDTO.getNrAno(), vencimentoIcmsAtualizarDTO.getTpContribuinte(),
				vencimentoIcmsAtualizarDTO.getNrFormaRecolhimentoIcms(), vencimentoIcmsAtualizarDTO.getNrObjetoReferencia(), 
				vencimentoIcmsAtualizarDTO.getCdCategoriaAtvEcon(),	vencimentoIcmsAtualizarDTO.getCdSubgrupo(), 
				vencimentoIcmsAtualizarDTO.getCdAtvMacro(), vencimentoIcmsAtualizarDTO.getCdCnae(), 
				vencimentoIcmsAtualizarDTO.getCdCnaef(), vencimentoIcmsAtualizarDTO.getNrParcelaApuracao(),
				vencimentoIcmsAtualizarDTO.getFlMesVencimentoSubsequente(),	vencimentoIcmsAtualizarDTO.getNrDiaVencimento(), 
				vencimentoIcmsAtualizarDTO.getNrPortaria(),	vencimentoIcmsAtualizarDTO.getNrAnoPortaria(), 
				vencimentoIcmsAtualizarDTO.getNrPrioridadePortaria(), vencimentoIcmsAtualizarDTO.getNrMesRefInicial(), 
				vencimentoIcmsAtualizarDTO.getNrAnoRefInicial(), vencimentoIcmsAtualizarDTO.getNrMesRefFinal(), 
				vencimentoIcmsAtualizarDTO.getNrAnoRefFinal(), vencimentoIcmsAtualizarDTO.getCdPessoa(), 
				vencimentoIcmsAtualizarDTO.getCdProduto(),	vencimentoIcmsAtualizarDTO.getTpProduto(), 
				vencimentoIcmsAtualizarDTO.getNrQtdMesSubsequente());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> remover(Integer nrSequencialVencimentoIcms, Integer nrAno) {
		vencimentoIcmsRepository.remover(nrSequencialVencimentoIcms, nrAno);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}