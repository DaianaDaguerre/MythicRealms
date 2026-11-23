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
            // El frente del caballo es -Z. z=-9 era el HOCICO; z=-2.5 es la frente.
            // Unido a head_parts para seguir las animaciones de la cabeza (Minecraft 1.21.1).
            data.getRoot().getChild("head_parts").addChild("mythic_horn",
                ModelPartBuilder.create()
                    .uv(56, 0).cuboid(-1.0f, -15.0f, -2.5f, 2.0f, 5.0f, 2.0f)
                    .uv(56, 10).cuboid(-0.75f, -19.0f, -2.25f, 1.5f, 4.0f, 1.5f)
                    .uv(56, 17).cuboid(-0.4f, -22.0f, -2.0f, 0.8f, 3.0f, 0.8f),
                ModelTransform.NONE);
            return TexturedModelData.of(data, 64, 64);
        });
        EntityRendererRegistry.register(MythicRealms.UNICORN, UnicornRenderer::new);
    }
}
