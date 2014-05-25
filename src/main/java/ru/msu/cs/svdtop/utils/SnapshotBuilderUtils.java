package ru.msu.cs.svdtop.utils;

import ru.msu.cs.svdtop.domain.Snapshot;
import ru.msu.cs.svdtop.domain.SnapshotBuilder;
import ru.msu.cs.svdtop.domain.impl.snapshots.NaiveSnapshotBuilder;

import ru.yandex.bolts.collection.MapF;
import ru.yandex.bolts.collection.Tuple2;
import ru.yandex.bolts.collection.Tuple2List;

/**
 * @author sankear
 */
public class SnapshotBuilderUtils {

    public static final MapF<String, SnapshotBuilder<? extends Snapshot>> builders =
            Tuple2List.tuple2List(
                Tuple2.<String, SnapshotBuilder<? extends Snapshot>>tuple("naive", new NaiveSnapshotBuilder())
            ).toMap();

    public static final SnapshotBuilder<? extends Snapshot> getByName(String name) {
        return builders.getOrThrow(name);
    }

}
