package net.undertaker.grimtales.networking;

import net.minecraft.network.Connection;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.*;
import net.minecraftforge.network.simple.SimpleChannel;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.networking.packet.AnvilS2CPacket;
import org.apache.logging.log4j.core.jmx.Server;

import java.net.SocketAddress;
import java.util.function.Supplier;

public class ModNetworkPackets {
  private static SimpleChannel INSTANCE;

  private static int packetId = 0;
  private static int id() {
    return packetId++;
  }

  public static void register() {
    SimpleChannel net =
        NetworkRegistry.ChannelBuilder.named(new ResourceLocation(GrimTales.MOD_ID, "packets"))
            .networkProtocolVersion(() -> "1.0")
            .clientAcceptedVersions(i -> true)
            .serverAcceptedVersions(i -> true)
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
    INSTANCE.send( PacketDistributor.SERVER.noArg() ,packets);
  }

  public static <MSG> void sendToPlayer(MSG packets, ServerPlayer player) {
    INSTANCE.send( PacketDistributor.PLAYER.with(() -> player), packets);
  }

  public static <MSG> void sendToAll(MSG packet) {
    INSTANCE.send(PacketDistributor.ALL.noArg(), packet);
  }
}
