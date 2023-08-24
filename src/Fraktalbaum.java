

import java.awt.Color;


public class Fraktalbaum {
  private static Stift stift = Stift.getStift(800, 600);

  public static void zeichneBaum0(double laenge) {
    stift.vor(laenge);
    stift.vor(-laenge);
  }

  public static void zeichneBaum1(double laenge) {
    stift.vor(laenge);
    stift.drehe(45);
    zeichneBaum0(laenge / 2);
    stift.drehe(-90);
    zeichneBaum0(laenge / 2);
    stift.drehe(45);
    stift.vor(-laenge);
  }

  public static void zeichneBaum2(double laenge) {
    stift.vor(laenge);
    stift.drehe(45);
    zeichneBaum1(laenge / 2);
    stift.drehe(-90);
    zeichneBaum1(laenge / 2);
    stift.drehe(45);
    stift.vor(-laenge);
  }

  public static void zeichneBaum3(double laenge) {
    stift.vor(laenge);
    stift.drehe(45);
    zeichneBaum2(laenge / 2);
    stift.drehe(-90);
    zeichneBaum2(laenge / 2);
    stift.drehe(45);
    stift.vor(-laenge);
  }

  public static void zeichneBaumX(int n, double laenge) {
    stift.vor(laenge);
    stift.drehe(45);
    zeichneBaum(n - 1, laenge / 2);
    stift.drehe(-90);
    zeichneBaum(n - 1, laenge / 2);
    stift.drehe(45);
    stift.vor(-laenge);
  }

  public static void zeichneBaum(int n, double laenge) {
    if (n == 0) { // Abbruchbedingung
      stift.vor(laenge); // Grundfigur
      stift.vor(-laenge);
    } else {
      stift.vor(laenge);
      stift.drehe(45);
      zeichneBaum(n - 1, laenge / 2); // Rekursionsaufruf
      stift.drehe(-90);
      zeichneBaum(n - 1, laenge / 2); // Rekursionsaufruf
      stift.drehe(45);
      stift.vor(-laenge);
    } // end of if-else
  }

  public static void main(String[] args) {
    stift.geheZuOhne(100, 500);
    stift.setRichtung(90);
    zeichneBaum0(100);

    stift.geheZuOhne(250, 500);
    stift.setRichtung(90);
    zeichneBaum1(100);

    stift.geheZuOhne(400, 500);
    stift.setRichtung(90);
    zeichneBaum2(100);

    stift.geheZuOhne(550, 500);
    stift.setRichtung(90);
    zeichneBaum3(100);
  }
}
