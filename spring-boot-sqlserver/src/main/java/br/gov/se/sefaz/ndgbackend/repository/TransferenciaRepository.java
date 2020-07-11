package br.gov.se.sefaz.ndgbackend.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.TransferenciaModel;

@Repository
public interface TransferenciaRepository extends JpaRepository<TransferenciaModel, Integer> {

  @Query(nativeQuery = true)
  Slice<TransferenciaModel> buscaPorCpf(String cpf, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<TransferenciaModel> buscaPorDataRelatorio(String dataRelatorio, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<TransferenciaModel> buscaPorStatus(int status, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<TransferenciaModel> buscaTodos(Pageable pageable);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int atualizaParaInvalida(Integer id);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int atualizaParaConferencia();

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int atualizaParaResgate();

}