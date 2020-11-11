package com.company.graphicalElements;

import java.awt.*;

import com.company.util.Case;


public class Element extends Case {
    public final Color color;

    public Element(int absc, int ord, Color color) {
        super(absc, ord);
        this.color = color;
    }
    
    public Element(Case c, Color color) {
        super(c.absc, c.ord);
        this.color = color;
    }
    
}
