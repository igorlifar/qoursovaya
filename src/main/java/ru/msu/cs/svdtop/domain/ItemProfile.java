package ru.msu.cs.svdtop.domain;

/**
 * @author lifar
 */
public class ItemProfile {
    private final long id;
    private final Profile profile;

    public ItemProfile(long id, Profile profile) {
        this.id = id;
        this.profile = profile;
    }

    public long getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }
}
