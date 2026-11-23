package com.mythicrealms.entity;

import com.mythicrealms.MythicRealms;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.HorseColor;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

/** Unicornio: usa movimiento, guardado de propietario y montura de los caballos de Minecraft. */
public class UnicornEntity extends HorseEntity {
    public UnicornEntity(EntityType<? extends HorseEntity> type, World world) {
        super(type, world);
        this.setVariant(HorseColor.WHITE);
    }

    public static boolean canSpawnNaturally(EntityType<UnicornEntity> type, WorldAccess world,
                                           SpawnReason reason, BlockPos pos, Random random) {
        return world.getBaseLightLevel(pos, 0) > 8 &&
               world.getBlockState(pos.down()).isSolidBlock(world, pos.down());
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
        // Cada cinco minutos aprox., el unicornio domesticado puede dejar pelo mágico.
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
    public HorseEntity createChild(ServerWorld world, net.minecraft.entity.passive.PassiveEntity mate) {
        UnicornEntity child = MythicRealms.UNICORN.create(world);
        if (child != null) child.setVariant(HorseColor.WHITE);
        return child;
    }
}
