package bmnsouza.database.nota.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.SorteioPremio;
import bmnsouza.database.nota.entity.dto.sorteioPremio.SorteioBuscarBilhetesPorSorteioDTO;
import bmnsouza.database.nota.entity.id.SorteioPremioId;
import bmnsouza.database.nota.repository.custom.SorteioPremioRepositoryCustom;

@Repository
public interface SorteioPremioRepository extends JpaRepository<SorteioPremio, SorteioPremioId>, SorteioPremioRepositoryCustom {

	@Query(nativeQuery = true)
	Slice<SorteioPremio> buscarPorSorteio(Integer codSorteio, Pageable pageable);

	@Query(nativeQuery = true)
	List<SorteioPremio> buscarPorSorteioTipoPremio(Integer codSorteio, String tpPremio);

	@Query(nativeQuery = true)
	Slice<SorteioBuscarBilhetesPorSorteioDTO> buscarBilhetesPorSorteio(Integer codSorteio, Pageable pageable);

	@Query(nativeQuery = true)
	int buscarQtdePremiosPorSorteio(Integer codSorteio);

	@Transactional
	int cadastrar(List<SorteioPremio> sorteioPremio);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int transferir(Integer codSorteio);

}