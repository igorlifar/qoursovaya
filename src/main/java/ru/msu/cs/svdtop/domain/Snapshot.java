package ru.msu.cs.svdtop.domain;

import java.io.File;

/**
 * @author lifar
 */
public interface Snapshot {
    void dumpToFiles(File dir);
}
