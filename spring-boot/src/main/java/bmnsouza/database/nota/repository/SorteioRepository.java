package bmnsouza.database.nota.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.Sorteio;
import bmnsouza.database.nota.entity.dto.sorteio.SorteioExpiradosDTO;

@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Integer> {

	@Query(nativeQuery = true)
	Sorteio buscarPorCodigo(Integer codSorteio);

	@Query(nativeQuery = true)
	Slice<SorteioExpiradosDTO> buscarExpirados(Pageable pageable);
	
	@Query(nativeQuery = true)
	List<SorteioExpiradosDTO> buscarNaoExpirados();

	@Query(nativeQuery = true)
	Slice<Sorteio> buscarTodos(Pageable pageable);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(Integer codSorteio, LocalDate dataInicio, LocalDate dataFim, LocalDate dataRealizacao, String observacao);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer codSorteio, Integer faixaInicio, Integer faixaFim, String sementeBilhete, Integer hashCodeSementeBilhete, int status,
		LocalDate dataRealizacao, Integer concursoLoteria, String premiosLoteria, String sementeSorteio, Integer hashCodeSementeSorteio);

}