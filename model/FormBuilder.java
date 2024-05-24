package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FormBuilder {
    private JPanel panel;
    private int gridX;
    private int gridY;
    private int inBetweenLineSpace = 2;
    private int labelComponentSpace = 5;
    private Insets labelInsets;

    private int currentFill;

    private int labelAnchor = GridBagConstraints.EAST;
    private int textFieldAnchor = GridBagConstraints.EAST;
    private int buttonAnchor = GridBagConstraints.EAST;

    public FormBuilder(JPanel panel) {
        this.panel = panel;
    }
    public FormBuilder() {
        this(new JPanel(new GridBagLayout()));
    }
    private FormBuilder addOnSameLineStep(int baseX, int anchor, JComponent... components) {
        for (int componentIndex = 0; componentIndex < components.length; componentIndex++) {
            this.panel.add(components[componentIndex], this.getConstraints(anchor, baseX + componentIndex));
        }
        return this;
    }
    private FormBuilder addOnSameLineGroupStep(int baseX, ComponentGroup... componentGroups) {
        int base = baseX;
        for (ComponentGroup group : componentGroups) {
            this.addOnSameLineStep(base, group.getAnchor(), group.getComponents());
            base += group.getSize();
        }
        this.nextY();
        return this;
    }
    public FormBuilder addMultipleRow(
            int baseAnchor,
            JComponent baseComponent,
            int componentsAnchor,
            JComponent... rowComponent) {
        int baseX = this.gridX;
        this.panel.add(baseComponent, this.getConstraints(baseAnchor, baseX));
        for (int rowIndex = 0; rowIndex < rowComponent.length; rowIndex++) {
            this.panel.add(rowComponent[rowIndex], this.getConstraints(componentsAnchor, baseX + 1));
            this.nextY();
        }
        return this;
    }

    public FormBuilder addOnSameLine(int anchor, JComponent... components) {
        this.addOnSameLineStep(this.gridX, anchor, components);
        this.nextY();
        return this;
    }
    public FormBuilder addOnSameLine(ComponentGroup... componentGroups) {
        return this.addOnSameLineGroupStep(this.gridX, componentGroups);
    }
    public FormBuilder addOnSameLine(int extraX, ComponentGroup... componentGroups) {
        return this.addOnSameLineGroupStep(this.gridX + extraX, componentGroups);
    }
    public FormBuilder addLabelAnd(JLabel label, JComponent otherComponent) {
        return this.addOnSameLine(
                new ComponentGroup(this.labelAnchor, label),
                new ComponentGroup(this.textFieldAnchor, otherComponent)
        );
    }
    public FormBuilder addButton(JButton button) {
        return this.addOnSameLine(
                gridX + 1,
                new ComponentGroup(this.buttonAnchor, button)
        );
    }
    public FormBuilder setInBetweenLineSpace(int inBetweenLineSpace) {
        this.inBetweenLineSpace = inBetweenLineSpace;
        this.labelInsets.bottom = inBetweenLineSpace;
        return this;
    }
    public FormBuilder setLabelComponentSpace(int labelComponentSpace) {
        this.labelComponentSpace = labelComponentSpace;
        return this;
    }

    public JPanel build() {
        return this.panel;
    }
    private void nextY() {
        this.gridY++;
    }
    private GridBagConstraints getConstraints(int anchor, int topSpace, int newGridX, int fill) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = newGridX;
        constraints.gridy = gridY;
        constraints.insets = new Insets(topSpace, 0, this.inBetweenLineSpace, this.labelComponentSpace);;
        constraints.anchor = anchor;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        return constraints;
    }

    private GridBagConstraints getConstraints(int anchor, int newGridX) {
        return this.getConstraints(anchor, 0, newGridX, GridBagConstraints.NONE);
    }
    private GridBagConstraints getConstraints(int anchor) {
        return this.getConstraints(anchor, 0, gridX, GridBagConstraints.NONE);
    }
}