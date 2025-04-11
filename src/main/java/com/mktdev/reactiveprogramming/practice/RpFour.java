package com.mktdev.reactiveprogramming.practice;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.time.Duration;

public class RpFour {
    private Flux<Integer> fluxDoFunctions() {
        Flux<Integer> flux1 = Flux.range(1, 20);

        return flux1.doOnEach(signal -> {
            if (signal.getType() == SignalType.ON_COMPLETE) {
                System.out.println("I am done!");
            } else {
                System.out.println(signal.get());
            }
        });
    }

    private Flux<Integer> fluxDoFunctions2() {
        Flux<Integer> flux1 = Flux.range(1, 20);

        return flux1.doOnComplete(
                () -> System.out.println("I am done!")
        );
    }

    private Flux<Integer> fluxDoFunctions3() {
        Flux<Integer> flux1 = Flux.range(1, 10).delayElements(Duration.ofSeconds(1));

        return flux1.doOnSubscribe(
                (subscription) -> System.out.println("I am subscribed!")
        ).doOnCancel(() -> System.out.println("I am cancelled!"));
    }

    public static void main(String[] args) throws InterruptedException {
        RpFour rpFour = new RpFour();
//        rpFour.fluxDoFunctions().subscribe(System.out::println);
//        rpFour.fluxDoFunctions2().subscribe(System.out::println);
        Disposable disposable = rpFour.fluxDoFunctions3().subscribe(System.out::println);

        Thread.sleep(3400);
        disposable.dispose();
    }
}
