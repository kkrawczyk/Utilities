/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.novait.utilities.gui.table;

import eu.novait.utilities.gui.panel.ImagePanel;
import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Krzysztof
 */
public class BufferedImageCellRenderer extends ImagePanel implements TableCellRenderer {

    private int rowHeight = 100;

    public BufferedImageCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof BufferedImage) {
            table.setRowHeight(row, this.rowHeight);
            BufferedImage bi = (BufferedImage) value;
            this.setImage(bi);
        }
        return this;
    }

    /**
     * @return the rowHeight
     */
    public int getRowHeight() {
        return rowHeight;
    }

    /**
     * @param rowHeight the rowHeight to set
     */
    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

}
