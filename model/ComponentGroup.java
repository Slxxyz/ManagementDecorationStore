package model;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class ComponentGroup {
    private JComponent[] components;
    private int groupAnchor;

    public ComponentGroup(int groupAnchor, JComponent... components) {
        this.groupAnchor = groupAnchor;
        this.components = components;
    }

    public int getAnchor() {
        return this.groupAnchor;
    }

    public JComponent getNext(int index) {
        if (0 <= index && index < this.components.length) {
            return this.components[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getSize() {
        return this.components.length;
    }

    public JComponent[] getComponents() {
        return components;
    }
}
