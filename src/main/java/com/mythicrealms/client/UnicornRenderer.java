package com.mythicrealms.client;

import com.mythicrealms.entity.UnicornEntity;
import net.minecraft.client.render.entity.AbstractHorseEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.util.Identifier;

public class UnicornRenderer extends AbstractHorseEntityRenderer<UnicornEntity, HorseEntityModel<UnicornEntity>> {
    private static final Identifier TEXTURE = Identifier.of("mythicrealms", "textures/entity/unicornio.png");

    public UnicornRenderer(EntityRendererFactory.Context context) {
        super(context, new HorseEntityModel<>(context.getPart(MythicRealmsClient.UNICORN_LAYER)), 1.0f);
    }

    @Override
    public Identifier getTexture(UnicornEntity entity) {
        return TEXTURE;
    }
}
