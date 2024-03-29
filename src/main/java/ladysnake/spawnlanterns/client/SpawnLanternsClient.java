package ladysnake.spawnlanterns.client;

import ladysnake.spawnlanterns.common.SpawnLanterns;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class SpawnLanternsClient implements ClientModInitializer {

    public static void registerRenders() {
        BlockRenderLayerMap.INSTANCE.putBlock(SpawnLanterns.SOOTHING_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SpawnLanterns.CRYING_LANTERN, RenderLayer.getCutout());
    }

    @Override
    public void onInitializeClient() {
        registerRenders();
    }
}
