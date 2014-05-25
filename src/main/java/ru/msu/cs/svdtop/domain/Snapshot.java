package ru.msu.cs.svdtop.domain;

import java.io.File;
import java.io.IOException;

/**
 * @author lifar
 */
public interface Snapshot {
    String getDefaultDirectory();
    void dumpToFiles(File dir) throws IOException;
}
