package ru.msu.cs.svdtop.utils.protobuf;

import java.io.IOException;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;

/**
 * @author sankear
 */
public interface ProtobufSerializer<T> {

    T deserialize(CodedInputStream stream) throws IOException;

    void serialize(T item, CodedOutputStream stream) throws IOException;

}
