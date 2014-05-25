package ru.msu.cs.svdtop.utils.generators;

import java.util.Random;

import ru.msu.cs.svdtop.domain.Profile;

/**
 * @author sankear
 */
public class ProfileGenerator implements Generator<Profile> {

    private static final Random R = new Random();

    public static final int DIMENSION = 6;
    public static final double COORDINATE_MAX_VALUE = 100;

    public Profile generate() {
        double[] profile = new double[DIMENSION];
        for (int i = 0; i < DIMENSION; ++i) {
            profile[i] = R.nextDouble() * COORDINATE_MAX_VALUE;
        }
        return new Profile(profile);
    }

}
