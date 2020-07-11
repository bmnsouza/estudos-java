package br.gov.se.sefaz.ndgbackend.repository;

import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.ContaCorrenteModel;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrenteModel, Integer> {

  @Query(nativeQuery = true)
  ContaCorrenteModel buscaPorId(Integer id);

  @Query(nativeQuery = true)
  ContaCorrenteModel buscaPorCpf(String cpf);

  @Query(nativeQuery = true)
  Slice<ContaCorrenteModel> buscaTodos(Pageable pageable);

}