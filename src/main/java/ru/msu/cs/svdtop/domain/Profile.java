package ru.msu.cs.svdtop.domain;

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
}
