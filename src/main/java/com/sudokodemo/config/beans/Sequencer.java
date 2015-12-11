package com.sudokodemo.config.beans;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Component;

/**
 *
 * @author SARAT
 */
@Component
public class Sequencer {

    private final AtomicInteger sequenceNumber
            = new AtomicInteger(0);

    public int next() {
        return sequenceNumber.getAndIncrement();
    }

}
