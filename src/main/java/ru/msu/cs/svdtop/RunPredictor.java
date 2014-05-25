package ru.msu.cs.svdtop;

import java.io.File;
import java.io.IOException;

import com.google.protobuf.CodedInputStream;
import org.apache.log4j.Logger;
import ru.msu.cs.svdtop.domain.Predictor;
import ru.msu.cs.svdtop.domain.Profile;
import ru.msu.cs.svdtop.domain.Snapshot;
import ru.msu.cs.svdtop.domain.SnapshotBuilder;
import ru.msu.cs.svdtop.utils.PredictorUtils;
import ru.msu.cs.svdtop.utils.Progress;
import ru.msu.cs.svdtop.utils.SnapshotBuilderUtils;
import ru.msu.cs.svdtop.utils.Statistic;
import ru.msu.cs.svdtop.utils.protobuf.ProtobufProfileListSerializer;
import ru.msu.cs.svdtop.utils.protobuf.ProtobufUtils;

import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.internal.Validate;

/**
 * @author sankear
 */
public class RunPredictor {

    private static final Logger logger = Logger.getLogger(RunPredictor.class);

    private static final int TOP_COUNT = 1000;

    public static void main(String[] args) throws IOException {
        Validate.isTrue(args.length >= 4, "");

        logger.info("Load queries from file");
        CodedInputStream stream = ProtobufUtils.getInputStream(new File(args[0]));
        ListF<Profile> queries = ProtobufProfileListSerializer.S.deserialize(stream);
        logger.info("Total " + queries.size() + " queries found");

        SnapshotBuilder<? extends Snapshot> builder = SnapshotBuilderUtils.getByName(args[1]);
        logger.info("Load snapshot from files");
        Snapshot snapshot = builder.fromFiles(new File(args[2]));

        Progress progress = new Progress("Run predictor", logger, queries.size(), 100);

        Statistic statistic = new Statistic();

        Predictor predictor = PredictorUtils.getByName(args[3]);

        for (Profile query : queries) {
            long startTime = System.currentTimeMillis();
            ListF<Long> answer = predictor.getTop(snapshot, query, TOP_COUNT);
            long finishTime = System.currentTimeMillis();
            statistic.add(finishTime - startTime);
            progress.tick();
        }

        progress.summary();

        statistic.summary();

    }

}
