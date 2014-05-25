package ru.msu.cs.svdtop.domain.impl.predictors;

import ru.msu.cs.svdtop.domain.ItemProfile;
import ru.msu.cs.svdtop.domain.Predictor;
import ru.msu.cs.svdtop.domain.Profile;
import ru.msu.cs.svdtop.domain.impl.snapshots.NaiveSnapshot;
import ru.msu.cs.svdtop.utils.algo.FixedSizeTop;

import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.collection.Tuple2;
import ru.yandex.bolts.collection.Tuple2List;

/**
 * @author sankear
 */
public class FixedSizeTopPredictor implements Predictor<NaiveSnapshot> {

    public ListF<Long> getTop(NaiveSnapshot snapshot, Profile profile, int count) {
        FixedSizeTop<Tuple2<Long, Double>> top = FixedSizeTop.cons(
                count,
                Tuple2.<Long, Double>get2F().andThenNaturalComparator().invert());
        for (ItemProfile itemProfile : snapshot.getProfiles()) {
            top.add(Tuple2.tuple(itemProfile.getId(), itemProfile.getProfile().mult(profile)));
        }
        return Tuple2List.tuple2List(top.getTopElements()).get1();
    }

}
