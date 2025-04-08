package com.mktdev.reactiveprogramming.practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class RPFirst {
    // Mono | Flux
    // Publisher | Subscriber

    // to send one data
    private Mono<String> helloMono() {
        return Mono.just("Hello").log();
    }

    private Mono<String> emptyMono() {
        // cannot do this
//        return Mono.just(null);

        return Mono.empty();
    }

    // to send more than one data
    private Flux<String> helloFlux() {
        List<String> programmingLanguages = Arrays.asList("Java", "Javascript", "C++", "C#", "Python", "PHP");
        return Flux.fromIterable(programmingLanguages);

//        return Flux.just("Java", "Javascript", "C++", "C#", "Python", "PHP");
    }

    // Mapping
    private Flux<String> mapFlux() {
        Flux<String> fluxStrings = Flux.just("Java", "Javascript", "C++", "C#", "Python", "PHP");

        // use map for returning normal values
        return fluxStrings.map(s -> s.toUpperCase(Locale.ROOT));
    }

    // Flat Mapping
    private Flux<String> flatmapFlux() {
        Flux<String> fluxStrings = Flux.just("Java", "Javascript", "C++", "C#", "Python", "PHP");

        // use flatmap for returning publisher
        return fluxStrings.flatMap(s -> Mono.just(s.toUpperCase(Locale.ROOT)));
    }



    public static void main(String[] args) {

        RPFirst rpFirst = new RPFirst();

        rpFirst.helloMono().subscribe(System.out::println);
        rpFirst.emptyMono().subscribe(System.out::println);
        rpFirst.helloFlux().subscribe(System.out::println);
        rpFirst.mapFlux().subscribe(System.out::println);
        rpFirst.flatmapFlux().subscribe(System.out::println);

    }
}
