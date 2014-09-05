package eu.novait.utilities.gui.panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Krzysztof Krawczyk
 */
public class ImagePanel extends javax.swing.JPanel {

    protected BufferedImage image;

    public ImagePanel() {
        super();
    }

    public ImagePanel(BufferedImage bi) {
        super();
        this.image = bi;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.image != null) {
            Graphics2D g2d = (Graphics2D) g;
            int w = this.getWidth();
            int h = this.getHeight();
            int iw = this.image.getWidth();
            int ih = this.image.getHeight();
            double iratio = (double) iw / (double) ih;

            if (iw > w) {
                int newIw = w;
                int newIh = (int) Math.floor(newIw / iratio);
                int hmargin = 0;
                if (h > newIh) {
                    int diff = h - newIh;
                    hmargin = diff / 2;
                }
                g2d.drawImage(image, 0, hmargin, newIw, newIh, null);
            } else {
                int diff = w - iw;
                int margin = diff / 2;
                int hmargin = 0;
                if (h > ih) {
                    diff = h - ih;
                    hmargin = diff / 2;
                }
                g2d.drawImage(image, margin, hmargin, iw, ih, null);
            }
        }
    }

    /**
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
