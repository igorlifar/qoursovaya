package ru.msu.cs.svdtop.utils.protobuf;

import java.io.IOException;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import ru.msu.cs.svdtop.domain.Profile;

/**
 * @author sankear
 */
public class ProtobufProfileSerializer implements ProtobufSerializer<Profile> {

    public static ProtobufSerializer<Profile> S = new ProtobufProfileSerializer();

    public Profile deserialize(CodedInputStream stream) throws IOException {
        int dimension = stream.readInt32();
        double[] profile = new double[dimension];
        for (int i = 0; i < dimension; ++i) {
            profile[i] = stream.readDouble();
        }
        return new Profile(profile);
    }

    public void serialize(Profile profile, CodedOutputStream stream) throws IOException {
        stream.writeInt32NoTag(profile.getProfile().length);
        for (double d : profile.getProfile()) {
            stream.writeDoubleNoTag(d);
        }
    }

}
