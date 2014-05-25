package ru.msu.cs.svdtop.domain;

import ru.yandex.bolts.collection.ListF;

/**
 * @author lifar
 */
public interface TopPredictor<TSnapshot extends Snapshot> {
    ListF<Long> getTop(TSnapshot snapshot, Profile userProfile, long count);
}
