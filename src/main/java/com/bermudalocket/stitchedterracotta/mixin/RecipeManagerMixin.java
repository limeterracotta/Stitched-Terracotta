package com.bermudalocket.stitchedterracotta.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;
import java.util.Optional;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {

    @Shadow protected abstract <C extends Inventory, T extends Recipe<C>> Map<Identifier, Recipe<C>> getAllOfType(RecipeType<T> type);

    /**
     * @author bermudalocket
     * @reason performance
     */
    @Overwrite
    public <C extends Inventory, T extends Recipe<C>> Optional<T> getFirstMatch(RecipeType<T> type, C inventory, World world) {
        return this.getAllOfType(type)
                   .values()
                   .stream()
                   .parallel()
                   .flatMap(recipe -> Util.stream(type.match(recipe, world, inventory)))
                   .findFirst();
    }

}
