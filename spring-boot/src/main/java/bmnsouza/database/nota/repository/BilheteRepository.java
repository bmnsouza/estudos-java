package bmnsouza.database.nota.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.Bilhete;
import bmnsouza.database.nota.entity.dto.bilhete.BilheteBuscarPercentualEntidadeDTO;
import bmnsouza.database.nota.entity.dto.bilhete.BilheteBuscarQtdeConsumidorPorSorteioDTO;
import bmnsouza.database.nota.entity.dto.bilhete.BilheteBuscarQtdeEntidadeDTO;
import bmnsouza.database.nota.entity.id.BilheteId;
import bmnsouza.database.nota.repository.custom.BilheteRepositoryCustom;

@Repository
public interface BilheteRepository extends JpaRepository<Bilhete, BilheteId>, BilheteRepositoryCustom {

	@Query(nativeQuery = true)
	Slice<Bilhete> buscarPremiadosPorSorteio(Integer codSorteio, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Bilhete> buscarConsumidorPorSorteio(Integer codSorteio, String cpfConsumidor, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Bilhete> buscarEntidadePorSorteio(Integer codSorteio, String cnpjEntidade, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Bilhete> buscarBilhetesPorSorteio(Integer codSorteio, Pageable pageable);

	@Query(nativeQuery = true)
	List<Bilhete> buscarConsumidorSemPremioPorSorteio(Integer codSorteio);

	@Query(nativeQuery = true)
	List<Bilhete> buscarEntidadeSemPremioPorSorteio(Integer codSorteio);

	@Query(nativeQuery = true)
	int buscarQtdeBilhetesPorDatas(LocalDate dataInicio, LocalDate dataFim);

	@Query(nativeQuery = true)
	int buscarQtdeBilhetesPorSorteio(Integer codSorteio);

	@Query(nativeQuery = true)
	int buscarQtdePremiadosPorSorteio(Integer codSorteio);

	@Query(nativeQuery = true)
	Slice<BilheteBuscarQtdeConsumidorPorSorteioDTO> buscarQtdeConsumidorPorSorteio(int codSorteio, Pageable pageable);

	@Query(nativeQuery = true)
	List<BilheteBuscarQtdeEntidadeDTO> buscarQtdeEntidade(LocalDate dataInicio, LocalDate dataFim);

	@Query(nativeQuery = true)
	Slice<BilheteBuscarPercentualEntidadeDTO> buscarPercentualEntidadeBilhete(int codSorteio, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<BilheteBuscarPercentualEntidadeDTO> buscarPercentualEntidadeIndicacao(int codSorteio, Pageable pageable);

	@Transactional
	int cadastrar(List<Bilhete> bilhete);

	@Transactional
	int atualizar(List<Bilhete> bilhete);

}