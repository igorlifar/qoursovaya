package ru.msu.cs.svdtop.domain;

import java.io.File;

import ru.yandex.bolts.collection.ListF;

/**
 * @author lifar
 */
public interface SnapshotBuilder<TSnapshot extends Snapshot> {
    TSnapshot fromProfiles(ListF<ItemProfile> itemProfiles);

    TSnapshot fromFiles(File dir);
}
