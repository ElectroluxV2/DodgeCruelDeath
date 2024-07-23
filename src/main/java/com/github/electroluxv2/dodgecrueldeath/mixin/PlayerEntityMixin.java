package com.github.electroluxv2.dodgecrueldeath.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;", at = @At("RETURN"))
    protected void onDropItem(final ItemStack stack, final boolean throwRandomly, final boolean retainOwnership, final CallbackInfoReturnable<ItemEntity> cir) {
        if (this.isAlive()) return;

        final var entity = (ItemEntityAccessor) cir.getReturnValue();
        if (entity == null) return;

        entity.setAge(Short.MIN_VALUE + 1); // Minecraft checks if age is equal to MIN_VALUE for entities to live infinitely
    }
}
