package net.undertaker.grimtales.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.ModBlocks;

public class ModBlockEntities {

  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
      DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GrimTales.MOD_ID);

  public static void register(IEventBus eventBus) {
    BLOCK_ENTITIES.register(eventBus);
  }

  public static final RegistryObject<BlockEntityType<AltarBlockEntity>> ALTAR_BLOCK_ENTITY =
      BLOCK_ENTITIES.register(
          "altar_block_entity",
          () ->
              BlockEntityType.Builder.of(AltarBlockEntity::new, ModBlocks.ALTAR_BLOCK.get())
                  .build(null));

  public static final RegistryObject<BlockEntityType<WorkstationEntity>> WORKSTATION_BLOCK_ENTITY =
      BLOCK_ENTITIES.register(
          "workstation_block_entity",
          () ->
              BlockEntityType.Builder.of(WorkstationEntity::new, ModBlocks.WORKSTATION_BLOCK.get())
                  .build(null));
  public static final RegistryObject<BlockEntityType<PedestalEntity>> PEDESTAL_ENTITY =
          BLOCK_ENTITIES.register("pedestal_entity", () ->
                  BlockEntityType.Builder.of(PedestalEntity::new, ModBlocks.PEDESTAL_BLOCK.get()).build(null));
}
