package src;

import java.awt.Color;
public class Koch {
    private static final Stift stift = Stift.getStift(1600, 1000);

    private static void kochkurve(int stufe, double laenge) {
        if (stufe == 0) { // Rekursionsabbruch
            stift.vor(laenge);
        } else {
            kochkurve(stufe - 1, laenge / 3);
            stift.drehe(60);
            kochkurve(stufe - 1, laenge / 3);
            stift.drehe(-120);
            kochkurve(stufe - 1, laenge / 3);
            stift.drehe(60);
            kochkurve(stufe - 1, laenge / 3);
        }
    }

    public static void main(String[] args) {
        stift.geheZuOhne(100, 100);
        kochkurve(0, 200);
        stift.geheZuOhne(100, 200);
        kochkurve(1, 200);
        stift.geheZuOhne(100, 300);
        kochkurve(2, 200);
        stift.geheZuOhne(100, 400);
        kochkurve(3, 200);
        stift.geheZuOhne(100, 500);
        kochkurve(4, 200);
        stift.geheZuOhne(400, 200);

        kochkurve(4, 200);
        stift.drehe(-120);
        kochkurve(4, 200);
        stift.drehe(-120);
        kochkurve(4, 200);
    }
}
