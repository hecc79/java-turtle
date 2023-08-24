

import java.awt.Color;
public class SierpinksiDreieck {
    private static final Stift stift = Stift.getStift(1600, 1000);

    private static void dreieck(int stufe, double laenge) {
        if (stufe == 0) {
            stift.vor(laenge);
            stift.drehe(120);
            stift.vor(laenge);
            stift.drehe(120);
            stift.vor(laenge);
            stift.drehe(120);
        } else {
            dreieck(stufe - 1, laenge / 2);
            stift.vor(laenge / 2);
            dreieck(stufe - 1, laenge / 2);
            stift.vor(-laenge / 2);
            stift.drehe(60);
            stift.vor(laenge / 2);
            stift.drehe(-60);
            dreieck(stufe - 1, laenge / 2);
            stift.drehe(60);
            stift.vor(-laenge / 2);
            stift.drehe(-60);
        }
    }

    public static void main(String[] args) {
        stift.geheZuOhne(100, 250);
        dreieck(0,256);
        stift.geheZuOhne(100, 500);
        dreieck(1,256);
        stift.geheZuOhne(400, 250);
        dreieck(2,256);
        stift.geheZuOhne(400, 500);
        dreieck(3,256);
    }

}
