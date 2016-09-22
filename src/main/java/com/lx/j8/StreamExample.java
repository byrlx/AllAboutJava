package com.lx.j8;

import com.lx.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;
import java.util.stream.Stream;
/**
 * Created by lx on 9/8/16.
 */
public class StreamExample {
    ArrayList<Artist> allArtists;

    class Artist {
        String city;

        Artist(String city) {
            this.city = city;
        }

        boolean isFrom(String city) {
            return this.city.equals(city);
        }
    }

    StreamExample() {
        init();
    }

    void init() {
        allArtists = new ArrayList<>();
        allArtists.add(new Artist("London"));
        allArtists.add(new Artist("Paris"));
        allArtists.add(new Artist("London"));
        allArtists.add(new Artist("New York"));
    }

    void test() {
        //London number
        artistsNumFromCity("London");
        artistsNumFromCity("Paris");
        artistsNumFromCity("New York");
        artistsNumFromCity("Bei Jing");

        //of
        List<String> list = Stream.of("a", "m", "f", "z").collect(Collectors.toList());

        //flatmap
        long result = Stream.of(Arrays.asList(1, 4), Arrays.asList(2, 5, 9))
                .flatMap(n->n.stream())
                .reduce(3, (acc, elem) -> acc * elem);
        Log.e(result+"");
    }

    long artistsNumFromCity(String city) {
        long result = allArtists.stream()
                .filter(artist -> artist.isFrom(city))
                .count();

        System.out.println("Artists number from " + city + " is " + result);
        return result;
    }

    public static void main(String[] args) {
        (new StreamExample()).test();
    }
}
