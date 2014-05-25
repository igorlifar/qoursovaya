package ru.msu.cs.svdtop.domain;

import ru.yandex.bolts.internal.Validate;

/**
 * @author lifar
 */
public class Profile {
    private final double[] profile;

    public Profile(double[] profile) {
        this.profile = profile;
    }

    public double[] getProfile() {
        return profile;
    }

    public double mult(Profile other) {
        Validate.isTrue(profile.length == other.getProfile().length, "Dimension must be equals");
        double res = 0;
        for (int i = 0; i < profile.length; ++i) {
            res += profile[i] * other.getProfile()[i];
        }
        return res;
    }
}
