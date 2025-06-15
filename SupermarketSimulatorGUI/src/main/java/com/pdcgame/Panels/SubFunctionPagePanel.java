package com.pdcgame.Panels;

import javax.swing.*;
import java.awt.*;

public class SubFunctionPagePanel extends JPanel {

    public SubFunctionPagePanel(SubPagePanel subPage, FunctionPagePanel functionPage) {
        setLayout(new BorderLayout());
        add(subPage, BorderLayout.CENTER);
        add(functionPage, BorderLayout.EAST);
    }
}