package org.carbonit.jmeritte.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LCATLogFormatter extends Formatter {

    private static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String PATTERN_FORMATAGE_MESSAGE = "%1$s %2$s %3$s --- %4$s %n";

    public static final String ANSI_GREEN = "\033[1;32m";
    public static final String ANSI_YELLOW = "\033[1;33m";
    public static final String ANSI_RED = "\033[1;31m";

    @Override
    public String format(LogRecord logRecord) {
        String date = new SimpleDateFormat(FORMAT_DATE).format(new Date(logRecord.getMillis()));
        var color = switch (logRecord.getLevel().getName()) {
            case "WARNING" -> ANSI_YELLOW;
            case "SEVERE" -> ANSI_RED;
            default -> ANSI_GREEN;
        };
        return String.format(PATTERN_FORMATAGE_MESSAGE, color, date, logRecord.getLevel().getName(), logRecord.getMessage());
    }
}
