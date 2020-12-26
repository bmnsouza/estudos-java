package bmnsouza.database.nota.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.ContaCorrenteBloqueio;

@Repository
public interface ContaCorrenteBloqueioRepository extends JpaRepository<ContaCorrenteBloqueio, Integer> {

	@Query(nativeQuery = true)
	ContaCorrenteBloqueio buscarBloqueioPorCpf(String cpf);

	@Query(nativeQuery = true)
	Slice<ContaCorrenteBloqueio> buscarPorCpf(String cpf, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<ContaCorrenteBloqueio> buscarTodos(Pageable pageable);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(String cpf, String contaBloqueada, String cpfResponsavel, String justificativa);

}