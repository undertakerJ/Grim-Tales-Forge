package net.undertaker.grimtales.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.custom.*;
import net.undertaker.grimtales.item.ModItems;
import net.undertaker.grimtales.util.VelescilTreeGrower;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, GrimTales.MOD_ID);
  public static final RegistryObject<Block> CEBBITE_ORE =
      registerBlock(
          "cebbite_ore",
          () ->
              new Block(
                  BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)
                      .destroyTime(25f)
                      .explosionResistance(1200f)));
  public static final RegistryObject<Block> SCULK_CEBBITE_ORE =
      registerBlock(
          "sculk_cebbite_ore",
          () ->
              new Block(
                  BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)
                      .destroyTime(25f)
                      .explosionResistance(1200f)));
  public static final RegistryObject<Block> DEEPSLATE_CEBBITE_ORE =
      registerBlock(
          "deepslate_cebbite_ore",
          () ->
              new Block(
                  BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)
                      .destroyTime(25f)
                      .explosionResistance(1200f)));
  public static final RegistryObject<Block> FIRE_ESSENCE_CRYSTAL_ORE =
      registerBlock(
          "fire_essence_crystal_ore",
          () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).destroyTime(5f)));
  public static final RegistryObject<Block> WATER_ESSENCE_CRYSTAL_ORE =
      registerBlock(
          "water_essence_crystal_ore",
          () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).destroyTime(5f)));
  public static final RegistryObject<Block> AIR_ESSENCE_CRYSTAL_ORE =
      registerBlock(
          "air_essence_crystal_ore",
          () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).destroyTime(5f)));
  public static final RegistryObject<Block> EARTH_ESSENCE_CRYSTAL_ORE =
      registerBlock(
          "earth_essence_crystal_ore",
          () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).destroyTime(5f)));
  public static final RegistryObject<Block> FIRE_DEEPSLATE_ESSENCE_CRYSTAL_ORE =
      registerBlock(
          "fire_deepslate_essence_crystal_ore",
          () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).destroyTime(5f)));
  public static final RegistryObject<Block> WATER_DEEPSLATE_ESSENCE_CRYSTAL_ORE =
      registerBlock(
          "water_deepslate_essence_crystal_ore",
          () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).destroyTime(5f)));
  public static final RegistryObject<Block> AIR_DEEPSLATE_ESSENCE_CRYSTAL_ORE =
      registerBlock(
          "air_deepslate_essence_crystal_ore",
          () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).destroyTime(5f)));
  public static final RegistryObject<Block> EARTH_DEEPSLATE_ESSENCE_CRYSTAL_ORE =
      registerBlock(
          "earth_deepslate_essence_crystal_ore",
          () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).destroyTime(5f)));

  public static final RegistryObject<Block> ADAMANTITE_ORE =
      registerBlock(
          "adamantite_ore",
          () ->
              new Block(
                  BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)
                      .destroyTime(30f)
                      .explosionResistance(1200f)));
  public static final RegistryObject<Block> ADAMANTITE_BLOCK =
      registerBlock(
          "adamantite_block",
          () ->
              new Block(
                  BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)
                      .destroyTime(25f)
                      .explosionResistance(4800f)));
  public static final RegistryObject<Block> CEBBITE_BLOCK =
      registerBlock(
          "cebbite_block",
          () ->
              new Block(
                  BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)
                      .destroyTime(20f)
                      .explosionResistance(1200f)));
 public static final RegistryObject<Block> ASTRALITE_BLOCK =
      registerBlock(
          "astralite_block",
          () ->
              new Block(
                  BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)
                      .destroyTime(30f)
                      .explosionResistance(4800f)));
  public static final RegistryObject<Block> ALTAR_BLOCK =
      registerBlock(
          "altar_block",
          () ->
              new AltarBlock(
                  BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)
                      .destroyTime(-1)
                      .explosionResistance(480000f)));
  public static final RegistryObject<Block> WORKSTATION_BLOCK =
      registerBlock(
          "workstation_block",
          () ->
              new WorkstationBlock(
                  BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion().destroyTime(4f)));
  public static final RegistryObject<Block> PEDESTAL_BLOCK =
      registerBlock(
          "pedestal_block",
          () ->
              new PedestalBlock(
                  BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

  public static final RegistryObject<Block> WORKSTATION_BLOCK_LEFT =
      registerBlock(
          "workstation_block_left",
          () ->
              new WorkstationLeftSide(
                  BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                      .noOcclusion()
                      .destroyTime(4f)));

  public static final RegistryObject<Block> VELESCIL_LOG =
      registerBlock(
          "velescil_log",
          () ->
              new ModFlammableRotatedPillarBlock(
                  BlockBehaviour.Properties.copy(Blocks.OAK_LOG).lightLevel(new ToIntFunction<BlockState>() {
                      @Override
                      public int applyAsInt(BlockState value) {
                          return 5;
                      }
                  })));
  public static final RegistryObject<Block> VELESCIL_WOOD =
      registerBlock(
          "velescil_wood",
          () ->
              new ModFlammableRotatedPillarBlock(
                  BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).lightLevel(new ToIntFunction<BlockState>() {
                      @Override
                      public int applyAsInt(BlockState value) {
                          return 5;
                      }
                  })));
  public static final RegistryObject<Block> STRIPPED_VELESCIL_LOG =
      registerBlock(
          "stripped_velescil_log",
          () ->
              new ModFlammableRotatedPillarBlock(
                  BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
  public static final RegistryObject<Block> STRIPPED_VELESCIL_WOOD =
      registerBlock(
          "stripped_velescil_wood",
          () ->
              new ModFlammableRotatedPillarBlock(
                  BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
  public static final RegistryObject<Block> VELESCIL_SAPLING =
      registerBlock(
          "velescil_sapling",
          () -> new SaplingBlock(new VelescilTreeGrower(), BlockBehaviour.Properties.copy(Blocks.SPRUCE_SAPLING)));

  public static final RegistryObject<Block> VELESCIL_PLANKS =
      registerBlock(
          "velescil_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
                  @Override
                  public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                      return true;
                  }

                  @Override
                  public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                      return 10;
                  }

                  @Override
                  public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                      return 5;
                  }
              });
  public static final RegistryObject<Block> VELESCIL_LEAVES =
      registerBlock(
          "velescil_leaves",
          () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
              @Override
              public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
                  return 5;
              }
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                  return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                  return 60;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                  return 30;
              }
          });

  private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
    RegistryObject<T> toReturn = BLOCKS.register(name, block);
    registerBlockItem(name, toReturn);
    return toReturn;
  }

  private static <T extends Block> RegistryObject<Item> registerBlockItem(
      String name, RegistryObject<T> block) {
    return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
  }

  public static void register(IEventBus eventBus) {
    BLOCKS.register(eventBus);
  }
}
