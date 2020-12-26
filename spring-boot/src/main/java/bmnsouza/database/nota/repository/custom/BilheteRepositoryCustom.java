package bmnsouza.database.nota.repository.custom;

import java.sql.SQLException;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import bmnsouza.database.nota.entity.Bilhete;

public interface BilheteRepositoryCustom {

	int cadastrar(List<Bilhete> bilhete) throws SQLException, SQLServerException;

	int atualizar(List<Bilhete> bilhete);

}