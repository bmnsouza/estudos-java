package br.gov.se.sefaz.ndgbackend.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.SaldoModel;

@Repository
public interface SaldoRepository extends JpaRepository<SaldoModel, Integer> {

  @Query(nativeQuery = true)
  SaldoModel buscaPorId(Integer id);

  @Query(nativeQuery = true)
  SaldoModel buscaPorCpf(String cpf);

  @Query(nativeQuery = true)
  Slice<SaldoModel> buscaPorSaldoMenorQue(BigDecimal saldo, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<SaldoModel> buscaPorSaldoIgualA(BigDecimal saldo, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<SaldoModel> buscaPorSaldoMaiorQue(BigDecimal saldo, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<SaldoModel> buscaTodos(Pageable pageable);

}