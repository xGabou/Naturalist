package com.starfish_studios.naturalist.common.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.starfish_studios.naturalist.core.registry.NaturalistRecipes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record BugNetInteractionRecipe(ResourceLocation id, EntityType<?> entityType, ItemStack dropStack) implements Recipe<Container> {

    @Override
    public boolean matches(Container container, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return dropStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return dropStack;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NaturalistRecipes.BUG_NET_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return NaturalistRecipes.BUG_NET;
    }

    public static class Serializer implements RecipeSerializer<BugNetInteractionRecipe> {
        @Override
        public @NotNull BugNetInteractionRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject serializedRecipe) {
            JsonObject resultObject = GsonHelper.getAsJsonObject(serializedRecipe, "result");

            ItemStack output = ShapedRecipe.itemStackFromJson(resultObject);
            if (output.isEmpty()) {
                throw new IllegalArgumentException("Invalid or empty output ItemStack for recipe " + recipeId);
            }

            String entityTypeId = GsonHelper.getAsString(serializedRecipe, "entity_type");
            if (entityTypeId.isEmpty()) {
                throw new JsonSyntaxException("Missing or invalid 'entity_type' in recipe: " + recipeId);
            }

            EntityType<?> entityType = BuiltInRegistries.ENTITY_TYPE.get(new ResourceLocation(entityTypeId));

            return new BugNetInteractionRecipe(recipeId, entityType, output);
        }

        @Override
        public BugNetInteractionRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            ItemStack output = buffer.readItem();
            EntityType<?> entityType = BuiltInRegistries.ENTITY_TYPE.get(buffer.readResourceLocation());

            return new BugNetInteractionRecipe(recipeId, entityType, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, BugNetInteractionRecipe recipe) {
            buffer.writeItem(recipe.dropStack);
            buffer.writeResourceLocation(BuiltInRegistries.ENTITY_TYPE.getKey(recipe.entityType));
        }
    }
}