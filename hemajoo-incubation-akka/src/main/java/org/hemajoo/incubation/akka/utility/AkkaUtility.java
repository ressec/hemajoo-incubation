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

public final class AkkaUtility
{
	/**
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	@SuppressWarnings("nls")
	private static int getRandomNumberInRange(int min, int max)
	{
		if (min >= max)
		{
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();

		return r.nextInt(max - min + 1) + min;
	}

}
