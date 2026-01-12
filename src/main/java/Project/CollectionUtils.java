package Project;

import java.util.*;
import java.util.function.Predicate;

public final class CollectionUtils {
    public static <T> List<T> sort(Collection<T> data, Comparator<T> comparator) {
        List<T> list = new ArrayList<>(data);
        list.sort(comparator);
        return list;
    }
}
