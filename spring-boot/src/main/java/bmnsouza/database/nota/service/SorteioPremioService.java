package bmnsouza.database.nota.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.nota.entity.SorteioPremio;
import bmnsouza.database.nota.entity.dto.sorteio.SorteioDTO;
import bmnsouza.database.nota.entity.dto.sorteioPremio.SorteioBuscarBilhetesPorSorteioDTO;
import bmnsouza.database.nota.repository.SorteioPremioRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class SorteioPremioService {

	@Autowired
	private SorteioPremioRepository sorteioPremioRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarPorSorteio(Integer codSorteio, int pagina) {
		Slice<SorteioPremio> dados = sorteioPremioRepository.buscarPorSorteio(codSorteio, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarBilhetesPorSorteio(Integer codSorteio, int pagina) {
		Slice<SorteioBuscarBilhetesPorSorteioDTO> dados = sorteioPremioRepository.buscarBilhetesPorSorteio(codSorteio, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> cadastrar(List<SorteioPremio> sorteioPremio) {
		sorteioPremioRepository.cadastrar(sorteioPremio);
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> transferir(SorteioDTO sorteioDTO) {
		sorteioPremioRepository.transferir(sorteioDTO.getCodSorteio());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}