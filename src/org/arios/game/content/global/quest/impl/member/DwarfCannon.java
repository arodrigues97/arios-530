package org.arios.game.content.global.quest.impl.member;

import org.arios.game.content.global.quest.Quest;
import org.arios.game.node.entity.player.Player;
import org.arios.game.node.item.Item;

/**
 * Represents the dwarf cannon quest.
 * @author Vexia
 */
public class DwarfCannon extends Quest {

	/**
	 * The name fo the quest.
	 */
	public static final String NAME = "Dwarf Cannon";

	/**
	 * The dwarf remain item.
	 */
	public static final Item DWARF_REMAINS = new Item(0);

	/**
	 * The tool kit item.
	 */
	public static final Item TOOL_KIT = new Item(1);

	/**
	 * The nulodion notes.
	 */
	public static final Item NULODION_NOTES = new Item(3);

	/**
	 * The mould item.
	 */
	public static final Item MOULD = new Item(4);

	/**
	 * Constructs a new {@Code DwarfCannon} {@Code Object}
	 * @param player the player.
	 */
	public DwarfCannon(Player player) {
		super(player);
	}

	@Override
	public void update() {
		super.clear();
		switch (stage) {
		case 0:
			line("<blue>I can start this quest by speaking to <red>Lawgof the Dwarven<n><red>Captain of the Black Watch <blue>, he is defending an area<n><red>North-west of the Fishing Guild <blue>against <red>goblin <blue>attack.", 11);
			break;
		case 10:
			line("<str>I have spoken to Captain Lawgof, he recruited me into the<n><str>Black Guard and asked me to help the dwarves.<n><n>" + (player.getConfigManager().get(1) == 2016 ? "<str>I have repaired all the broken railings,<n><blue>I should report back to <red>Captain Lawgof." : "<blue>My first task is to <red>fix the broken railings<n><blue>in the dwarves defensive perimeter."), 11);
			break;
		case 20:
			line("<str>I have spoken to Captain Lawgof, he recruited me into the<n><str>Black Guard and asked me to help the dwarves.<n><n>" + (player.hasItem(DWARF_REMAINS) ? "<str>I went to the watchtower where I found the remains of<n><str>Gilob.<n><blue>I should take them back to <red>Captain Lawgof." : "<str>I have repaired all the broken railings,<n><blue>Captain Lawgof has asked me to check up on his guards at<n><red>the watchtower <blue>to the South of this camp."), 11);
			break;
		case 30:
			line("<str>I have spoken to Captain Lawgof, he recruited me into the<n><str>Black Guard and asked me to help the dwarves.<n><n><str>I gave the remains to Captain Lawgof.<n><blue>he sent me to find the <red>Goblin base<blue>, South-east of the<n><blue>camp.", 11);
			break;
		case 40:
			line("<str>I have spoken to Captain Lawgof, he recruited me into the<n><str>Black Guard and asked me to help the dwarves.<n><n><str>I have rescued Lollk and sent him back to the Captain.<n><blue>I need to <red>speak to Captain Lawgof <blue>again.", 11);
			break;
		case 50:
			line("<str>I have spoken to Captain Lawgof, he recruited me into the<n><str>Black Guard and asked me to help the dwarves.<n><n><blue>Captain Lawgof has asked me to <red>fix the multicannon.", 11);
			break;
		case 60:
			line("<str>I have spoken to Captain Lawgof, he recruited me into the<n><str>Black Guard and asked me to help the dwarves.<n><n><str>I've fixed the broken multicannon,<n><blue>I need to <red>speak to Captain Lawgof <blue>again.", 11);
			break;
		case 70:
			line("<str>I have spoken to Captain Lawgof, he recruited me into the<n><str>Black Guard and asked me to help the dwarves.<n><n><blue>Captain Lawgof asked me to find <red>Nulodion the Engineer<blue>, he<n><blue>needs to know what <red>ammunition the multicannon <blue>fires.", 11);
			break;
		case 80:
			line("<str>I have spoken to Captain Lawgof, he recruited me into the<n><str>Black Guard and asked me to help the dwarves.<n><n><str>I've spoken to Nulodion,<n><str>He gave me an ammo mould and notes<n><blue>I need to <red>speak to Captain Lawgof <blue>again.", 11);
			break;
		case 100:
			line("<str>I have spoken to Captain Lawgof, he recruited me into the<n><str>Black Guard and asked me to help the dwarves.<n><n><str>I've helped the dwarves keep out the Goblins, and found<n><str>the remains of their friend.<n><str>I fixed the cannon and got a mould for Captain Lawgof.<n><blue>I can now <red>buy a multicannon <blue>from <red>Nulodion <blue>as a reward.<n><n><col=FF0000>QUEST COMPLETE!", 11);
			break;
		}
	}

	@Override
	public void start() {
		super.start();
		player.getConfigManager().set(0, 1);
	}

	@Override
	public void finish() {
		super.finalize();
		player.getPacketDispatch().sendString("1 Quest Point", 277, 8 + 2);
		player.getPacketDispatch().sendString("750 Crafting XP", 277, 9 + 2);
		player.getPacketDispatch().sendString("Permission to purchase and", 277, 10 + 2);
		player.getPacketDispatch().sendString("use the Dwarf Multicannon", 277, 11 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(TOOL_KIT.getId(), 235, 277, 3 + 2);
		player.getQuestRepository().update(player);
	}

	@Override
	public int[] getConfig() {
		int id = 0, val = 0;
		switch (state) {
		case STARTED:
			val = player.getConfigManager().get(id);
			break;
		case COMPLETED:
			val = 11;
			break;
		default:
			break;
		}
		return new int[] { id, val };
	}

	@Override
	public boolean isInDevelopment() {
		return false;
	}
	
	@Override
	public int getButtonId() {
		return getIndex() - 1;
	}


	@Override
	public int getIndex() {
		return 49;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getQuestPoints() {
		return 1;
	}

}
