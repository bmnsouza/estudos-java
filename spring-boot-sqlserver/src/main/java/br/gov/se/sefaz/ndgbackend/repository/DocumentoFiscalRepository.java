package br.gov.se.sefaz.ndgbackend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.DocumentoFiscalModel;

@Repository
public interface DocumentoFiscalRepository extends JpaRepository<DocumentoFiscalModel, String> {

  @Query(nativeQuery = true)
  DocumentoFiscalModel buscaPorNotaFiscal(String numeroNotaFiscal);

  @Query(nativeQuery = true)
  Slice<DocumentoFiscalModel> buscaPorEmitente(String cnpjEmitente, String dataEmissaoInicio, String dataEmissaoFim, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<DocumentoFiscalModel> buscaPorDestinatario(String cpfDestinatario, String dataEmissaoInicio, String dataEmissaoFim, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<DocumentoFiscalModel> buscaPorEmitenteDestinatario(String cnpjEmitente, String cpfDestinatario, String dataEmissaoInicio, String dataEmissaoFim, Pageable pageable);

}