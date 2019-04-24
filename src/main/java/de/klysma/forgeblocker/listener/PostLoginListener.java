package de.klysma.forgeblocker.listener;

import de.klysma.forgeblocker.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

public class PostLoginListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent event){
        ProxiedPlayer proxiedPlayer = event.getPlayer();

            ProxyServer.getInstance().getScheduler().schedule(Main.plugin, () -> {
                proxiedPlayer.sendData("fml:handshake", new byte[]{-2, 0});
                proxiedPlayer.sendData("fml:handshake", new byte[]{0, 2, 0, 0, 0, 0});
                proxiedPlayer.sendData("fml:handshake", new byte[]{2, 0, 0, 0, 0});
            }, 2, TimeUnit.SECONDS);
    }
}
