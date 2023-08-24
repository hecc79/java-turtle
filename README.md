# java-turtle
Eine reduzierte, einfache Java Turtle mit deutschem Interface

Um mit dem Java-Editor zeichnen zu können, muss die folgende Datei Stidt.java im Arbeitsverzeichnis liegen und übersetzt werden.

``` java title="Beispiel für das Zeichnen mit der Klasse Stift" linenums="1"
import java.awt.Color;

public class Beispiel {
  
  private static Stift stift = Stift.getStift(800, 600); 
  
  public static void main(String[] args) {
    
    //Bewege den Stift ohne zu zeichnen auf (100|100)
    //Zeichenrichtung rechts
    stift.heben();
    stift.vor(100);
    stift.drehe(-90);
    stift.vor(100);
    stift.drehe(90);
    stift.senken();
    
    //Zeichne ein Dreieck
    stift.setzeFarbe(Color.blue);
    stift.vor(100);
    stift.drehe(-120);
    stift.setzeFarbe(Color.red);
    stift.vor(100);
    stift.drehe(-120);
    stift.setzeFarbe(Color.green);
    stift.vor(100);
    stift.drehe(-120);
  }
}
```

Die import Anweisung (Zeile 1) ist nötig, um die Farben (s. Zeilen 9, 12, 15) zu spezifizieren.

Das Anlegen der Variable stift (Zeile 5) außerhalb der main Methode ist nötig, um den Stift auch in anderen Methoden nutzen zu können. Die beiden Parameter bedeuten, dass das Zeichenblatt 800 Punkte breit und 600 Punkte hoch sein soll.

Der Startpunkt (0|0) liegt in der linken oberen Ecke. Die x Werte erhöhen sich nach links, y-Werte erhöhen sich nach unten. Startblickrichtung ist nach rechts (0).

Zum Zeichnen können die folgenden Methoden des Stiftes genutzt werden:

``` java
  public void vor(double laenge)
         // Der Stift zeichnet laenge Einheiten in aktueller Richtung nach vorne.

  public void drehe(double grad)
         // Dreht im mathematisch positiven Sinn um den Winkel grad.

  public void setzeFarbe(Color c)
         /* Vordefiniert sind
            Color.black     Color.blue    Color.cyan
            Color.darkGray  Color.gray    Color.green
            Color.lightGray Color.magenta Color.orange
            Color.pink      Color.red     Color.white
            Color.yellow
         Andere Farben kann man z.B. mit
         stiftfarbe (new Color(int rot, int gruen, int blau));
         erzeugen. Die Werte fuer rot, gruen und blau
         müssen im Bereich zwischen 0 und 255 liegen.
         */

  public void heben()
     /* Wenn der Stift oben ist, kann er bewegt werden ohne zu zeichnen */

  public void senken()
    /* Wird der Stift bewegt, hinterlässt er auch Zeichenspuren */

  public void geheZuOhne(double x, double y) {
   /* Gehe zum Punkt (x | y) ohne zu zeichnen */
```
