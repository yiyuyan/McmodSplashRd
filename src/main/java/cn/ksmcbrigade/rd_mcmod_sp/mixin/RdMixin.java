package cn.ksmcbrigade.rd_mcmod_sp.mixin;

import cn.ksmcbrigade.rd_mcmod_sp.SplashUtils;
import com.mojang.rubydung.RubyDung;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RubyDung.class,remap = false)
public class RdMixin {

	@Unique
	private String titleSplash = null;
	@Unique
	private boolean gotSplash = false;
	@Unique
	private boolean set = false;

	@Inject(method = "init",at = @At(value = "TAIL"))
	public void init(CallbackInfo ci) {
		new Thread(() -> {
			titleSplash = Display.getTitle() + " - " + new SplashUtils().getOnlineSplash();
			gotSplash = true;
		}).start();
	}

	@Inject(method = "render",at = @At("TAIL"))
	public void renderTick(float a, CallbackInfo ci){
		if(!set && gotSplash && titleSplash!=null){
			Display.setTitle(titleSplash);
			set = true;
		}
	}
}
