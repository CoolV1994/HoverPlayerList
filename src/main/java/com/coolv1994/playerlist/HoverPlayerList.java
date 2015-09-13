package com.coolv1994.playerlist;

import net.md_5.bungee.api.plugin.Plugin;

/**
 * Created by Vinnie on 9/13/2015.
 */
public class HoverPlayerList extends Plugin {
	@Override
	public void onEnable() {
		getProxy().getPluginManager().registerListener(this, new PingListener(this));
	}
}