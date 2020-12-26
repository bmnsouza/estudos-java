package bmnsouza.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import bmnsouza.exception.ServiceException;

public class DataUtil {

	// Mensagem de erro padrão utilizada no métodos de formatação de data
	private static String MSG_ERRO_FORMATAR = "Não foi possível formatar a data";

	// Formato para hora
	public static final String HHMMSS_DOIS_PONTOS = "HH:mm:ss";

	// Formatos para data
	public static final String AAAAMMDD_BASICO = "uuuuMMdd";
	public static final String AAAAMMDD_HIFEN = "uuuu-MM-dd";
	public static final String DDMMAAAA_BARRA = "dd/MM/uuuu";
	public static final String DDMMAAAA_BASICO = "ddMMuuuu";

	// Formatos para data e hora
	public static final String AAAAMMDD_HHMMSS_BASICO = "uuuuMMdd HHmmss";
	public static final String AAAAMMDD_HHMMSS_HIFEN = "uuuu-MM-dd HH:mm:ss";
	public static final String DDMMAAAA_HHMMSS_BARRA = "dd/MM/uuuu HH:mm:ss";
	public static final String DDMMAAAA_HHMMSS_BASICO = "ddMMuuuu HHmmss";

	/**
	 * Converte uma String de um formato para outro
	 * @param data Data ou data hora a ser formatada
	 * @param formatoOriginal Formato original da data ou data hora
	 * @param formatoDesejado Formato desejado da data ou data hora
	 * @return String convertida no formato desejado
	 * @throws Exception
	 */
	public static String formatar(String data, String formatoOriginal, String formatoDesejado) throws ServiceException {
		try {
			if (data.length() <= 10) {
				LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ofPattern(formatoOriginal).withResolverStyle(ResolverStyle.STRICT));
				return formatar(localDate, formatoDesejado);
			} else {
				LocalDateTime localDateTime = LocalDateTime.parse(data, DateTimeFormatter.ofPattern(formatoOriginal).withResolverStyle(ResolverStyle.STRICT));
				return formatar(localDateTime, formatoDesejado);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServiceException(MSG_ERRO_FORMATAR);
		}
	}

	/**
	 * Converte um localDate em uma String configurada no formato dd/MM/uuuu
	 * @param localDateTime Data a ser formatada
	 * @return String convertida no formato dd/MM/uuuu
	 * @throws Exception
	 */
	public static String formatar(LocalDate localDate) throws ServiceException {
		return formatar(localDate, DDMMAAAA_BARRA);
	}

	/**
	 * Converte um localDate em uma String configurada no formato informado
	 * @param localDate Data a ser formatada
	 * @param formato Formato desejado
	 * @return String convertida no formato informado
	 * @throws Exception
	 */
	public static String formatar(LocalDate localDate, String formato) throws ServiceException {
		try {
			return localDate.format(DateTimeFormatter.ofPattern(formato).withResolverStyle(ResolverStyle.STRICT));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServiceException(MSG_ERRO_FORMATAR);
		}
	}

	/**
	 * Converte um localDateTime em uma String configurada no formato dd/MM/uuuu HH:mm:ss
	 * @param localDateTime Data hora a ser formatada
	 * @return String convertida no formato dd/MM/uuuu HH:mm:ss
	 * @throws Exception
	 */
	public static String formatar(LocalDateTime localDateTime) throws ServiceException {
		return formatar(localDateTime, DDMMAAAA_HHMMSS_BARRA);
	}

	/**
	 * Converte um localDateTime em uma String configurada no formato informado
	 * @param localDateTime Data hora a ser formatada
	 * @param formato Formato desejado
	 * @return String convertida no formato informado
	 * @throws Exception
	 */
	public static String formatar(LocalDateTime localDateTime, String formato) throws ServiceException {
		try {
			return localDateTime.format(DateTimeFormatter.ofPattern(formato).withResolverStyle(ResolverStyle.STRICT));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServiceException(MSG_ERRO_FORMATAR);
		}
	}

}