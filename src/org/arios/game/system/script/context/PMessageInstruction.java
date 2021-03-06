package org.arios.game.system.script.context;

import java.util.Arrays;

import org.arios.game.node.entity.player.Player;
import org.arios.game.system.script.ScriptContext;

/**
 * Handles a send message to player instruction.
 * @author Emperor
 */
public class PMessageInstruction extends ScriptContext {

	/**
	 * The messages to send.
	 */
	private final String[] messages;

	/**
	 * Constructs a new {@code PMessageInstruction} {@code Object}.
	 */
	public PMessageInstruction() {
		this(null);
	}

	/**
	 * Constructs a new {@code PMessageInstruction} {@code Object}.
	 * @param messages The messages.
	 */
	public PMessageInstruction(String[] messages) {
		super("message");
		this.messages = messages;
	}

	@Override
	public boolean execute(Object... args) {
		Player player = (Player) args[0];
		for (String s : messages) {
			player.getPacketDispatch().sendMessage(s);
		}
		return true;
	}

	@Override
	public String toString() {
		return Arrays.toString(messages);
	}

	@Override
	public ScriptContext parse(Object... params) {
		String[] messages = new String[params.length];
		int messageIndex = 0;
		for (int i = 0; i < params.length; i++) {
			Object o = params[i];
			if (o instanceof String) {
				messages[messageIndex++] = (String) o;
			}
		}
		if (messageIndex != messages.length) {
			messages = Arrays.copyOf(messages, messageIndex);
		}
		PMessageInstruction context = new PMessageInstruction(messages);
		context.parameters = params;
		return context;
	}
}