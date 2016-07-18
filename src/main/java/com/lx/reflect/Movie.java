package com.lx.reflect;

import java.util.Arrays;
import java.util.List;

/**
 * Created by douhua on 4/6/16.
 */
public class Movie {
    private String name;
    private int star;

    public Movie(String name, int star) {
        this.name = name;
        this.star = star;
    }

    public static final List<Movie> BEST_MOVIES = Arrays.asList(
            new Movie("the man from earth", 5),
            new Movie("noting hill", 5),
            new Movie("x man 1", 4),
            new Movie("x men 2", 3)
    );

}
