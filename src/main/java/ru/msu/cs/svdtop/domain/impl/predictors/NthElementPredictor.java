package ru.msu.cs.svdtop.domain.impl.predictors;

import java.util.Arrays;

import ru.msu.cs.svdtop.domain.ItemProfile;
import ru.msu.cs.svdtop.domain.Predictor;
import ru.msu.cs.svdtop.domain.Profile;
import ru.msu.cs.svdtop.domain.impl.snapshots.NaiveSnapshot;
import ru.msu.cs.svdtop.utils.algo.NthElement;

import ru.yandex.bolts.collection.Cf;
import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.collection.Tuple2;
import ru.yandex.bolts.collection.Tuple2List;

/**
 * @author sankear
 */
public class NthElementPredictor implements Predictor<NaiveSnapshot> {

    public ListF<Long> getTop(NaiveSnapshot snapshot, Profile profile, int count) {
        Tuple2List<Long, Double> idsWithWeights =
                Tuple2List.tuple2List(Cf.<Tuple2<Long, Double>>arrayList(snapshot.getProfiles().size()));
        for (ItemProfile itemProfile : snapshot.getProfiles()) {
            idsWithWeights.add(itemProfile.getId(), itemProfile.getProfile().mult(profile));
        }
        Tuple2<Long, Double>[] arr = idsWithWeights.toArray();
        NthElement.inplaceNth(
                arr,
                Tuple2.<Long, Double>get2F().andThenNaturalComparator().invert(), count - 1);
        ListF<Tuple2<Long, Double>> tmp = Cf.list(Arrays.copyOf(arr, count));
        return Tuple2List.tuple2List(tmp).sortedBy2().get1().reverse();
    }

}
