package br.gov.se.sefaz.ndgbackend.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;

public class Data {

  // Mensagem de erro padrão utilizada no métodos de formatação de data
  private static String MSG_ERRO_FORMATA = "Não foi possível formatar a data";

  // Formatos para data
  public static final String AAAAMMDD_BASICA = "uuuuMMdd";
  public static final String AAAAMMDD_HIFEN = "uuuu-MM-dd";
  public static final String DDMMAAAA_BARRA = "dd/MM/uuuu";
  public static final String DDMMAAAA_BASICA = "ddMMuuuu";

  // Formatos para data hora
  public static final String AAAAMMDD_HHMMSS_BASICA = "uuuuMMdd HHmmss";
  public static final String AAAAMMDD_HHMMSS_HIFEN = "uuuu-MM-dd HH:mm:ss";
  public static final String DDMMAAAA_HHMMSS_BARRA = "dd/MM/uuuu HH:mm:ss";
  public static final String DDMMAAAA_HHMMSS_BASICA = "ddMMuuuu HHmmss";

  /**
   * Verifica se a String informada é uma data válida para o formato dd/MM/uuuu
   * @param data data a ser validada
   * @return true or false
   */
  public static boolean isValida(String data) {
    return isValida(data, DDMMAAAA_BARRA);
  }

  /**
   * Verifica se a String informada é uma data válida para o formato informado
   * @param data data ou data hora a ser validada
   * @param formato formato da data ou data hora a ser validada
   * @return true or false
   */
  public static boolean isValida(String data, String formato) {
    try {
      DateTimeFormatter.ofPattern(formato).withResolverStyle(ResolverStyle.STRICT).parse(data);
    } catch (Exception ex) {
      return false;
    }
    return true;
  }

  /**
   * Converte uma String de um formato para outro
   * @param data data ou data hora a ser formatada
   * @param formatoOriginal formato original da data ou data hora
   * @param formatoDesejado formato desejado da data ou data hora
   * @return String convertida no formato desejado
   * @throws Exception
   */
  public static String formata(String data, String formatoOriginal, String formatoDesejado) throws TransactionAbortException {
    try {
      if (data.length() <= 10) {
        LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ofPattern(formatoOriginal));
        return formata(localDate, formatoDesejado);
      } else {
        LocalDateTime localDateTime = LocalDateTime.parse(data, DateTimeFormatter.ofPattern(formatoOriginal));
        return formata(localDateTime, formatoDesejado);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new TransactionAbortException(MSG_ERRO_FORMATA);
    }
  }

  /**
   * Converte um localDate em uma String configurada no formato dd/MM/uuuu
   * @param localDateTime data a ser formatada
   * @return String convertida no formato dd/MM/uuuu
   * @throws Exception
   */
  public static String formata(LocalDate localDate) throws TransactionAbortException {
    return formata(localDate, DDMMAAAA_BARRA);
  }

  /**
   * Converte um localDate em uma String configurada no formato informado
   * @param localDate data a ser formatada
   * @param formato formato desejado
   * @return String convertida no formato informado
   * @throws Exception
   */
  public static String formata(LocalDate localDate, String formato) throws TransactionAbortException {
    try {
      return localDate.format(DateTimeFormatter.ofPattern(formato));
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new TransactionAbortException(MSG_ERRO_FORMATA);
    }
  }

  /**
   * Converte um localDateTime em uma String configurada no formato dd/MM/uuuu HH:mm:ss
   * @param localDateTime data hora a ser formatada
   * @return String convertida no formato dd/MM/uuuu HH:mm:ss
   * @throws Exception
   */
  public static String formata(LocalDateTime localDateTime) throws TransactionAbortException {
    return formata(localDateTime, DDMMAAAA_HHMMSS_BARRA);
  }

  /**
   * Converte um localDateTime em uma String configurada no formato informado
   * @param localDateTime data hora a ser formatada
   * @param formato formato desejado
   * @return String convertida no formato informado
   * @throws Exception
   */
  public static String formata(LocalDateTime localDateTime, String formato) throws TransactionAbortException {
    try {
      return localDateTime.format(DateTimeFormatter.ofPattern(formato));
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new TransactionAbortException(MSG_ERRO_FORMATA);
    }
  }

}

/*

  public void data() {
    System.out.println("--------------------------------------------------------------");
    System.out.println(">>> Formatando data com a classe DateTimeFormatter");
    LocalDate hoje = LocalDate.now();
    DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatadorTraco = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    System.out.println("Data com /: " + hoje.format(formatadorBarra));
    System.out.println("Data com -: " + hoje.format(formatadorTraco));

    System.out.println("--------------------------------------------------------------");
    System.out.println(">>> Classe LocalDate");
    LocalDate localDate = LocalDate.now();
    System.out.println(localDate);
    System.out.println("Dia da semana: " + localDate.getDayOfWeek().name());
    System.out.println("Dia da semana: " + localDate.getDayOfWeek().ordinal());
    System.out.println("Mes: " + localDate.getMonthValue());
    System.out.println("Mes: " + localDate.getMonth().name());
    System.out.println("Ano: " + localDate.getYear());

    System.out.println("--------------------------------------------------------------");
    System.out.println(">>> Calculando a duração de um evento na API de datas antiga");
    long instateInicial = System.currentTimeMillis();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    long instanteFinal = System.currentTimeMillis();
    long duracaoEmMilesegundos = instanteFinal - instateInicial;
    System.out.println("Duração em segundos: " + (duracaoEmMilesegundos / 1000) % 60);

    System.out.println("--------------------------------------------------------------");
    System.out.println(">>> Calculando a duração de um evento na nova API de datas");
    Instant iInicial = Instant.now();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Instant iFinal = Instant.now();
    Duration duracao = Duration.between(iInicial, iFinal);
    System.out.println("Duração em nanos segundos: " + duracao.toNanos());
    System.out.println("Duração em minutos: " + duracao.toMinutes());
    System.out.println("Duração em horas: " + duracao.toHours());
    System.out.println("Duração em milisegundos: " + duracao.toMillis());
    System.out.println("Duração em dias: " + duracao.toDays());

    System.out.println("--------------------------------------------------------------");
    System.out.println(">>> Comparando duas datas");
    LocalDate localDateAntigo = LocalDate.of(2010, 3, 7);
    LocalDate localDateNovo = LocalDate.of(2015, 3, 5);
    System.out.println(localDateAntigo.isAfter(localDateNovo));
    System.out.println(localDateAntigo.isBefore(localDateNovo));
    System.out.println(localDateAntigo.isEqual(localDateNovo));
    Period periodo = Period.between(localDateAntigo, localDateNovo);
    System.out.println(periodo.getYears() + " Anos " + periodo.getMonths() + " Meses " + periodo.getDays() + " Dias");
    System.out.println("Apenas meses: " + periodo.toTotalMonths());

    System.out.println("--------------------------------------------------------------");
    System.out.println(">>> Comparando duas datas");
    LocalDate dataManipulacao = LocalDate.now();
    System.out.println("Mais 5 dias:" + dataManipulacao.plusDays(5));
    System.out.println("Mais 5 semanas:" + dataManipulacao.plusWeeks(5));
    System.out.println("Mais 5 anos:" + dataManipulacao.plusYears(5));
    System.out.println("Mais 2 meses:" + dataManipulacao.plusMonths(2));
    System.out.println("Menos 1 ano:" + dataManipulacao.minusYears(1));
    System.out.println("Menos 1 mês:" + dataManipulacao.minusMonths(1));
    System.out.println("Menos 3 dia: " + dataManipulacao.minusDays(3));
    System.out.println("Data Original:" + dataManipulacao);

    System.out.println("--------------------------------------------------------------");
    System.out.println(">>> Comparando datas com fuso horário");
    LocalDateTime hora = LocalDateTime.of(2016, Month.APRIL, 4, 22, 30);
    ZoneId fusoHorarioDeSaoPaulo = ZoneId.of("America/Sao_Paulo");
    ZonedDateTime horaSaoPaulo = ZonedDateTime.of(hora, fusoHorarioDeSaoPaulo);
    System.out.println(horaSaoPaulo);
    ZoneId fusoHorarioDeParis = ZoneId.of("Europe/Paris");
    ZonedDateTime horaParis = ZonedDateTime.of(hora, fusoHorarioDeParis);
    System.out.println(horaParis);
    Duration diferencaDeHoras = Duration.between(horaSaoPaulo, horaParis);
    System.out.println(diferencaDeHoras.getSeconds() / 60 / 60);

    System.out.println("--------------------------------------------------------------");
    System.out.println(">>> Mais alguns métodos interessantes");
    LocalDate data = LocalDate.now();
    System.out.println("Ano bissexto: " + data.isLeapYear());
    System.out.println("Número de dias do mês: " + data.lengthOfMonth());
    System.out.println("Número de dias do ano: " + data.lengthOfYear());
    System.out.println("Menor data: " + LocalDate.MIN);
    System.out.println("Maior data: " + LocalDate.MAX);

    System.out.println("--------------------------------------------------------------");
    System.out.println(">>> LocalDate");
    LocalDate ld = LocalDate.now();
    System.out.println("LocalDate: " + ld);
    System.out.println("BASIC_ISO_DATE: " + ld.format(DateTimeFormatter.BASIC_ISO_DATE));
    System.out.println("ISO_DATE: " + ld.format(DateTimeFormatter.ISO_DATE));
    System.out.println("ISO_LOCAL_DATE: " + ld.format(DateTimeFormatter.ISO_LOCAL_DATE));
    System.out.println("ISO_ORDINAL_DATE: " + ld.format(DateTimeFormatter.ISO_ORDINAL_DATE));
    System.out.println("ISO_WEEK_DATE: " + ld.format(DateTimeFormatter.ISO_WEEK_DATE));

    System.out.println("--------------------------------------------------------------");
    System.out.println(">>> LocalDateTime");
    LocalDateTime ldt = LocalDateTime.now();
    System.out.println("LocalDateTime: " + ldt);
    System.out.println("BASIC_ISO_DATE: " + ldt.format(DateTimeFormatter.BASIC_ISO_DATE));
    System.out.println("ISO_DATE: " + ldt.format(DateTimeFormatter.ISO_DATE));
    System.out.println("ISO_DATE_TIME: " + ldt.format(DateTimeFormatter.ISO_DATE_TIME));
    System.out.println("ISO_LOCAL_DATE: " + ldt.format(DateTimeFormatter.ISO_LOCAL_DATE));
    System.out.println("ISO_LOCAL_DATE_TIME: " + ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    System.out.println("ISO_LOCAL_TIME: " + ldt.format(DateTimeFormatter.ISO_LOCAL_TIME));
    System.out.println("ISO_ORDINAL_DATE: " + ldt.format(DateTimeFormatter.ISO_ORDINAL_DATE));
    System.out.println("ISO_TIME: " + ldt.format(DateTimeFormatter.ISO_TIME));
    System.out.println("ISO_WEEK_DATE: " + ldt.format(DateTimeFormatter.ISO_WEEK_DATE));
  }

*/