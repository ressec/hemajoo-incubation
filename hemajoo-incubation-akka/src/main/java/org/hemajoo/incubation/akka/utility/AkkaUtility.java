/*
 * Copyright(c) 2018 - Hemajoo Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the
 * Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.hemajoo.incubation.akka.utility;

import java.util.Random;

/**
 * Utility class for the Akka tutorials.
 * <hr>
 * @author Resse Christophe - Hemajoo Corp.
 * @version 1.0.0
 */
public final class AkkaUtility
{
	/**
	 * A pseudo-random number generator.
	 */
	private static Random random = new Random();

	/**
	 * Private constructor to avoid instantiation.
	 */
	private AkkaUtility()
	{
	}

	/**
	 * Generates a pseudo-random number given a minimum and maximum value.
	 * <hr>
	 * @param min Minimum value to generate.
	 * @param max Maximum value to generate.
	 * @return A pseudo-random number.
	 */
	@SuppressWarnings("nls")
	public static final int getRandomNumberInRange(int min, int max)
	{
		if (min >= max)
		{
			throw new IllegalArgumentException("max must be greater than min");
		}

		return random.nextInt(max - min + 1) + min;
	}
}
