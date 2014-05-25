package ru.msu.cs.svdtop.domain.impl.predictors;

import ru.msu.cs.svdtop.domain.ItemProfile;
import ru.msu.cs.svdtop.domain.Predictor;
import ru.msu.cs.svdtop.domain.Profile;
import ru.msu.cs.svdtop.domain.impl.snapshots.NaiveSnapshot;

import ru.yandex.bolts.collection.Cf;
import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.collection.Tuple2;
import ru.yandex.bolts.collection.Tuple2List;

/**
 * @author sankear
 */
public class NaivePredictor implements Predictor<NaiveSnapshot> {

    public ListF<Long> getTop(NaiveSnapshot snapshot, Profile profile, int count) {
        Tuple2List<Long, Double> idsWithWeights = Tuple2List.tuple2List(
                Cf.<Tuple2<Long, Double>>arrayList(snapshot.getProfiles().size()));
        for (ItemProfile item : snapshot.getProfiles()) {
            double weight = item.getProfile().mult(profile);
            idsWithWeights.add(item.getId(), weight);
        }
        return idsWithWeights.sortedBy2().get1().reverse().take(count);
    }

}
