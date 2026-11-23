package com.mythicrealms.client;

import com.mythicrealms.entity.UnicornEntity;
import net.minecraft.client.render.entity.AbstractHorseEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.util.Identifier;

public class UnicornRenderer extends AbstractHorseEntityRenderer<UnicornEntity, HorseEntityModel<UnicornEntity>> {
    private static final String[] NAMES = {
        "blanco", "celeste", "rosado", "verde", "azul", "amarillo", "violeta", "rojo"
    };
    private static final Identifier[] TEXTURES = new Identifier[NAMES.length];
    static {
        for (int i = 0; i < NAMES.length; i++) {
            TEXTURES[i] = Identifier.of("mythicrealms", "textures/entity/unicornio_" + NAMES[i] + ".png");
        }
    }

    public UnicornRenderer(EntityRendererFactory.Context context) {
        super(context, new HorseEntityModel<>(context.getPart(MythicRealmsClient.UNICORN_LAYER)), 1.0f);
    }

    @Override
    public Identifier getTexture(UnicornEntity entity) {
        int variant = entity.getCoat();
        return TEXTURES[Math.max(0, Math.min(variant, TEXTURES.length - 1))];
    }
}
