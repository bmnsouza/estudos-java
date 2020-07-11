package br.gov.se.sefaz.ndgbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.FazendarioPortalModel;

@Repository
public interface FazendarioPortalRepository extends JpaRepository<FazendarioPortalModel, String> {

  @Query(nativeQuery = true)
  FazendarioPortalModel buscaPorCpf(String cpf);

  @Query(nativeQuery = true)
  List<FazendarioPortalModel> buscaPorNome(String nome);

}