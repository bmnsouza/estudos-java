package br.gov.se.sefaz.ndgbackend.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.OmissaoModel;
import br.gov.se.sefaz.ndgbackend.model.id.OmissaoModelId;

@Repository
public interface OmissaoRepository extends JpaRepository<OmissaoModel, OmissaoModelId> {

  @Query(nativeQuery = true)
  Slice<OmissaoModel> buscaPorIe(String ie, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<OmissaoModel> buscaPorCnpj(String cnpj, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<OmissaoModel> buscaPorAnoMes(int ano, int mes, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<OmissaoModel> buscaPorRbaMenorQue(BigDecimal rba, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<OmissaoModel> buscaPorRbaIgualA(BigDecimal rba, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<OmissaoModel> buscaPorRbaMaiorQue(BigDecimal rba, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<OmissaoModel> buscaPorDataEntrada(String dataEntrada, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<OmissaoModel> buscaTodos(Pageable pageable);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int exclui(String ie, String cnpj, int ano, int mes, String cpfResponsavel, String protocolo);

}