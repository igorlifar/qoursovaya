package ru.msu.cs.svdtop.utils;

import org.apache.log4j.Logger;

import ru.yandex.bolts.collection.Option;
import ru.yandex.bolts.internal.Validate;

/**
 * @author sankear
 */
public class Progress {

    public static final long DEFAULT_INTERVAL_BETWEEN_LOG_MESSAGES = 100;

    private final Option<Long> maxTicks;
    private final String message;
    private long ticks;
    private final Logger logger;
    private final long startTime;
    private final long intervalBetweenTicks;

    public Progress(String message, Logger logger) {
        this(message, logger, Option.<Long>none());
    }

    public Progress(String message, Logger logger, long maxTicks) {
        this(message, logger, Option.some(maxTicks));
    }

    public Progress(String message, Logger logger, long maxTicks, long intervalBetweenTicks) {
        this(message, logger, Option.some(maxTicks), intervalBetweenTicks);
    }

    public Progress(String message, Logger logger, Option<Long> maxTicks) {
        this(message, logger, maxTicks, DEFAULT_INTERVAL_BETWEEN_LOG_MESSAGES);
    }

    public Progress(String message, Logger logger, Option<Long> maxTicks, long intervalBetweenTicks) {
        Validate.isTrue(intervalBetweenTicks > 0, "Must be greater than 0");
        this.maxTicks = maxTicks;
        this.message = message;
        this.logger = logger;
        this.ticks = 0;
        this.startTime = System.currentTimeMillis();
        this.intervalBetweenTicks = intervalBetweenTicks;
    }

    public void tick() {
        ++ticks;
        if (ticks % intervalBetweenTicks == 0) {
            if (maxTicks.isDefined()) {
                logger.info(String.format(
                        "Process [%s] started %.2f minutes ago, do %d of %d ticks",
                        message,
                        (System.currentTimeMillis() - startTime) / (1000.0 * 60),
                        ticks,
                        maxTicks.get()));
            } else {
                logger.info(String.format(
                        "Process [%s] started %.2f minutes ago",
                        message,
                        (System.currentTimeMillis() - startTime) / (1000.0 * 60)));
            }
        }
    }

    public void summary() {
        logger.info(String.format(
                "Process [%s] finished, started %.2f minutes ago",
                message,
                (System.currentTimeMillis() - startTime) / (1000.0 * 60)));
    }

}
