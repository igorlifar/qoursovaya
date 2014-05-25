package ru.msu.cs.svdtop.utils.protobuf;

import ru.msu.cs.svdtop.domain.Profile;

/**
 * @author sankear
 */
public class ProtobufProfileListSerializer extends AbstractProtobufListSerializer<Profile> {

    public static final ProtobufProfileListSerializer S =
            new ProtobufProfileListSerializer(ProtobufProfileSerializer.S);

    public ProtobufProfileListSerializer(ProtobufSerializer<Profile> serializer) {
        super(serializer);
    }

}
