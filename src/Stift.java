

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import static javax.swing.UIManager.*;

/**
 * Einfache Turtlegrafik für Java-Editor
 * <br>
 * Für Zeichnungen eine eigene Klasse erstellen und dann
 * private static Stift stift = Stift.getStift(800, 600);
 * als statisches Attribut anlegen, den eigentlichen Zeichencode mit
 * stift.methode() in eigenen statischen Methoden schreiben.
 * <br>
 * Singleton
 *
 * @author hecc79 Christian Hecker
 * @version 1.1
 */
@SuppressWarnings("unused")
public class Stift {

    private static Stift stiftInstance;
    private static Blatt blatt;

    public static Stift getStift(int breite, int hoehe) {
        if (Stift.stiftInstance == null) {
            Stift.blatt = new Blatt("Zeichenblatt", breite, hoehe);
            stiftInstance = new Stift(0, 0, blatt);
        }
        return stiftInstance;
    }

    public static class Blatt extends JFrame implements WindowListener {

        private static final long serialVersionUID = 1L;
        private final JPanel panel;
        private final Image image;

        public Blatt(String name, int width, int height) {
            super(name);
            try {
                setLookAndFeel(getSystemLookAndFeelClassName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            setPreferredSize(new Dimension(width + 50, height + 50));
            panel = new JPanel() {

                private static final long serialVersionUID = 1L;

                @Override
                public void paint(Graphics graphics) {
                    super.paint(graphics);
                    graphics.drawImage(image, 0, 0, Color.white, null);
                }
            };
            panel.setPreferredSize(new Dimension(width, height));
            JScrollPane sp = new JScrollPane(panel);
            add(sp);
            pack();
            image = panel.createImage(width, height);
            addWindowListener(this);
            setVisible(true);
        }

        public Image getImage() {
            return image;
        }

        public JPanel getPanel() {
            return panel;
        }


        @SuppressWarnings("BusyWait")
        public void warten() {
            while (isVisible()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    setVisible(false);
                }
            }
        }

        public void windowActivated(WindowEvent e) {
            paint(getGraphics());
        }

        public void windowClosed(WindowEvent e) {
        }

        public void windowClosing(WindowEvent e) {
            setVisible(false);
            dispose();
        }

        public void windowDeactivated(WindowEvent e) {
        }

        public void windowDeiconified(WindowEvent e) {
            paint(getGraphics());
        }

        public void windowIconified(WindowEvent e) {
        }

        public void windowOpened(WindowEvent e) {
            paint(getGraphics());
        }

    }

    private double richtung; //Winkel im Bogenmaß rechtsdrehend.
    private double x;
    private double y;
    private boolean stiftOben;
    private Color farbe;

    private final Graphics2D graphics2D;
    private final Container container;

    public void setzeFarbe(Color c) {
        farbe = c;
    }

    /**
     * Privater Konstruktor. Bitte Instanz über Singleton Methode Stift.getStift() erzeugen.
     * @param x Maximale Pixelzahl Breite
     * @param y Maximale Pixelzahl Höhe
     * @param blatt das Zeichenblatt
     */
    private Stift(double x, double y, Blatt blatt) {
        this.x = x;
        this.y = y;
        richtung = 0.0;
        stiftOben = false;

        this.container = blatt.getPanel();
        this.graphics2D = (Graphics2D) blatt.getImage().getGraphics();

        setzeFarbe(Color.BLACK);

    }


    /**
     * Hebt den Stift. Bewegungen hinterlassen ab jetzt keine Zeichenspur.
     */
    public void heben() {

        stiftOben = true;
    }

    /**
     * Senkt den Stift. Bewegungen hinterlassen ab jetzt eine Zeichenspur.
     */
    public void senken() {
        stiftOben = false;
    }

    /**
     * Dreht die Orientierung des Stifts linksherum im Gradmaß
     * @param winkel Drehwinkel im Gradmaß, linksdrehend
     */
    public void drehe(double winkel) {
        this.richtung = (richtung + Math.toRadians(-winkel));
    }


    private int runden(double d) {
        return (int) (d + 0.5);
    }

    /**
     * Bewegt den Stift strecke vor. Wenn der Stift gesenkt ist, wird eine Strecke gezeichnet.
     * @param strecke Streckenlänge
     */
    public void vor(double strecke) {

        int oldx = getXi();
        int oldy = getYi();
        x += strecke * Math.cos(richtung);
        y += strecke * Math.sin(richtung);
        zeichneLinie(oldx, oldy, getXi(), getYi(), farbe);
    }

    @SuppressWarnings("Convert2Lambda")
    private void zeichneLinie(final int x1, final int y1, final int x2, final int y2, final Color c) {
        if (!stiftOben) SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                graphics2D.setColor(c);
                graphics2D.drawLine(x1, y1, x2, y2);
                container.repaint();
            }
        });
    }

    /**
     * Gehe zu den Koordinaten (x,y) und zeichne entlang des Weges eine Strecke, wenn der Stift gesenkt ist.
     * @param x x-Koordinate
     * @param y y-Koordinate
     */
    @SuppressWarnings("unused")
    public void geheZu(double x, double y) {
        int oldx = getXi();
        int oldy = getYi();
        this.x = x;
        this.y = y;

        zeichneLinie(oldx, oldy, getXi(), getYi(), farbe);

    }

    /**
     * Bewegt den Stift zu den Koordinaten x,y ohne eine Strecke zu zeichnen.
     * @param x x-Koordinate
     * @param y y-Koordinate
     */
    public void geheZuOhne(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private int getXi() {
        return runden(getX());
    }

    private int getYi() {
        return runden(getY());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     *
     * @return Gibt die Drehrichtung (linksdrehend) im Gradmaß, 0-rechts, 90-oben, 180-links, 270-unten
     */
    public double getRichtung() {
        return Math.toDegrees(-richtung);
    }

    /**
     * Setzt die Drehrichtung
     * @param d Drehrichtung (linksdrehend) im Gradmaß, 0-rechts, 90-oben, 180-links, 270-unten
     */
    public void setRichtung(double d) {
        richtung = Math.toRadians(-d);
    }

    public String toString() {
        return getX() + " / " + getY() + " / " + getRichtung();
    }

}