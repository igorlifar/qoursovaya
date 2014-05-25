package ru.msu.cs.svdtop.domain.impl.snapshots;

import java.io.File;
import java.io.IOException;

import com.google.protobuf.CodedOutputStream;
import ru.msu.cs.svdtop.domain.ItemProfile;
import ru.msu.cs.svdtop.domain.Snapshot;
import ru.msu.cs.svdtop.utils.protobuf.ProtobufItemProfileListSerializer;
import ru.msu.cs.svdtop.utils.protobuf.ProtobufUtils;

import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.internal.Validate;

/**
 * @author sankear
 */
public class NaiveSnapshot implements Snapshot {

    private final ListF<ItemProfile> profiles;

    public NaiveSnapshot(ListF<ItemProfile> profiles) {
        this.profiles = profiles;
    }

    public void dumpToFiles(File dir) throws IOException {
        Validate.isTrue(dir.isDirectory(), "Must be directory");
        CodedOutputStream stream = ProtobufUtils.getOutputStream(new File(dir.getAbsolutePath() + "profiles"));
        ProtobufItemProfileListSerializer.S.serialize(profiles, stream);
    }

}
