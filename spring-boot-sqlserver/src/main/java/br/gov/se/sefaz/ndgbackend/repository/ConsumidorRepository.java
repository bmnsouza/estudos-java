package br.gov.se.sefaz.ndgbackend.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.ConsumidorModel;

@Repository
public interface ConsumidorRepository extends JpaRepository<ConsumidorModel, Integer> {

  @Query(nativeQuery = true)
  ConsumidorModel buscaPorId(Integer id);

  @Query(nativeQuery = true)
  ConsumidorModel buscaPorCpf(String cpf);

  @Query(nativeQuery = true)
  Slice<ConsumidorModel> buscaPorNome(String nome, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<ConsumidorModel> buscaTodos(Pageable pageable);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int cadastra(String cpf, String nome, String dataNascimento, String logradouro, String numero, String complemento, String bairro, String cep, String municipio,
    String uf, String ddd, String telefone, String email, String frase, String fraseEmail, Integer idEntidade, String senha);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int atualiza(Integer id, String cpf, String nome, String dataNascimento, String logradouro, String numero, String complemento, String bairro, String cep,
    String municipio, String uf, String ddd, String telefone, String email, String frase, String fraseEmail, Integer idEntidade, String estado, String situacao,
    String cpfResponsavel, String justificativa);

}