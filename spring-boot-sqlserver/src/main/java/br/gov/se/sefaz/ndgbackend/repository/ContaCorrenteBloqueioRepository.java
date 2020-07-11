package br.gov.se.sefaz.ndgbackend.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.ContaCorrenteBloqueioModel;

@Repository
public interface ContaCorrenteBloqueioRepository extends JpaRepository<ContaCorrenteBloqueioModel, Integer> {

  @Query(nativeQuery = true)
  ContaCorrenteBloqueioModel buscaPorId(Integer id);

  @Query(nativeQuery = true)
  Slice<ContaCorrenteBloqueioModel> buscaPorContaCorrente(Integer idContaCorrente, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<ContaCorrenteBloqueioModel> buscaPorCpf(String cpf, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<ContaCorrenteBloqueioModel> buscaTodos(Pageable pageable);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int cadastra(String cpf, char contaBloqueada, String cpfResponsavel, String justificativa);

}