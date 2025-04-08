package com.mktdev.reactiveprogramming.practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RpThree {

    private Mono<List<Integer>> collectFlux() {
        Flux<Integer> flux1 = Flux.range(1, 20);

        // combine everything into a single list
        return flux1.collectList();
    }

    private Flux<List<Integer>> bufferFlux() {
        Flux<Integer> flux1 = Flux.range(1, 10).delayElements(Duration.ofMillis(500));

        // collecting the items to more than one list
//        return flux1.buffer();
        return flux1.buffer(3);
    }

    private Mono<Map<Integer, Integer>> collectMapFlux() {
        Flux<Integer> flux1 = Flux.range(1, 10);
        return flux1.collectMap(i -> i, i -> i * 2);
    }

    public static void main(String[] args) throws InterruptedException {

        RpThree rpThree = new RpThree();

//        rpThree.collectFlux().subscribe(System.out::println);
//        rpThree.bufferFlux().subscribe(System.out::println);
//        rpThree.collectFlux().subscribe(System.out::println);
        rpThree.collectMapFlux().subscribe(System.out::println);

        // to get it synchronously
//        List<Integer> listOfIntegers = rpThree.collectFlux().block();
//        System.out.println(listOfIntegers);

//        Thread.sleep(15000);
    }
}
