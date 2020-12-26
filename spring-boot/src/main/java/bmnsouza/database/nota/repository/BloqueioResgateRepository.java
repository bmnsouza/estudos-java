package bmnsouza.database.nota.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.BloqueioResgate;

@Repository
public interface BloqueioResgateRepository extends JpaRepository<BloqueioResgate, String> {

	@Query(nativeQuery = true)
	BloqueioResgate buscar();

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(LocalDate dataBloqueio, String mensagemBloqueio);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(LocalDate dataBloqueio, String mensagemBloqueio);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(LocalDate dataBloqueio);

}