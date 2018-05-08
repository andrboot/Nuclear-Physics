package org.halvors.nuclearphysics.common.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.halvors.nuclearphysics.api.recipe.QuantumAssemblerRecipes;
import org.halvors.nuclearphysics.common.ConfigurationManager.General;

public class ModRecipes {
    public static void registerRecipes() {
        if (General.allowGeneratedQuantumAssemblerRecipes) {
            final String[] prefixList = { "ore", "ingot", "nugget", "dust", "gem", "dye", "block", "stone", "crop", "slab", "stair", "pane", "gear", "rod", "stick", "plate", "dustTiny", "cover" };

            // Add common items and blocks from ore dictionary.
            for (String oreName : OreDictionary.getOreNames()) {
                for (String prefix : prefixList) {
                    if (oreName.startsWith(prefix)) {
                        for (ItemStack itemStack : OreDictionary.getOres(oreName)) {
                            QuantumAssemblerRecipes.addRecipe(itemStack);
                        }
                    }
                }
            }

            // Add RECIPES for all items in this mod.
            for (Item item : ModItems.items) {
                QuantumAssemblerRecipes.addRecipe(new ItemStack(item));
            }

            // Add RECIPES for all blocks in this mod.
            for (ItemBlock itemBlock : ModBlocks.itemBlocks) {
                QuantumAssemblerRecipes.addRecipe(new ItemStack(itemBlock));
            }
        }
    }
}
