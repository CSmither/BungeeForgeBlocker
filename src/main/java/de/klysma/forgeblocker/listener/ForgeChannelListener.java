package de.klysma.forgeblocker.listener;

import de.klysma.forgeblocker.Main;
import de.klysma.forgeblocker.utils.ModData;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Map;

public class ForgeChannelListener implements Listener {

    @EventHandler
    public void onMessageRecieve(PluginMessageEvent event){
        ProxyServer.getInstance().getLogger().info("######################### EVENT:->"+event.getTag());
        if(event.getTag().equals("fml:handshake")){
            ModData modData = ModData.getModData(event.getData());

            Map<String, String> mods = modData.getMods();

            ProxiedPlayer proxiedPlayer = (ProxiedPlayer)event.getSender();

            if(proxiedPlayer != null){
                if(!mods.isEmpty()){
                    if(!proxiedPlayer.hasPermission(Main.bypassPerm)) {
                        for (ProxiedPlayer proxiedPlayers : ProxyServer.getInstance().getPlayers()) {
                            if (proxiedPlayers.hasPermission(Main.getMessagePerm)) {
                                proxiedPlayers.sendMessage(Main.prefix + "§cPlayer §e" + proxiedPlayer.getName() + " §cis using Forge and got kicked!");
                            }
                        }

                        proxiedPlayer.disconnect(Main.kickMessage);
                    }else{
                        proxiedPlayer.sendMessage(Main.prefix + "§cCurrently bypass Forge!");
                    }
                }
            }
        }
    }
}
