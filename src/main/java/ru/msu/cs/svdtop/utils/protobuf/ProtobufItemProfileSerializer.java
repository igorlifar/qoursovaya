package ru.msu.cs.svdtop.utils.protobuf;

import java.io.IOException;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import ru.msu.cs.svdtop.domain.ItemProfile;
import ru.msu.cs.svdtop.domain.Profile;

/**
 * @author sankear
 */
public class ProtobufItemProfileSerializer implements ProtobufSerializer<ItemProfile> {

    public static final ProtobufItemProfileSerializer S = new ProtobufItemProfileSerializer();

    public ItemProfile deserialize(CodedInputStream stream) throws IOException {
        long id = stream.readInt64();
        Profile profile = ProtobufProfileSerializer.S.deserialize(stream);
        return new ItemProfile(id, profile);
    }

    public void serialize(ItemProfile profile, CodedOutputStream stream) throws IOException {
        stream.writeInt64NoTag(profile.getId());
        ProtobufProfileSerializer.S.serialize(profile.getProfile(), stream);
    }

}
