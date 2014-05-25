package ru.msu.cs.svdtop.utils.generators;

import ru.yandex.bolts.collection.ListF;

/**
 * @author sankear
 */
public interface ListGenerator<T> {

    ListF<T> generate(int size);

}
