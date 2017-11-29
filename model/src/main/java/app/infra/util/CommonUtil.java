package app.infra.util;

import app.model.entity.geography.City;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Contains utility function of the general purpose
 * @author jeka
 * */
public class CommonUtil {
    private CommonUtil() {
    }

    /**
     * Returns not-null unmodifiable copy of the source set
     * @param source
     * @return
     * */
    public static <T> Set<T> getSafeSet(Set<T> source) {
        return Collections.unmodifiableSet(Optional.ofNullable(source).orElse(Collections.emptySet()));
    }

    /**
     * Return not-null unmodifiable copy of the source list
     * @param source
     * @return
     * */
    public static <T> List<T> getSafeList(List<T> source) {
        return Collections.unmodifiableList(Optional.ofNullable(source).orElse(Collections.emptyList()));
    }
}
