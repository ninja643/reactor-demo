package rs.ac.ni.pmf.marko.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactorDemo
{
	public static void main(String[] args)
	{
		doOnNextWithMono();
		simpleDoOnNext();
	}

	public static void simpleDoOnNext()
	{
		final Flux<Integer> evenNumbers = Flux.range(1, 10)
			.filter(value -> value % 2 == 0);

		evenNumbers
			.doOnNext(number -> {
				try
				{
					System.out.println("Doing some stuff with the number " + number);
					Thread.sleep(1000);
					System.out.println("Stuff done for " + number);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			})
			.doOnNext(number -> {
				try
				{
					System.out.println("Doing something completely different with the number " + number);
					Thread.sleep(800);
					System.out.println("Something completely different done for " + number);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			})
			.subscribe(number -> {
				System.out.println("Number: " + number);
			});
	}

	public static void doOnNextWithMono()
	{
		final Flux<Integer> evenNumbers = Flux.range(1, 10)
			.filter(value -> value % 2 == 0);

		evenNumbers
			.doOnNext(number -> {
				doSomethingWithTheNumber(number)
					.subscribe(num -> {
						System.out.println("Handled the number " + num + " in subscribe");
					});
			})
			.subscribe(number -> {
				System.out.println("Number: " + number);
			});
	}

	private static Mono<Integer> doSomethingWithTheNumber(int number)
	{
		System.out.println("Gonna do something with " + number);
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		System.out.println("Done something with " + number);
		return Mono.just(number);
	}
}
