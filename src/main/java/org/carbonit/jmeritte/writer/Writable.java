package org.carbonit.jmeritte.writer;

import java.util.List;

public interface Writable {

    /**
     * Recupere une liste d'objets définissant l'objet à fournir au LCATWriter
     *
     * @return {List<Object>} Les objets constituant la définition
     */
    List<Object> recupererDefinitionWriter();
}
