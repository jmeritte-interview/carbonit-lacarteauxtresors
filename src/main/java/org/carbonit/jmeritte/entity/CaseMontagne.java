package org.carbonit.jmeritte.entity;

import org.carbonit.jmeritte.writer.Writable;

import java.util.List;

public class CaseMontagne extends Case implements Writable {

    public CaseMontagne(int x, int y) {
        super(x, y);
        this.visitable = false;
    }

    @Override
    public List<Object> recupererDefinitionWriter() {
        return List.of("M", x, y);
    }
}
