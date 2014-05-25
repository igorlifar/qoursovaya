package ru.msu.cs.svdtop.utils.protobuf;

import java.io.IOException;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;

import ru.yandex.bolts.collection.Cf;
import ru.yandex.bolts.collection.ListF;

/**
 * @author sankear
 */
public class AbstractProtobufListSerializer<T> implements ProtobufSerializer<ListF<T>> {

    private final ProtobufSerializer<T> itemSerializer;

    public AbstractProtobufListSerializer(ProtobufSerializer<T> itemSerializer) {
        this.itemSerializer = itemSerializer;
    }

    public ListF<T> deserialize(CodedInputStream stream) throws IOException {
        int total = stream.readInt32();
        ListF<T> result = Cf.arrayList(total);
        for (int i = 0; i < total; ++i) {
            result.add(itemSerializer.deserialize(stream));
        }
        return result;
    }

    public void serialize(ListF<T> items, CodedOutputStream stream) throws IOException {
        stream.writeInt32NoTag(items.size());
        for (T item : items) {
            itemSerializer.serialize(item, stream);
        }
    }

}
