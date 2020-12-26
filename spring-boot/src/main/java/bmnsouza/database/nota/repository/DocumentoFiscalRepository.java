package bmnsouza.database.nota.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.DocumentoFiscal;

@Repository
public interface DocumentoFiscalRepository extends JpaRepository<DocumentoFiscal, String> {

	@Query(nativeQuery = true)
	DocumentoFiscal buscarPorNotaFiscal(String numeroNotaFiscal);

	@Query(nativeQuery = true)
	Slice<DocumentoFiscal> buscarPorEmitente(String cnpjEmitente, LocalDate dataEmissaoInicio, LocalDate dataEmissaoFim, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<DocumentoFiscal> buscarPorDestinatario(String cpfDestinatario, LocalDate dataEmissaoInicio, LocalDate dataEmissaoFim, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<DocumentoFiscal> buscarPorEmitenteDestinatario(String cnpjEmitente, String cpfDestinatario, LocalDate dataEmissaoInicio, LocalDate dataEmissaoFim,
		Pageable pageable);

}