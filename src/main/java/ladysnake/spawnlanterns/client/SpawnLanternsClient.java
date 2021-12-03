package ladysnake.spawnlanterns.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SpawnLanternsClient implements ClientModInitializer {

    public static void registerRenders() {
//        BlockRenderLayerMap.INSTANCE.putBlock(SpawnLanterns.SOOTHING_LANTERN, RenderLayer.getCutout());
//        BlockRenderLayerMap.INSTANCE.putBlock(SpawnLanterns.CRYING_LANTERN, RenderLayer.getCutout());
    }

    @Override
    public void onInitializeClient() {
        registerRenders();
    }
}
