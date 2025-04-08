package com.mktdev.reactiveprogramming.practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.Duration;

public class RPSecond {
    // Skip
    private Flux<String> skipFlux() {
        Flux<String> fluxStrings =
                Flux.just("Java", "Javascript", "C++", "C#", "Python", "PHP")
                        .delayElements(Duration.ofSeconds(1));

        // use flatmap for returning publisher
//        return fluxStrings.skip(2);
        return fluxStrings.skip(Duration.ofMillis(2100));
//        return fluxStrings.delayElements(Duration.ofSeconds(1)).log();
    }

    private Flux<Integer> skipFlux2() {
        Flux<Integer> flux = Flux.range(1, 20);

//        return flux;
        return flux.skipWhile(x -> x < 10);
    }

    private Flux<Integer> concatFlux() {
        Flux<Integer> flux1 = Flux.range(1, 20);
        Flux<Integer> flux2 = Flux.range(101, 20);

        // concat will wait until the first flux is completed and so on
        return Flux.concat(flux1, flux2);
    }

    private Flux<Integer> mergeFlux() {
        Flux<Integer> flux1 = Flux.range(1, 20).delayElements(Duration.ofMillis(500));
        Flux<Integer> flux2 = Flux.range(101, 20).delayElements(Duration.ofMillis(500));

        // merge will return data as the data completes
        return Flux.merge(flux1, flux2);
    }

    private Flux<Tuple3<Integer, Integer, Integer>> zipFlux() {
        Flux<Integer> flux1 = Flux.range(1, 20).delayElements(Duration.ofMillis(500));
        Flux<Integer> flux2 = Flux.range(101, 20).delayElements(Duration.ofMillis(500));

        Mono<Integer> mono1 = Mono.just(1001);

        // can have upto 9 Tuple
        // if one flux is less than the other than it will complete as soon as first one completes
        return Flux.zip(flux1, flux2, Flux.range(1, 20));

        // this will end in just first element
//        return Flux.zip(flux1, flux2, mono1);
    }

    public static void main(String[] args) throws InterruptedException {
        RPSecond rpSecond = new RPSecond();

//        rpSecond.skipFlux().subscribe(System.out::println);
//        rpSecond.skipFlux2().subscribe(System.out::println);
//        rpSecond.concatFlux().subscribe(System.out::println);
//            rpSecond.mergeFlux().subscribe(System.out::println);
        rpSecond.zipFlux().subscribe(System.out::println);

        Thread.sleep(10000);
    }
}
