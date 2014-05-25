package ru.msu.cs.svdtop.domain.impl.snapshots;

import java.io.File;
import java.io.IOException;

import com.google.protobuf.CodedInputStream;
import org.apache.log4j.Logger;
import ru.msu.cs.svdtop.domain.ItemProfile;
import ru.msu.cs.svdtop.domain.SnapshotBuilder;
import ru.msu.cs.svdtop.utils.protobuf.ProtobufUtils;
import ru.msu.cs.svdtop.utils.protobuf.ProtobufItemProfileListSerializer;

import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.internal.Validate;

/**
 * @author sankear
 */
public class NaiveSnapshotBuilder implements SnapshotBuilder<NaiveSnapshot> {

    private static final Logger logger = Logger.getLogger(NaiveSnapshotBuilder.class);

    public NaiveSnapshot fromProfiles(ListF<ItemProfile> profiles) {
        return new NaiveSnapshot(profiles);
    }

    public NaiveSnapshot fromFiles(File dir) throws IOException {
        Validate.isTrue(dir.isDirectory(), "Must be directory");
        CodedInputStream stream = ProtobufUtils.getInputStream(new File(dir.getAbsolutePath() + "/profiles"));
        ListF<ItemProfile> profiles = ProtobufItemProfileListSerializer.S.deserialize(stream);
        logger.info("Total " + profiles.size() + " item profiles found");
        return new NaiveSnapshot(profiles);
    }

}
