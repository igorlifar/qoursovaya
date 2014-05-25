package ru.msu.cs.svdtop;

import java.io.File;
import java.io.IOException;

import com.google.protobuf.CodedInputStream;
import org.apache.log4j.Logger;
import ru.msu.cs.svdtop.utils.Statistic;
import ru.msu.cs.svdtop.utils.protobuf.ProtobufAnswersSerializer;
import ru.msu.cs.svdtop.utils.protobuf.ProtobufUtils;

import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.collection.SetF;
import ru.yandex.bolts.internal.Validate;

/**
 * @author sankear
 */
public class CompareTwoPredictors {

    private static final Logger logger = Logger.getLogger(CompareTwoPredictors.class);

    public static void main(String[] args) throws IOException {
        Validate.isTrue(args.length >= 2, "");

        logger.info("Deserialize answers of first predictor");
        CodedInputStream stream = ProtobufUtils.getInputStream(new File(args[0]));
        ListF<ListF<Long>> firstAnswers = ProtobufAnswersSerializer.S.deserialize(stream);

        logger.info("Deserialize answers of second predictor");
        stream = ProtobufUtils.getInputStream(new File(args[1]));
        ListF<ListF<Long>> secondAnswers = ProtobufAnswersSerializer.S.deserialize(stream);

        Statistic statistic = new Statistic();

        Validate.isTrue(firstAnswers.size() == secondAnswers.size(), "Size of answers must be equals");

        int size = -1;
        for (int i = 0; i < firstAnswers.size(); ++i) {
            if (size == -1) {
                size = firstAnswers.get(i).size();
            }
            Validate.isTrue(firstAnswers.get(i).size() == size, "");
            Validate.isTrue(secondAnswers.get(i).size() == size, "");
            SetF<Long> goodAnswers = firstAnswers.get(i).unique();
            int count = 0;
            for (Long id : secondAnswers.get(i)) {
                if (goodAnswers.containsTs(id)) {
                    ++count;
                }
            }
            statistic.add(count);
        }

        statistic.summary();
    }

}
