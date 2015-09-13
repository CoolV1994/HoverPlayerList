package com.coolv1994.playerlist;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Iterator;

/**
 * Created by Vinnie on 9/13/2015.
 */
public class PingListener implements Listener {
	private static final int playerSlots = 12;
	private static final int andMoreSlot = 13;
	private final HoverPlayerList plugin;

	public PingListener(HoverPlayerList plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPing(ProxyPingEvent event) {
		int online = plugin.getProxy().getOnlineCount();
		if (online > playerSlots)
			online = andMoreSlot;
		ServerPing.PlayerInfo[] players = new ServerPing.PlayerInfo[online];
		Iterator<ProxiedPlayer> onlinePlayers = plugin.getProxy().getPlayers().iterator();
		for (int i = 0; i < online; i++) {
			if (i == playerSlots) {
				players[i] = new ServerPing.PlayerInfo("... and " + (online - playerSlots) + " more ...", "");
				break;
			}
			ProxiedPlayer p = onlinePlayers.next();
			players[i] = new ServerPing.PlayerInfo(p.getName(), p.getUniqueId());
		}
		event.getResponse().getPlayers().setSample(players);
	}
}
