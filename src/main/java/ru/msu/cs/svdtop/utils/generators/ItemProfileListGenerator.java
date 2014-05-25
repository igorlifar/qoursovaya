package ru.msu.cs.svdtop.utils.generators;

import java.util.Random;

import ru.msu.cs.svdtop.domain.ItemProfile;
import ru.msu.cs.svdtop.domain.Profile;

import ru.yandex.bolts.collection.Cf;
import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.collection.SetF;

/**
 * @author sankear
 */
public class ItemProfileListGenerator implements Generator<ListF<ItemProfile>> {

    private static final Random R = new Random();

    public static final int DIMENSION = 6;
    public static final double COORDINATE_MAX_VALUE = 100;

    public ListF<ItemProfile> generate(int size) {
        ListF<ItemProfile> profiles = Cf.arrayList(size);
        SetF<Long> existIds = Cf.hashSet();
        for (int i = 0; i < size; ++i) {
            long id = R.nextLong();
            while (!existIds.add(id)) {
                id = R.nextLong();
            }
            double[] profile = new double[DIMENSION];
            for (int j = 0; j < DIMENSION; ++j) {
                profile[j] = R.nextDouble() * COORDINATE_MAX_VALUE;
            }
            profiles.add(new ItemProfile(id, new Profile(profile)));
        }
        return profiles;
    }

}
