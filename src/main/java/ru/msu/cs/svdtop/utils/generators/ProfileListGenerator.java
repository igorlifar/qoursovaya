package ru.msu.cs.svdtop.utils.generators;

import org.apache.log4j.Logger;
import ru.msu.cs.svdtop.domain.Profile;
import ru.msu.cs.svdtop.utils.Progress;

import ru.yandex.bolts.collection.Cf;
import ru.yandex.bolts.collection.ListF;

/**
 * @author sankear
 */
public class ProfileListGenerator implements ListGenerator<Profile> {

    private static final Logger logger = Logger.getLogger(ProfileListGenerator.class);

    public ListF<Profile> generate(int size) {
        logger.info("Start generate " + size + " profiles");
        ListF<Profile> profiles = Cf.arrayList(size);
        ProfileGenerator profileGenerator = new ProfileGenerator();
        Progress progress = new Progress("Generate profiles", logger, size);
        for (int i = 0; i < size; ++i) {
            profiles.add(profileGenerator.generate());
            progress.tick();
        }
        progress.summary();
        return profiles;
    }

}
