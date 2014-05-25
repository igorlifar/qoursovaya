package ru.msu.cs.svdtop.utils.generators;

import java.util.Random;

import org.apache.log4j.Logger;
import ru.msu.cs.svdtop.domain.ItemProfile;
import ru.msu.cs.svdtop.utils.Progress;

import ru.yandex.bolts.collection.Cf;
import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.collection.SetF;

/**
 * @author sankear
 */
public class ItemProfileListGenerator implements ListGenerator<ItemProfile> {

    private static final Logger logger = Logger.getLogger(ItemProfileListGenerator.class);

    private static final Random R = new Random();

    public ListF<ItemProfile> generate(int size) {
        logger.info("Start generate " + size + " item profiles");
        Progress progress = new Progress("Generate item profiles", logger, size);
        ListF<ItemProfile> profiles = Cf.arrayList(size);
        SetF<Long> existIds = Cf.hashSet();
        ProfileGenerator profileGenerator = new ProfileGenerator();
        for (int i = 0; i < size; ++i) {
            long id = Math.abs(R.nextLong());
            while (!existIds.add(id)) {
                id = Math.abs(R.nextLong());
            }
            profiles.add(new ItemProfile(id, profileGenerator.generate()));
            progress.tick();
        }
        progress.summary();
        return profiles;
    }

}
