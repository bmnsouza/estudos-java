package bmnsouza.util.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import bmnsouza.util.result.EntidadeResult.Paginacao;
import bmnsouza.util.result.EntidadeResult.Result;

@Component
public class ResultUtil {

	// Mensagens de retorno
	public static final String MENSAGEM_SUCESSO = "Operação realizada com sucesso";
	public static final String MENSAGEM_ERRO = "Não foi possível realizar a operação";

	public ResponseEntity<EntidadeResult> resultSucesso(HttpStatus httpStatus, String msgUsuario) {
		EntidadeResult result = entidadeResult(httpStatus.value(), "", msgUsuario, new HashMap<>());
		return ResponseEntity.status(httpStatus).body(result);
	}

	public ResponseEntity<EntidadeResult> resultSucesso(HttpStatus httpStatus, String msgUsuario, Object dados) {
		EntidadeResult result = entidadeResult(httpStatus.value(), "", msgUsuario, dados);
		return ResponseEntity.status(httpStatus).body(result);
	}

	public ResponseEntity<EntidadeResult> resultErro(HttpStatus httpStatus, String msgUsuario) {
		return resultErro(httpStatus, "", msgUsuario);
	}

	public ResponseEntity<EntidadeResult> resultErro(HttpStatus httpStatus, Throwable msgTecnica, String msgUsuario) {
		return resultErro(httpStatus, msgTecnica.toString(), msgUsuario);
	}

	public ResponseEntity<EntidadeResult> resultErro(HttpStatus httpStatus, String msgTecnica, String msgUsuario) {
		EntidadeResult result = entidadeResult(httpStatus.value(), msgTecnica, msgUsuario, null);
		return ResponseEntity.status(httpStatus).body(result);
	}

	private EntidadeResult entidadeResult(int cdRetorno, String msgTecnica, String msgUsuario, Object dados) {
		Result result = new EntidadeResult().new Result();
		result.setCdRetorno(cdRetorno);

		if (msgUsuario != null) {
			result.setMsgUsuario(msgUsuario);
		}

		if (msgTecnica != null) {
			result.setMsgTecnica(msgTecnica);
		}

		if (dados != null) {
			if (isSlice(dados)) {
				dados = obtemDadosSlice((Slice<?>)dados);
			} else if (isArrayList(dados)) {
				dados = obtemDadosArrayList((ArrayList<?>)dados);
			}
			result.setDados(dados);
		}

		return new EntidadeResult(result);
	}

	private boolean isSlice(Object object) {
		return (object instanceof Slice);
	}

	private boolean isArrayList(Object object) {
		return (object instanceof ArrayList);
	}

	private Object obtemDadosSlice(Slice<?> slice) {
		Object dados = new HashMap<>();

		// Verifica se o objeto possui conteúdo
		if (slice.getContent().size() > 0) {
			// Adiciona o conteúdo à lista
			List<Object> lista = new ArrayList<Object>();
			lista.add(slice.getContent());
			
			// Obtém os dados da paginação
			Paginacao paginacao = new EntidadeResult().new Paginacao();
			paginacao.setPagina(slice.getNumber());
			paginacao.setElementos(slice.getNumberOfElements());
			paginacao.setTamanho(slice.getSize());
			paginacao.setConteudo(slice.hasContent());
			paginacao.setAnterior(slice.hasPrevious());
			paginacao.setProxima(slice.hasNext());
			paginacao.setPrimeira(slice.isFirst());
			paginacao.setUltima(slice.isLast());
			
			// Adiciona paginacao à lista
			Map<String, Object> hashPaginacao = new HashMap<>();
			hashPaginacao.put("paginacao", paginacao);
			lista.add(hashPaginacao);

			dados = lista;
		}

		return dados;
	}

	private Object obtemDadosArrayList(ArrayList<?> arrayList) {
		Object dados = new HashMap<>();

		// Verifica se o objeto possui conteúdo
		if (arrayList.size() > 0) {
			dados = arrayList;
		}

		return dados;
	}

}