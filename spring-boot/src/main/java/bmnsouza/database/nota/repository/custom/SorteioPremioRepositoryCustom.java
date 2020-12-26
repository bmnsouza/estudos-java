package bmnsouza.database.nota.repository.custom;

import java.util.List;

import bmnsouza.database.nota.entity.SorteioPremio;

public interface SorteioPremioRepositoryCustom {

	int cadastrar(List<SorteioPremio> sorteioPremio);

}