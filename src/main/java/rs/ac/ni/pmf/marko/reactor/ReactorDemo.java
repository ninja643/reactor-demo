package rs.ac.ni.pmf.marko.reactor;

import reactor.core.publisher.Flux;

public class ReactorDemo
{
	public static void main(String[] args)
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
}
