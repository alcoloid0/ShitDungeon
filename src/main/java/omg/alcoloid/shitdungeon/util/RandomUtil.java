package omg.alcoloid.shitdungeon.util;

import com.google.common.collect.Iterables;
import org.jetbrains.annotations.NotNull;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.Random;

public final class RandomUtil {
    private static final Random RANDOM = new Random();

    private RandomUtil() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static int randomInt(int origin, int bound) {
        return RANDOM.nextInt(origin, bound);
    }

    public static int randomInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static <T> T choice(@NotNull Collection<T> collection) {
        if (collection.isEmpty()) {
            throw new InvalidParameterException("collection is empty");
        }

        return Iterables.get(collection, randomInt(collection.size()));
    }

    @SafeVarargs
    public static <T> T choice(@NotNull T @NotNull ... array) {
        if (array.length == 0) {
            // qwizlebb, как тебе мой уровень английского A1?
            throw new InvalidParameterException("array.length == 0");
        }

        return array[randomInt(array.length)];
    }
}
