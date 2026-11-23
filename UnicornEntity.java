package com.mythicrealms.entity;

import com.mythicrealms.MythicRealms;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.HorseColor;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

/** Unicornio: modelo y animaciones de caballo; ocho colores de fantasía. */
public class UnicornEntity extends HorseEntity {
    private static final TrackedData<Integer> COAT =
            DataTracker.registerData(UnicornEntity.class, TrackedDataHandlerRegistry.INTEGER);
    // 0 blanco 30%; 1 celeste 25%; 2 rosado 25%; 3 verde 7%;
    // 4 azul 6%; 5 amarillo 5%; 6 violeta 1.5%; 7 rojo 0.5%.
    private static final int[] WEIGHTS_PER_1000 = {300, 250, 250, 70, 60, 50, 15, 5};
    private static final int COLOR_COUNT = WEIGHTS_PER_1000.length;

    public UnicornEntity(EntityType<? extends HorseEntity> type, World world) {
        super(type, world);
        this.setVariant(HorseColor.WHITE);
        if (!world.isClient) setCoat(rollCoat(this.random));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(COAT, 0);
    }

    private static int rollCoat(Random random) {
        int number = random.nextInt(1000);
        for (int i = 0; i < WEIGHTS_PER_1000.length; i++) {
            number -= WEIGHTS_PER_1000[i];
            if (number < 0) return i;
        }
        return 0;
    }

    public int getCoat() {
        return this.dataTracker.get(COAT);
    }

    public void setCoat(int coat) {
        this.dataTracker.set(COAT, Math.max(0, Math.min(coat, COLOR_COUNT - 1)));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("MythicCoat", getCoat());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("MythicCoat")) setCoat(nbt.getInt("MythicCoat"));
    }

    public static boolean canSpawnNaturally(EntityType<UnicornEntity> type, WorldAccess world,
                                            SpawnReason reason, BlockPos pos, Random random) {
        return world.getBaseLightLevel(pos, 0) > 8
                && world.getBlockState(pos.down()).isSolidBlock(world, pos.down());
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack held = player.getStackInHand(hand);
        if (!this.isTame() && held.isOf(Items.GOLDEN_APPLE)) {
            if (!this.getWorld().isClient) {
                if (!player.getAbilities().creativeMode) held.decrement(1);
                this.setOwnerUuid(player.getUuid());
                this.setTame(true);
                this.getWorld().sendEntityStatus(this, (byte) 7);
            }
            return ActionResult.success(this.getWorld().isClient);
        }
        return super.interactMob(player, hand);
    }

    @Override
    public void tick() {
        super.tick();
        // Recurso renovable sin matar a los unicornios domesticados.
        if (!this.getWorld().isClient && this.isTame() && !this.isBaby()
                && this.age % 6000 == 0 && this.random.nextInt(3) == 0) {
            this.dropItem(MythicRealms.PELO_UNICORNIO);
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.GOLDEN_CARROT);
    }

    @Override
    public HorseEntity createChild(ServerWorld world, PassiveEntity mate) {
        UnicornEntity child = MythicRealms.UNICORN.create(world);
        if (child != null) {
            child.setVariant(HorseColor.WHITE);
            // 90% uno de los progenitores, 10% nuevo color al azar.
            if (mate instanceof UnicornEntity other && this.random.nextInt(10) != 0) {
                child.setCoat(this.random.nextBoolean() ? this.getCoat() : other.getCoat());
            } else {
                child.setCoat(rollCoat(this.random));
            }
        }
        return child;
    }
}
