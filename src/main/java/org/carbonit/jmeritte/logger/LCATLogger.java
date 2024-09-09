package org.carbonit.jmeritte.logger;

import org.carbonit.jmeritte.formatter.LCATLogFormatter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class LCATLogger extends Logger {

    public LCATLogger(String name) {
        super(name, null);
        var handler = new ConsoleHandler();
        handler.setFormatter(new LCATLogFormatter());
        this.addHandler(handler);
    }
}
