/*
 * This file is part of World Downloader: A mod to make backups of your multiplayer worlds.
 * https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/2520465-world-downloader-mod-create-backups-of-your-builds
 *
 * Copyright (c) 2014 nairol, cubic72
 * Copyright (c) 2017 Pokechu22, julialy
 *
 * This project is licensed under the MMPLv2.  The full text of the MMPL can be
 * found in LICENSE.md, or online at https://github.com/iopleke/MMPLv2/blob/master/LICENSE.md
 * For information about this the MMPLv2, see https://stopmodreposts.org/
 *
 * Do not redistribute (in modified or unmodified form) without prior permission.
 */
package wdl;

import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import wdl.api.IEntityManager;
import wdl.api.IWDLMod;
import wdl.api.IWDLModDescripted;
import wdl.config.settings.EntitySettings.TrackDistanceMode;

import com.google.common.collect.Sets;

/**
 * Handles holograms.
 * <br/>
 * Right now, the definition of a hologram is an invisible ArmorStand
 * with a custom name set.  This is how holograms are generated by the
 * <a href="https://dev.bukkit.org/bukkit-plugins/holographic-displays/">
 * Holographic Displays</a> plugin, which is the most popular version.
 * However, the older style of holograms created by Asdjke's method
 * <a href="https://www.youtube.com/watch?v=q1B19JvX5TE">(showcased in
 * this video)</a> do not get recognized.  I don't think this is a big
 * problem, but it is something to keep in mind.
 * <br/>
 * This is also an example of how a {@link IWDLMod} would be implemented.
 */
public class HologramHandler implements IEntityManager,
IWDLModDescripted {

	@Override
	public boolean isValidEnvironment(String version) {
		return true;
	}

	@Override
	public String getEnvironmentErrorMessage(String version) {
		return null;
	}

	@Override
	public String getDisplayName() {
		return "Hologram support";
	}

	@Override
	public Set<String> getProvidedEntities() {
		return Sets.newHashSet("x-extended:hologram");
	}

	@Override
	public String getIdentifierFor(Entity entity) {
		if (entity instanceof EntityArmorStand &&
				entity.isInvisible() &&
				entity.hasCustomName()) {
			return "x-extended:hologram";
		}

		return null;
	}
	@Override
	public int getTrackDistance(String identifier, Entity entity) {
		// Assume holograms act the same as armor stands
		if (identifier == "x-extended:hologram") {
			// XXX This should use the current config value
			return EntityUtils.getEntityTrackDistance(TrackDistanceMode.DEFAULT, "minecraft:armor_stand", entity);
		} else {
			return -1;
		}
	}
	@Override
	public boolean enabledByDefault(String identifier) {
		return true;
	}
	@Override
	public String getGroup(String identifier) {
		return "Other";
	}
	@Override
	public String getDisplayGroup(String group) {
		// TODO
		return null;
	}
	@Override
	public String getDisplayIdentifier(String identifier) {
		// TODO
		return "Hologram";
	}

	@Override
	public String getMainAuthor() {
		return "Pokechu22";
	}

	@Override
	public String[] getAuthors() {
		return null;
	}

	@Override
	public String getURL() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Provides basic support for disabling holograms.";
	}
}
