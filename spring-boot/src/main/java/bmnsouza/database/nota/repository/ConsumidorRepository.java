package bmnsouza.database.nota.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.Consumidor;

@Repository
public interface ConsumidorRepository extends JpaRepository<Consumidor, Integer> {

	@Query(nativeQuery = true)
	Consumidor buscarPorId(Integer id);

	@Query(nativeQuery = true)
	Consumidor buscarPorCpf(String cpf);

	@Query(nativeQuery = true)
	Slice<Consumidor> buscarPorNome(String nome, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Consumidor> buscarTodos(Pageable pageable);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(String cpf, String nome, LocalDate dataNascimento, String logradouro, String numero, String complemento, String bairro, String cep, String municipio,
		String uf, String ddd, String telefone, String email, String frase, String fraseEmail, Integer idEntidade, String senha);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer id, String cpf, String nome, LocalDate dataNascimento, String logradouro, String numero, String complemento, String bairro, String cep,
		String municipio, String uf, String ddd, String telefone, String email, String frase, String fraseEmail, Integer idEntidade, String estado, String situacao,
		String cpfResponsavel, String justificativa);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizarSenha(Integer id, String senha, String fraseEmail, String cpfResponsavel);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizarTentativaLoginSucesso(String cpf);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizarTentativaLoginErro(String cpf);

}