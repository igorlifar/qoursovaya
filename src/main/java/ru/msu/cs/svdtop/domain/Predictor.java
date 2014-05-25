package ru.msu.cs.svdtop.domain;

import ru.yandex.bolts.collection.ListF;

/**
 * @author lifar
 */
public interface Predictor<TSnapshot extends Snapshot> {
    ListF<Long> getTop(TSnapshot snapshot, Profile userProfile, long count);
}
