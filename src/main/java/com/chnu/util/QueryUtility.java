package com.chnu.util;

import javax.persistence.NoResultException;
import java.util.Optional;
import java.util.function.Supplier;

public class QueryUtility {

    public static <T> Optional<T> findOrEmpty(final Supplier<T> sup) {
        try {
            return Optional.ofNullable(sup.get());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

}