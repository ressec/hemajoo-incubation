/*
 * Copyright(c) 2018 - Hemajoo Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Hemajoo Incubation Software (HIS) project
 * which is licensed under the Apache license version 2 and use is subject to
 * license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.hemajoo.incubation.akka.actor;

import org.hemajoo.incubation.akka.message.MessagePingPong;
import org.hemajoo.incubation.akka.message.PingPongType;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import lombok.extern.log4j.Log4j;

/**
 * PingPong actor class definition.
 * <hr>
 * @author Resse Christophe - Hemajoo Corp.
 * @version 1.0.0
 */
@Log4j
public final class PingPong extends AbstractActor
{
	/**
	 * Akka logger.
	 */
	private final LoggingAdapter LOGGER = Logging.getLogger(getContext().system(), this);

	/**
	 * Actor name
	 */
	private String actorName;

	/**
	 * Other actor name
	 */
	private String otherName;

	/**
	 * Reference to the other actor.
	 */
	private ActorSelection otherRef = null;

	/**
	 * Creates a new actor with a given name.
	 * <hr>
	 * @param actorName Actor name.
	 * @param otherName Other actor name.
	 */
	public PingPong(final String actorName, String otherName)
	{
		this.actorName = actorName;
		this.otherName = otherName;
	}

	@Override
	public Receive createReceive()
	{
		if (otherRef == null)
		{
			otherRef = getContext().actorSelection("/user/" + otherName);
		}

		return receiveBuilder()
				.match(MessagePingPong.class, message -> onMessagePingPong(message))
				.matchAny(o -> LOGGER.info("received unknown message"))
				.build();
	}

	private final void onMessagePingPong(final MessagePingPong message)
	{
		// As we received a 'ping' message, let's send to counter part a 'pong' one.
		LOGGER.info("Received {} message", message.getType());

		switch (message.getType())
		{
			case PING:
				otherRef.tell(new MessagePingPong(PingPongType.PONG), getSelf());
				break;

			case PONG:
				otherRef.tell(new MessagePingPong(PingPongType.PING), getSelf());
				break;

			default:
				LOGGER.error("Received {} message. Not able to process!", message.getType());
				break;
		}
	}
}
