package br.gov.se.sefaz.ndgbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.PerfilModel;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilModel, Integer> {

  @Query(nativeQuery = true)
  PerfilModel buscaPorId(Integer id);

  @Query(nativeQuery = true)
  List<PerfilModel> buscaTodos();

}