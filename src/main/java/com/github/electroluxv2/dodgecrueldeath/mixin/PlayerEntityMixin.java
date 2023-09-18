package com.github.electroluxv2.dodgecrueldeath.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "dropItem*", at = @At("RETURN"))
    protected void onDropItem(final ItemStack stack, final boolean throwRandomly, final boolean retainOwnership, final CallbackInfoReturnable<ItemEntity> cir) {
        if (this.isAlive()) return;

        final var entity = (ItemEntityAccessor) cir.getReturnValue();
        if (entity == null) return;

        entity.setItemAge(Short.MIN_VALUE + 1); // Minecraft checks if age is equal to MIN_VALUE for entities to live infinitely
    }
}
