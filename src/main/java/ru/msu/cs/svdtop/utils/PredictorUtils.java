package ru.msu.cs.svdtop.utils;

import ru.msu.cs.svdtop.domain.Predictor;
import ru.msu.cs.svdtop.domain.Snapshot;
import ru.msu.cs.svdtop.domain.impl.predictors.NaivePredictor;

import ru.yandex.bolts.collection.MapF;
import ru.yandex.bolts.collection.Tuple2;
import ru.yandex.bolts.collection.Tuple2List;

/**
 * @author sankear
 */
public class PredictorUtils {

    public static final MapF<String, Predictor<? extends Snapshot>> predictors = Tuple2List.tuple2List(
        Tuple2.<String, Predictor<? extends Snapshot>>tuple("naive", new NaivePredictor())
    ).toMap();

    public static Predictor<? extends Snapshot> getByName(String name) {
        return predictors.getOrThrow(name);
    }

}
