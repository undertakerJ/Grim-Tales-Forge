package net.undertaker.grimtales.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.undertaker.grimtales.sound.ModSounds;

public class AnvilS2CPacket {
  private int x;
  private int y;
  private int z;

  public AnvilS2CPacket(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public AnvilS2CPacket(FriendlyByteBuf byteBuf) {
    this.x = byteBuf.readInt();
    this.y = byteBuf.readInt();
    this.z = byteBuf.readInt();
  }

  public void writeToBytes(FriendlyByteBuf byteBuf) {
    byteBuf.writeInt(x);
    byteBuf.writeInt(y);
    byteBuf.writeInt(z);
  }

  public boolean handle(CustomPayloadEvent.Context ctx) {
    ctx.enqueueWork(
        () -> {
          Minecraft minecraft = Minecraft.getInstance();
          spawnParticles(x, y, z);
          minecraft.level.playLocalSound(x,y,z, ModSounds.VINE_BOOM.get(), SoundSource.PLAYERS, 1f, 1f, false);
        });
    return true;
  }

  private void spawnParticles(double x, double y, double z) {
    Minecraft minecraft = Minecraft.getInstance();
    if (minecraft.level != null) {
      for (int i = 0; i < 40; i++) {
        minecraft.level.addParticle(
            ParticleTypes.SOUL_FIRE_FLAME,
            x + RandomSource.create().triangle(0.5, 1),
            y + minecraft.level.random.nextDouble(),
            z + RandomSource.create().triangle(0.5, 1),
            0,
            0.05,
            0);
      }
    }
  }
}
