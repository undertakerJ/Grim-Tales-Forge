package net.undertaker.grimtales.networking;

import net.minecraft.network.Connection;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.*;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.networking.packet.AnvilS2CPacket;
import org.apache.logging.log4j.core.jmx.Server;

import java.net.SocketAddress;

public class ModNetworkPackets {
  private static SimpleChannel INSTANCE;

  private static int packetId = 0;

  private static int id() {
    return packetId++;
  }

  public static void register() {
    SimpleChannel net =
        ChannelBuilder.named(new ResourceLocation(GrimTales.MOD_ID, "packets"))
            .networkProtocolVersion(1)
            .clientAcceptedVersions((status, i) -> true)
            .serverAcceptedVersions((status, i) -> true)
            .simpleChannel();

    INSTANCE = net;

    INSTANCE
        .messageBuilder(AnvilS2CPacket.class, id())
        .decoder(AnvilS2CPacket::new)
        .encoder(AnvilS2CPacket::writeToBytes)
        .consumerMainThread(AnvilS2CPacket::handle)
        .add();
  }

  public static <MSG> void sendToServer(MSG packets) {
    INSTANCE.send(packets, PacketDistributor.SERVER.noArg());
  }

  public static <MSG> void sendToPlayer(MSG packets, ServerPlayer player) {
    INSTANCE.send(packets, PacketDistributor.PLAYER.with(player));
  }

  public static <MSG> void sendToAll(MSG packet) {
    INSTANCE.send(packet, PacketDistributor.ALL.noArg());
  }
}
