/*
 * Copyright(c) 2017 - Heliosphere Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the
 * Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.hemajoo.incubation.akka.message;

import java.io.Serializable;

import lombok.Getter;

/**
 * A ping/pong message
 * <hr>
 * @author Resse Christophe - Hemajoo Corp.
 * @version 1.0.0
 */
public class MessagePingPong implements Serializable
{
	/**
	 * Serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Type.
	 */
	@Getter
	private final PingPongType type;

	/**
	 * Creates a new ping/pong message.
	 * <hr>
	 * @param type Message type (a ping or a pong).
	 */
	public MessagePingPong(final PingPongType type)
	{
		this.type = type;
	}
}
