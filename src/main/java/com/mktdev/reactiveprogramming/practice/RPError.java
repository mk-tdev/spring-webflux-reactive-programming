package com.mktdev.reactiveprogramming.practice;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class RPError {

    private Flux<Integer> testErrorHandling1 () {
        Flux<Integer> flux1 = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .map(integer -> {
                    if (integer == 5) {
                        throw new RuntimeException("Unexpected error");
                    } else {
                        return integer;
                    }
                });

        return flux1.onErrorContinue(
                (throwable, o) -> {
                    System.out.println("Dont worry about " + o);
                }
        );
    }

    private Flux<Integer> testErrorHandling2 () {
        Flux<Integer> flux1 = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .map(integer -> {
                    if (integer == 5) {
                        throw new RuntimeException("Unexpected error");
                    } else {
                        return integer;
                    }
                });

        return flux1.
                onErrorReturn(-1);

    }

    private Flux<Integer> testErrorHandling3 () {
        Flux<Integer> flux1 = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .map(integer -> {
                    if (integer == 5) {
                        throw new RuntimeException("Unexpected error");
                    } else {
                        return integer;
                    }
                });

        return flux1.
                onErrorResume(throwable -> Flux.range(1000, 10));

    }

    public static void main(String[] args) throws InterruptedException {

        RPError rpError = new RPError();

//        rpError.testErrorHandling1().subscribe(System.out::println);
        rpError.testErrorHandling3().subscribe(System.out::println);

        Thread.sleep(10000);
    }
}
