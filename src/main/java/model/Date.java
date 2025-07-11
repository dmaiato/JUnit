package model;

public final class Date {

  private final int MINIMUM_YEAR = 1900;
  
  public final int day;
  public final int month;
  public final int year;

  public Date() {
    this.day = 1;
    this.month = 1;
    this.year = MINIMUM_YEAR;
  }

  public Date(int p1, int month, int p2) throws IllegalArgumentException {
    if (!isValidEntry(p1) || !isValidEntry(p2))
      throw new IllegalArgumentException("Invalid date: day/year check");
    if (unitParse(p1) == unitParse(p2))
      throw new IllegalArgumentException("Invalid date: ambiguity check");
    if (!isValidMonth(month))
      throw new IllegalArgumentException("Invalid date: month check");

    this.month = month;
    if (unitParse(p1) == 'D') {
      this.day = p1;
      this.year = p2;
    } else {
      this.day = p2;
      this.year = p1;
    }
    if (!isValidDayToMonth()) throw new IllegalArgumentException("Invalid date: leapyear or day of month check");
  }

  private boolean isValidEntry(int p) {
    if (p >= 1 && p <= 31)
      return true;
    else if (p > MINIMUM_YEAR)
      return true;
    return false;
  }

  private boolean isValidMonth(int month) {
    if (month < 1 || month > 12) 
      return false;
    return true;
  }

  private char unitParse(int p) {
    if (p >= 1 && p <= 31) return 'D';
    else return 'Y';
  }

  public boolean isLeapYear() {
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
      return true;
    return false;
  }

  public boolean isLeapYear(int year) {
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
      return true;
    return false;
  }

  private boolean isValidLeapYearDate() {
    if (isLeapYear() && month == 2 && day > 29) {
      return false;
    }
    return true;
  }

  public int lengthOfMonth() {
    return switch (month) {
      case 2 -> (isLeapYear() ? 29 : 28);
      case 4, 6, 9, 11 -> 30;
      default -> 31;
    };
  }

  public int lengthOfMonth(int month, int year) {
    return switch (month) {
      case 2 -> (isLeapYear(year) ? 29 : 28);
      case 4, 6, 9, 11 -> 30;
      default -> 31;
    };
  }

  private boolean isValidDayToMonth() {
    // verificacao especial de ano bissexto
    if (isLeapYear()) {
      if (isValidLeapYearDate()) return true;
      else return false;
    } else {
      if (month == 2 && day > 28) return false;
    }

    // validação convencional
    if (day <= 30) return true;

    // validação dos dias 31
    if (month <= 7) {
      if (month % 2 == day % 2) return true; // Mês e dia são ímpares
    } else {
      if (month % 2 != day % 2) return true; // inverte a lógica, culpa do imperador César Augusto
    }
    return false;
  }

  private String numberString(int num) {
    if (num < 10) return "0" + num;
    else return "" + num;
  }

  public String toString() {
    return numberString(day) + "/" + numberString(month) + "/" + year;
  }

  public boolean equals(Date date) {
    if (toInt() == date.toInt()) return true;
    return false;
  }

  public int toInt() {
    String parser = "" + year + numberString(month) + numberString(day);
    return Integer.parseInt(parser);
  }

  public boolean after(Date date) {
    if (toInt() > date.toInt()) return true;
    else return false;
  }

  public boolean before(Date date) {
    if (toInt() < date.toInt()) return true;
    else return false;
  }

  // fiquei orgulhoso desse aqui
  public Date plusDays(int daysInput) {
    int monthAux = month;
    int dayAux = day;
    int yearAux = year;
    for (int i = daysInput; i >= 0 ;) {
      // se a adição de dias ultrapassar o limite do mês
      if (i + dayAux > lengthOfMonth(monthAux, yearAux)) { 
        int difference = lengthOfMonth(monthAux, yearAux) - dayAux;
        monthAux++;
        i -= difference;
        dayAux = 0; // início do próximo mês

        if (monthAux >= 13) yearAux++;
        monthAux = monthAux % 13;
        if (monthAux == 0) monthAux = 1;
        continue;
      }
      dayAux += i;
      break;
    }
    return new Date(dayAux, monthAux, yearAux);
  }

  // segue quase a mesma lógica
  public Date minusDays(int daysInput) {
    int monthAux = month;
    int dayAux = day;
    int yearAux = year;
    for (int i = daysInput; i >= 0 ;) {
      if (dayAux - i <= 0) { 
        int difference = dayAux;
        monthAux--;
        i -= difference;
        dayAux = lengthOfMonth(monthAux, yearAux); // fim do próximo mês

        if (monthAux <= 0) {
          yearAux--;
          monthAux = 12;
        }          
        continue;
      }
      dayAux -= i;
      break;
    }
    return new Date(dayAux, monthAux, yearAux);
  }

  public Date tomorrow() {
    return plusDays(1);
  }

  public Date yesterday() {
    return minusDays(1);
  }
}
