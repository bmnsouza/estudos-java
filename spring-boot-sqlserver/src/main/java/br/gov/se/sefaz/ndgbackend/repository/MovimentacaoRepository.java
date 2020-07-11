package br.gov.se.sefaz.ndgbackend.repository;

import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.MovimentacaoModel;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoModel, Integer> {

  @Query(nativeQuery = true)
  Slice<MovimentacaoModel> buscaPorContaCorrente(Integer idContaCorrente, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<MovimentacaoModel> buscaPorCpf(String cpf, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<MovimentacaoModel> buscaPorSorteio(Integer codSorteio, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<MovimentacaoModel> buscaTodos(Pageable pageable);

}