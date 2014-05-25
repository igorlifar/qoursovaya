package ru.msu.cs.svdtop.utils.protobuf;

import java.io.IOException;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import ru.msu.cs.svdtop.domain.ItemProfile;
import ru.msu.cs.svdtop.domain.Profile;

import ru.yandex.bolts.collection.Cf;
import ru.yandex.bolts.collection.ListF;

/**
 * @author sankear
 */
public class ProtobufItemProfileListSerializer implements ProtobufSerializer<ListF<ItemProfile>> {

    public static ProtobufItemProfileListSerializer S = new ProtobufItemProfileListSerializer();

    public ListF<ItemProfile> deserialize(CodedInputStream stream) throws IOException {
        int total = stream.readInt32();
        ListF<ItemProfile> profiles = Cf.arrayList(total);
        for (int i = 0; i < total; ++i) {
            long id = stream.readInt64();
            Profile profile = ProtobufProfileSerializer.S.deserialize(stream);
            profiles.add(new ItemProfile(id, profile));
        }
        return profiles;
    }

    public void serialize(ListF<ItemProfile> profiles, CodedOutputStream stream) throws IOException{
        stream.writeInt32NoTag(profiles.size());
        for (ItemProfile profile : profiles) {
            stream.writeFixed64NoTag(profile.getId());
            ProtobufProfileSerializer.S.serialize(profile.getProfile(), stream);
        }
    }

}
