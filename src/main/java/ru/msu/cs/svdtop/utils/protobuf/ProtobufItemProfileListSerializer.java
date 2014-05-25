package ru.msu.cs.svdtop.utils.protobuf;

import ru.msu.cs.svdtop.domain.ItemProfile;

/**
 * @author sankear
 */
public class ProtobufItemProfileListSerializer extends AbstractProtobufListSerializer<ItemProfile> {

    public static final ProtobufItemProfileListSerializer S =
            new ProtobufItemProfileListSerializer(ProtobufItemProfileSerializer.S);

    public ProtobufItemProfileListSerializer(ProtobufSerializer<ItemProfile> serializer) {
        super(serializer);
    }

}
