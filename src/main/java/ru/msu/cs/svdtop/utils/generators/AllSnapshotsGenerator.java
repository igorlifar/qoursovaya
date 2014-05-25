package ru.msu.cs.svdtop.utils.generators;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import ru.msu.cs.svdtop.domain.ItemProfile;
import ru.msu.cs.svdtop.domain.Snapshot;
import ru.msu.cs.svdtop.domain.SnapshotBuilder;
import ru.msu.cs.svdtop.domain.impl.snapshots.NaiveSnapshotBuilder;
import ru.msu.cs.svdtop.utils.Progress;

import ru.yandex.bolts.collection.Cf;
import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.internal.Validate;

/**
 * @author sankear
 */
public class AllSnapshotsGenerator {

    private static final Logger logger = Logger.getLogger(AllSnapshotsGenerator.class);

    public static final ListF<SnapshotBuilder<? extends Snapshot>> snapshotBuilders = Cf.list(
            new NaiveSnapshotBuilder()
    );

    public static void main(String[] args) throws IOException {
        Validate.isTrue(args.length >= 2, "");
        File dir = new File(args[1]);
        Validate.isTrue(dir.isDirectory(), "Must be directory");
        ListF<ItemProfile> profiles = new ItemProfileListGenerator().generate(100_000_000);
        logger.info("Start dump snapshots");
        Progress progress = new Progress("Dump snapshots", logger, snapshotBuilders.size(), 1);
        for (SnapshotBuilder<? extends Snapshot> builder : snapshotBuilders) {
            logger.info("Use builder " + builder.getClass().getName());
            Snapshot snapshot = builder.fromProfiles(profiles);
            logger.info("Start dumping");
            snapshot.dumpToFiles(new File(dir.getAbsolutePath() + snapshot.getDefaultDirectory()));
            progress.tick();
        }
        progress.summary();
    }

}
