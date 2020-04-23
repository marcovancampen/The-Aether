package com.aether.client;

import com.aether.CommonProxy;
import com.aether.block.AetherBlocks;
import com.aether.client.gui.screen.inventory.EnchanterScreen;
import com.aether.client.renderer.entity.FloatingBlockRenderer;
import com.aether.client.renderer.entity.MimicRenderer;
import com.aether.entity.item.FloatingBlockEntity;
import com.aether.entity.monster.MimicEntity;
import com.aether.inventory.container.AetherContainerTypes;
import com.aether.item.TintedBlockItem;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy {
	
	@Override
	public void commonSetup(FMLCommonSetupEvent event) {
		super.commonSetup(event);
		registerColors();
	}
	
	@Override
	public void clientSetup(FMLClientSetupEvent event) {
		super.clientSetup(event);
		registerEntityRenderers();
		registerGuiFactories();
	}
	
	private void registerEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(FloatingBlockEntity.class, FloatingBlockRenderer::new);	
		RenderingRegistry.registerEntityRenderingHandler(MimicEntity.class, MimicRenderer::new);
	}
	
	private void registerGuiFactories() {
		ScreenManager.registerFactory(AetherContainerTypes.ENCHANTER, EnchanterScreen::new);
	}
	
	private void registerColors() {
		// Block colors
		registerColor(AetherBlocks.BLUE_AERCLOUD);
		registerColor(AetherBlocks.GOLDEN_AERCLOUD);
		
		// Item colors
		registerColor((TintedBlockItem) AetherBlocks.BLUE_AERCLOUD.asItem());
		registerColor((TintedBlockItem) AetherBlocks.GOLDEN_AERCLOUD.asItem());
	}
	
	private static <B extends Block & IBlockColor> void registerColor(B block) {
		Minecraft.getInstance().getBlockColors().register(block, block);
	}
	
	private static <I extends Item & IItemColor> void registerColor(I item) {
		Minecraft.getInstance().getItemColors().register(item, item);
	}
	
}