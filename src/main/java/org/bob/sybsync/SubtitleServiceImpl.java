package org.bob.sybsync;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author Babak Eghbali (Bob)
 * @since 2019/05/07
 */
public class SubtitleServiceImpl extends AbstractSubtitleService {

    DateTimeFormatter dateTimeFormatter;

    public SubtitleServiceImpl(){
        dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
    }

    @Override
    protected String process(String line, int milliseconds) {

        if (milliseconds == 0){
            return line;
        }

        if (!isTime(line)){
            return line;
        }

        String[] sections = line.split(" --> ");

        LocalTime startTime = LocalTime.parse(sections[0], dateTimeFormatter);
        LocalTime endTime = LocalTime.parse(sections[1], dateTimeFormatter);

        if (milliseconds < 0){
            startTime = startTime.minus(milliseconds, ChronoUnit.MILLIS);
            endTime = endTime.minus(milliseconds, ChronoUnit.MILLIS);
        }else{
            startTime = startTime.plus(milliseconds, ChronoUnit.MILLIS);
            endTime = endTime.plus(milliseconds, ChronoUnit.MILLIS);
        }

        line = dateTimeFormatter.format(startTime) + " -- >" + dateTimeFormatter.format(endTime);
        return line;
    }

    private boolean isTime(String line){
        return line.contains(" --> ");
    }


}

