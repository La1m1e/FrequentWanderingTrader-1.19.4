package la1m1e.wanderingtrader.mixin;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.WanderingTraderManager;
import net.minecraft.world.level.ServerWorldProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WanderingTraderManager.class)
public class WanderingTraderManageMixin {
	@Shadow private int spawnDelay;

	@Inject(method = "spawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;getGameRules()Lnet/minecraft/world/GameRules;", ordinal = 1))
	private void reduceSpawnCooldown(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals, CallbackInfoReturnable<Integer> cir) {
		spawnDelay = 100;
	}

	@Inject(method = "<init>", at = @At(value = "INVOKE", target  = "Lnet/minecraft/world/level/ServerWorldProperties;setWanderingTraderSpawnDelay(I)V"))
	private void setSpawnDelay(ServerWorldProperties properties, CallbackInfo ci) {
		spawnDelay = 100;
	}
}