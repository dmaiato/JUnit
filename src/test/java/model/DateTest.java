package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateTest {

  @Test
  void verificaDataMinima() {
    Date date = new Date();
    assertEquals(1, date.day);
    assertEquals(1, date.month);
    assertEquals(1900, date.year);
  }

  // @Test
  // void isImmutable() {
  //   date.day = 5;
  //   date.month = 1;
  //   date.year = 2001;
  // }

  @Test
  void jogaErroSeHouverAmbiguidadeEntreDiaEAno() {
    assertThrowsExactly(
      IllegalArgumentException.class, () -> {
        new Date(1900, 7, 2016);
      }
    );
  }

  @Test
  void jogaErroComAnoMenorQueMinimoNaPosicaoFinal() {
    assertThrowsExactly(
      IllegalArgumentException.class, () -> {
        new Date(16, 7, 1899);
      }
    );
  }

  @Test
  void jogaErroComAnoMenorQueMinimoNaPosicaoInicial() {
    assertThrowsExactly(
      IllegalArgumentException.class, () -> {
        new Date(1899, 7, 16);
      }
    );
  }

  @Test
  void jogaErroComMesMaiorQueMaximo() {
    assertThrowsExactly(
      IllegalArgumentException.class, () -> {
        new Date(2016, 13, 16);
      }
    );
  }

  @Test
  void jogaErroComMesMenorQueMinimo() {
    assertThrowsExactly(
      IllegalArgumentException.class, () -> {
        new Date(2016, -1, 16);
      }
    );
  }

  @Test
  void jogaErroComDiaMaiorQueMaximo() {
    assertThrowsExactly(
      IllegalArgumentException.class, () -> {
        new Date(2016, 12, 32);
      }
    );
  }

  @Test
  void jogaErroComDiaMenorQueMinimo() {
    assertThrowsExactly(
      IllegalArgumentException.class, () -> {
        new Date(2016, 12, -1);
      }
    );
  }

  @Test
  void jogaErroComDia30EmFevereiro() {
    assertThrowsExactly(
      IllegalArgumentException.class, () -> {
        new Date(2016, 2, 30);
      }
    );
  }

  @Test
  void verificaFormatacaoToStringComDiaEMesMenoresQue10() {
    Date d = new Date(5, 5, 2005);
    assertTrue(d.toString().equals("05/05/2005"));
  }

  @Test
  void verificaFormatacaoToStringComApenasDiaMenorQue10() {
    Date d = new Date(1, 10, 2001);
    assertTrue(d.toString().equals("01/10/2001"));
  }

  @Test
  void verificaSeDatasSaoIguais() {
    Date d1 = new Date(1, 7, 2014);
    Date d2 = new Date(1, 7, 2014);
    assertTrue(d1.equals(d2) == true);
  }

  @Test
  void verificaDesigualdadeDeDatas() {
    Date d1 = new Date(1, 7, 2014);
    assertFalse(d1.equals(new Date()));
  }

  @Test
  void verificaApenasDiaMaior() {
    Date d1 = new Date(2, 7, 2014);
    Date d2 = new Date(1, 7, 2014);
    assertTrue(d1.after(d2));
  }

  @Test
  void verificaApenasMesMaior() {
    Date d1 = new Date(1, 8, 2014);
    Date d2 = new Date(1, 7, 2014);
    assertTrue(d1.after(d2));
  }

  @Test
  void verificaApenasAnoMaior() {
    Date d1 = new Date(1, 7, 2015);
    Date d2 = new Date(1, 1, 2014);
    assertTrue(d1.after(d2));
  }
  
  @Test
  void verificaFuncionalidadeDoYesterday() {
    Date d1 = new Date(1, 7, 2015);
    Date d2 = d1.yesterday();
    assertTrue(d2.toString().equals("30/06/2015"));
  }
  
  @Test
  void verificaFuncionalidadeDoYesterdayEmAnoBissexto() {
    Date d1 = new Date(1, 3, 2020);
    Date d2 = d1.yesterday();
    assertTrue(d2.toString().equals("29/02/2020"));
  }
  
  @Test
  void confereImutabilidadeDaDataAnteriorNoYesterday() {
    Date d1 = new Date(28, 2, 2020);
    Date d2 = d1.yesterday();
    assertTrue(d1.toString().equals("28/02/2020"));
  }
  
  @Test
  void verificaFuncionalidadeDoTomorrow() {
    Date d1 = new Date(31, 12, 2015);
    Date d2 = d1.tomorrow();
    assertTrue(d2.toString().equals("01/01/2016"));
  }
  
  @Test
  void verificaFuncionalidadeDoTomorrowEmAnoBissexto() {
    Date d1 = new Date(28, 2, 2020);
    Date d2 = d1.tomorrow();
    assertTrue(d2.toString().equals("29/02/2020"));
  }
  
  @Test
  void confereImutabilidadeDaDataAnteriorNoTomorrow() {
    Date d1 = new Date(28, 2, 2020);
    Date d2 = d1.tomorrow();
    assertTrue(d1.toString().equals("28/02/2020"));
  }
  
  @Test
  void verificaFuncionalidadeDoPlusDays() {
    Date d1 = new Date(31, 12, 2014);
    Date d2 = d1.plusDays(1600);
    assertTrue(d2.toString().equals("19/05/2019"));
  }
  
  @Test
  void verificaFuncionalidadeDoMinusDays() {
    Date d1 = new Date(31, 12, 2014);
    Date d2 = d1.minusDays(1600);
    assertTrue(d2.toString().equals("14/08/2010"));
  }

  @Test
  void verificaFuncionalidadeDoTomorrowEncadeado() {
    Date q = new Date(31, 12, 2014);
    for (int i = 0; i < 400; i++) q = q.tomorrow();
    assertTrue(q.toString().equals("04/02/2016"));
  }

  @Test
  void verificaFuncionalidadeDoYesterdayEncadeado() {
    Date q = new Date(31, 12, 2014);
    for (int i = 0; i < 400; i++) q = q.yesterday();
    assertTrue(q.toString().equals("26/11/2013"));
  }

}
