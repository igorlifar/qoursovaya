package ru.msu.cs.svdtop;

import java.io.File;
import java.io.IOException;

import com.google.protobuf.CodedOutputStream;
import org.apache.log4j.Logger;
import ru.msu.cs.svdtop.domain.ItemProfile;
import ru.msu.cs.svdtop.domain.Profile;
import ru.msu.cs.svdtop.utils.Progress;
import ru.msu.cs.svdtop.utils.generators.ItemProfileListGenerator;
import ru.msu.cs.svdtop.utils.generators.ProfileListGenerator;
import ru.msu.cs.svdtop.utils.protobuf.ProtobufItemProfileListSerializer;
import ru.msu.cs.svdtop.utils.protobuf.ProtobufProfileListSerializer;
import ru.msu.cs.svdtop.utils.protobuf.ProtobufUtils;

import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.internal.Validate;

/**
 * @author sankear
 */
public class GenerateItemProfilesAndQueries {

    private static final Logger logger = Logger.getLogger(GenerateItemProfilesAndQueries.class);

    public static final int ITEM_PROFILES = 1_000_000;
    public static final int QUERIES = 1000;

    public static void main(String[] args) throws IOException {
        Validate.isTrue(args.length >= 2, "");

        Progress progress = new Progress("Generate items and queries", logger, 2, 1);

        ListF<ItemProfile> profiles = new ItemProfileListGenerator().generate(ITEM_PROFILES);
        CodedOutputStream stream = ProtobufUtils.getOutputStream(new File(args[0]));
        ProtobufItemProfileListSerializer.S.serialize(profiles, stream);
        stream.flush();

        progress.tick();

        ListF<Profile> queries = new ProfileListGenerator().generate(QUERIES);
        stream = ProtobufUtils.getOutputStream(new File(args[1]));
        ProtobufProfileListSerializer.S.serialize(queries, stream);
        stream.flush();

        progress.summary();
    }

}
