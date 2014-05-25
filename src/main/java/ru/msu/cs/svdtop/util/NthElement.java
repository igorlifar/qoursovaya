package ru.msu.cs.svdtop.util;

import java.util.Random;

import ru.yandex.bolts.function.forhuman.Comparator;

/**
 * @author sankear
 */
public class NthElement {

    private static Random R = new Random();

    private static <T> void swap(T[] elements, int i, int j) {
        T tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    private static <T> void nth(T[] elements, Comparator<T> comparator, int leftBorder, int rightBorder,
            int nthPosition)
    {
        if (leftBorder >= rightBorder) {
            return;
        }
        T separator = elements[leftBorder + R.nextInt(rightBorder - leftBorder + 1)];
        int firstGreater = leftBorder, lastLess = rightBorder;
        while (firstGreater <= lastLess) {
            while (firstGreater <= rightBorder && comparator.apply(elements[firstGreater], separator) < 0) {
                ++firstGreater;
                if (firstGreater > rightBorder) {
                    throw new RuntimeException("Check that you use correct comparator");
                }
            }
            while (lastLess >= leftBorder && comparator.apply(elements[lastLess], separator) > 0) {
                --lastLess;
                if (lastLess < leftBorder) {
                    throw new RuntimeException("Check that you use correct comparator");
                }
            }
            if (firstGreater <= lastLess) {
                swap(elements, firstGreater, lastLess);
                ++firstGreater;
                --lastLess;
            }
        }
        if (nthPosition <= lastLess) {
            nth(elements, comparator, leftBorder, lastLess, nthPosition);
        } else if (nthPosition >= firstGreater) {
            nth(elements, comparator, firstGreater, rightBorder, nthPosition);
        }
    }

    public static <T> void inplaceNth(T[] elements, Comparator<T> comparator, int nthPosition) {
        nth(elements, comparator, 0, elements.length - 1, nthPosition);
    }

    public static <T> void inplaceNth(T[] elements, Comparator<T> comparator, int size, int nthPosition) {
        nth(elements, comparator, 0, size - 1, nthPosition);
    }

}
