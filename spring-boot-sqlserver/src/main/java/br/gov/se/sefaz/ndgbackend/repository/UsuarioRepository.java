package br.gov.se.sefaz.ndgbackend.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, String> {

  @Query(nativeQuery = true)
  UsuarioModel buscaPorCpf(String cpf);

  @Query(nativeQuery = true)
  Slice<UsuarioModel> buscaPorNome(String nome, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<UsuarioModel> buscaPorPerfil(int codPerfil, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<UsuarioModel> buscaPorStatus(int status, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<UsuarioModel> buscaTodos(Pageable pageable);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int cadastra(String cpf, String nome, int codPerfil, String cpfResponsavel);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int atualiza(String cpf, String nome, int codPerfil, int status, String cpfResponsavel);

}