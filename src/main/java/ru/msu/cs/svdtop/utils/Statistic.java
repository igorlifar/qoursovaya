package ru.msu.cs.svdtop.utils;

import org.apache.log4j.Logger;

import ru.yandex.bolts.collection.Cf;
import ru.yandex.bolts.collection.ListF;

/**
 * @author sankear
 */
public class Statistic {

    private static final Logger logger = Logger.getLogger(Statistic.class);

    private final ListF<Long> values;

    public Statistic() {
        this.values = Cf.arrayList();
    }

    public void add(long value) {
        values.add(value);
    }

    public void summary() {
        double sum = 0;
        for (Long value : values) {
            sum += value;
        }
        logger.info(String.format(
                "Average value is %.6f",
                sum / values.size()));
    }

}
