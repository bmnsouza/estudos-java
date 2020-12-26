package bmnsouza.database.nota.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Value;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;

import bmnsouza.database.nota.entity.Bilhete;
import bmnsouza.database.nota.repository.custom.BilheteRepositoryCustom;

public class BilheteRepositoryImpl implements BilheteRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Value("${nota.datasource.jdbc-url}")
	private String URL;

	@Value("${nota.datasource.username}")
	private String USERNAME;
	
	@Value("${nota.datasource.password}")
	private String PASSWORD;
	
	@Override
	public int cadastrar(List<Bilhete> listaBilhete) throws SQLException, SQLServerException {
		// Obtém a conexão
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		try {
			// Cria um data table em memória
			SQLServerDataTable dataTable = new SQLServerDataTable();

			// Define metadata para o data table
			dataTable.setTvpName("dbo.tipoBilhete");
			dataTable.addColumnMetadata("bil_numbilhete", java.sql.Types.INTEGER);
			dataTable.addColumnMetadata("sor_codsorteio", java.sql.Types.INTEGER);
			dataTable.addColumnMetadata("sop_codpremio", java.sql.Types.INTEGER);
			dataTable.addColumnMetadata("bil_cpfcnpj", java.sql.Types.VARCHAR);
			dataTable.addColumnMetadata("bil_cnpjentidade", java.sql.Types.VARCHAR);

			// Preenche o data table
			for (Bilhete bilhete : listaBilhete) {
				dataTable.addRow(bilhete.getNumBilhete(), bilhete.getCodSorteio(), bilhete.getCodPremio(), bilhete.getCpfConsumidor(), bilhete.getCnpjEntidade());
			}

			// Passa o data table como parâmetro usando prepared statement
			SQLServerPreparedStatement pStmt = (SQLServerPreparedStatement)connection.prepareStatement("exec NFP_ContaCorrente..sp_cadastrar_bilhetes ?");
			pStmt.setStructured(1, dataTable.getTvpName(), dataTable);
			pStmt.execute();

			return listaBilhete.size();
			
		} finally {
			connection.close();
		}
	}

	@Override
	public int atualizar(List<Bilhete> bilhete) {
		StringBuilder update = new StringBuilder("update NFP_ContaCorrente..TB_BIL_BILHETE set sop_codpremio = b.codpremio from (values ");

		bilhete.forEach(sorteado -> {
			update.append('(').append(sorteado.getNumBilhete()).append(',').append(sorteado.getCodPremio()).append("),");
		});

		update.setLength(update.length()-1);
		update.append(") b (numbilhete, codpremio) where sor_codsorteio = ").append(bilhete.get(0).getCodSorteio()).append(" and bil_numbilhete = b.numbilhete");

		return entityManager.createNativeQuery(update.toString()).executeUpdate();
	}

}