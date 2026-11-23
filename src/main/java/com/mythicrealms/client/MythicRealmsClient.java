package com.mythicrealms.client;

import com.mythicrealms.MythicRealms;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.util.Identifier;

public class MythicRealmsClient implements ClientModInitializer {
    public static final EntityModelLayer UNICORN_LAYER = new EntityModelLayer(
        Identifier.of(MythicRealms.MOD_ID, "unicornio"), "main");

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(UNICORN_LAYER, () -> {
            ModelData data = HorseEntityModel.getModelData(Dilation.NONE);
            // El cuerno forma parte del modelo y acompaña los movimientos de la cabeza.
            data.getRoot().getChild("head_parts").addChild("mythic_horn",
                ModelPartBuilder.create().uv(0, 0).cuboid(-1.0f, -16.0f, -9.0f, 2.0f, 9.0f, 2.0f),
                ModelTransform.NONE);
            return TexturedModelData.of(data, 64, 64);
        });
        EntityRendererRegistry.register(MythicRealms.UNICORN, UnicornRenderer::new);
    }
}
