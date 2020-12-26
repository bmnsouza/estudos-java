package bmnsouza.database.fazendario.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.ProdutoSAE;
import bmnsouza.database.fazendario.entity.id.ProdutoSAEId;

@Repository
public interface ProdutoSAERepository extends JpaRepository<ProdutoSAE, ProdutoSAEId> {
	
	@Query(nativeQuery = true)	
	Slice<ProdutoSAE> buscarTodos(Pageable pageable);
	
	@Query(nativeQuery = true)
	ProdutoSAE buscarPorId(Integer cdProduto, Integer tpProduto);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(Integer tpProduto, String dsProduto, Integer cdGrupo, Integer cdSubGrupo, Integer cdAtvMacro, Integer cdCnae, Integer cdCnaef, Integer cdProdutoGnre);
		
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer cdProduto, Integer tpProduto, String dsProduto, String flAtivo, Integer cdGrupo, Integer cdSubGrupo, Integer cdAtvMacro, Integer cdCnae,
		Integer cdCnaef, Integer cdProdutoGnre);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer cdProduto, Integer tpProduto);

}