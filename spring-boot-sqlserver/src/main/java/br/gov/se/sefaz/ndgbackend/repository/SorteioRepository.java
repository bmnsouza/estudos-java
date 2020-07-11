package br.gov.se.sefaz.ndgbackend.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.SorteioModel;

@Repository
public interface SorteioRepository extends JpaRepository<SorteioModel, Integer> {

  @Query(nativeQuery = true)
  SorteioModel buscaPorCodigo(Integer codSorteio);

  @Query(nativeQuery = true)
  Slice<SorteioModel> buscaTodos(Pageable pageable);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int cadastra(Integer codSorteio, String dataInicio, String dataFim, String dataRealizacao, String observacao);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int atualiza(Integer codSorteio, Integer faixaInicio, Integer faixaFim, String sementeBilhete, Integer hashCodeSementeBilhete, int status,
    String dataRealizacao, Integer concursoLoteria, String premiosLoteria, String sementeSorteio, Integer hashCodeSementeSorteio);

}