package ru.msu.cs.svdtop.utils.protobuf;

import java.io.IOException;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;

import ru.yandex.bolts.collection.Cf;
import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.internal.Validate;

/**
 * @author sankear
 */
public class ProtobufAnswersSerializer implements ProtobufSerializer<ListF<ListF<Long>>> {

    public static ProtobufAnswersSerializer S = new ProtobufAnswersSerializer();

    public ListF<ListF<Long>> deserialize(CodedInputStream stream) throws IOException {
        int total = stream.readInt32(), answerSize = stream.readInt32();
        ListF<ListF<Long>> answers = Cf.arrayList(total);
        for (int i = 0; i < total; ++i) {
            ListF<Long> answer = Cf.arrayList(answerSize);
            for (int j = 0; j < answerSize; ++j) {
                answer.add(stream.readInt64());
            }
            answers.add(answer);
        }
        return answers;
    }

    public void serialize(ListF<ListF<Long>> answers, CodedOutputStream stream) throws IOException {
        if (answers.size() == 0) {
            stream.writeInt32NoTag(0);
            stream.writeInt32NoTag(0);
            return;
        }
        stream.writeInt32NoTag(answers.size());
        stream.writeInt32NoTag(answers.get(0).size());
        for (ListF<Long> answer : answers) {
            Validate.isTrue(answer.size() == answers.get(0).size(), "");
            for (long id : answer) {
                stream.writeInt64NoTag(id);
            }
        }
    }

}
