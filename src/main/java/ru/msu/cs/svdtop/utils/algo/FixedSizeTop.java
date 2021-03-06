package ru.msu.cs.svdtop.utils.algo;

import java.util.Arrays;
import java.util.Collection;

import ru.yandex.bolts.collection.Cf;
import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.function.forhuman.Comparator;
import ru.yandex.bolts.internal.Validate;

/**
 * equals input.sorterBy(comparator).take(topSize)
 * time complexity - O(N), where N - number of elements in input
 * memory - O(topSize)
 * @author sankear
 */
public class FixedSizeTop<T> {

    protected final int topSize;
    protected final Comparator<T> comparator;
    protected T maxValue;
    protected int currentSize;
    protected T[] elements;

    public FixedSizeTop(int topSize, Comparator<T> comparator) {
        Validate.isTrue(topSize > 0, "Size of top must be greater than 0");
        this.topSize = topSize;
        this.currentSize = 0;
        this.elements = (T[]) new Object[topSize * 2];
        this.comparator = comparator;
        this.maxValue = null;
    }

    public static <A extends Comparable<A>> FixedSizeTop<A> cons(int topSize) {
        return new FixedSizeTop<A>(topSize, Comparator.<A>naturalComparator());
    }

    public static <A> FixedSizeTop<A> cons(int topSize, Comparator<A> comparator) {
        return new FixedSizeTop<A>(topSize, comparator);
    }

    protected void makeActualTop(boolean checkSize) {
        if (checkSize && currentSize < 2 * topSize) {
            return;
        }
        if (currentSize < topSize) {
            return;
        }
        NthElement.inplaceNth(elements, comparator, currentSize, topSize - 1);
        maxValue = elements[topSize - 1];
        currentSize = topSize;
    }

    public void add(T value) {
        if (maxValue != null && comparator.apply(value, maxValue) >= 0) {
            return;
        }
        elements[currentSize++] = value;
        makeActualTop(true);
    }

    public void addAll(T... values) {
        for (T value : values) {
            add(value);
        }
    }

    public void addAll(Collection<T> values) {
        for (T value : values) {
            add(value);
        }
    }

    public ListF<T> getTopElements() {
        makeActualTop(false);
        return Cf.list(Arrays.copyOf(elements, Math.min(currentSize, topSize))).sorted(comparator);
    }

}
