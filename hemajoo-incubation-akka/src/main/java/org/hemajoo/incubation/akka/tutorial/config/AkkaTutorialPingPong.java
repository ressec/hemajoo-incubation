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
package org.hemajoo.incubation.akka.tutorial.config;

import org.hemajoo.incubation.akka.actor.PingPong;
import org.hemajoo.incubation.akka.message.MessagePingPong;
import org.hemajoo.incubation.akka.message.PingPongType;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;

/**
 * A tutorial to demonstrate how easy it is to create a simple actor system.
 * <p>
 * This tutorial is part of a series to demonstrate the use of the {@code Akka} library regarding several topics.
 * <p>
 * The purpose of the {@link AkkaTutorialPingPong} tutorial is to create a simple actor system with two actor instances.
 * These instances called {@code ping} and {@code pong} send a simple {@code PingPong} message between each other. As soon
 * as {@code ping} receives a {@code PingPong} message, it will log it and send send to {@code pong} a {@code PingPong}
 * message and so on.
 * <p>
 * The two actor instances are running on the same JVM.
 * <hr>
 * @author Resse Christophe - Hemajoo Corp.
 */
public final class AkkaTutorialPingPong
{
	/**
	 * Actor system name.
	 */
	@SuppressWarnings("nls")
	private static final String ACTOR_SYSTEM_NAME = "org_hemajoo_incubation_akka_tutorial_1";

	/**
	 * Main tutorial entry point method.
	 * <hr>
	 * @param arguments Arguments passed on the command line.
	 */
	@SuppressWarnings("nls")
	public static void main(String[] arguments)
	{
		// Create an actor system named.
		final ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME);

		// Create 2 PingPong actor instances.
		final ActorRef ping = system.actorOf(Props.create(PingPong.class, () -> new PingPong("ping", "pong")), "ping");
		system.actorOf(Props.create(PingPong.class, () -> new PingPong("pong", "ping")), "pong");

		// Send an initial message to the ping actor instance to initiate the exchange of messages.
		final Inbox inbox = Inbox.create(system);
		inbox.send(ping, new MessagePingPong(PingPongType.PING));
	}

}
