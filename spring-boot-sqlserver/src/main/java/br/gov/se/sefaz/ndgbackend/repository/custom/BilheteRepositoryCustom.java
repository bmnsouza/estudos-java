package br.gov.se.sefaz.ndgbackend.repository.custom;

import java.sql.SQLException;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import br.gov.se.sefaz.ndgbackend.model.BilheteModel;

public interface BilheteRepositoryCustom {

  public int cadastra(List<BilheteModel> bilheteModel) throws SQLException, SQLServerException;

  public int atualiza(List<BilheteModel> bilheteModel);

}