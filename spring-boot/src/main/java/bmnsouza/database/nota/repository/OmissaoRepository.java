package bmnsouza.database.nota.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.Omissao;
import bmnsouza.database.nota.entity.id.OmissaoId;

@Repository
public interface OmissaoRepository extends JpaRepository<Omissao, OmissaoId> {

	@Query(nativeQuery = true)
	Slice<Omissao> buscarPorIe(String ie, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Omissao> buscarPorCnpj(String cnpj, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Omissao> buscarPorAnoMes(int ano, int mes, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Omissao> buscarPorRbaMenorQue(BigDecimal rba, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Omissao> buscarPorRbaIgualA(BigDecimal rba, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Omissao> buscarPorRbaMaiorQue(BigDecimal rba, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Omissao> buscarPorDataEntrada(LocalDate dataEntrada, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Omissao> buscarTodos(Pageable pageable);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(String ie, String cnpj, int ano, int mes, String cpfResponsavel, String protocolo);

}