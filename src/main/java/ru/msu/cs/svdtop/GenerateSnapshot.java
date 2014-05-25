package ru.msu.cs.svdtop;

import java.io.File;
import java.io.IOException;

import com.google.protobuf.CodedInputStream;
import org.apache.log4j.Logger;
import ru.msu.cs.svdtop.domain.ItemProfile;
import ru.msu.cs.svdtop.domain.Snapshot;
import ru.msu.cs.svdtop.domain.SnapshotBuilder;
import ru.msu.cs.svdtop.utils.Progress;
import ru.msu.cs.svdtop.utils.SnapshotBuilderUtils;
import ru.msu.cs.svdtop.utils.protobuf.ProtobufItemProfileListSerializer;
import ru.msu.cs.svdtop.utils.protobuf.ProtobufUtils;

import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.internal.Validate;

/**
 * @author sankear
 */
public class GenerateSnapshot {

    private static final Logger logger = Logger.getLogger(GenerateSnapshot.class);

    public static void main(String[] args) throws IOException {
        Validate.isTrue(args.length >= 3, "");

        logger.info("Start generate snapshot");

        CodedInputStream stream = ProtobufUtils.getInputStream(new File(args[0]));
        ListF<ItemProfile> profiles = ProtobufItemProfileListSerializer.S.deserialize(stream);
        logger.info("Total " + profiles.size() + " item profiles found");

        SnapshotBuilder<? extends Snapshot> builder = SnapshotBuilderUtils.getByName(args[1]);
        logger.info("Start load snapshot from profiles");
        Progress progress = new Progress("Generate snapshot", logger, 2, 1);

        Snapshot snapshot = builder.fromProfiles(profiles);
        progress.tick();

        File dir = new File(args[2]);
        Validate.isTrue(dir.isDirectory(), "Must be directory");
        snapshot.dumpToFiles(new File(dir.getAbsolutePath() + "/" + snapshot.getDefaultDirectory()));

        progress.summary();
    }

}
