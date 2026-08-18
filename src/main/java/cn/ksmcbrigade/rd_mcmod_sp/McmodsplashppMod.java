package cn.ksmcbrigade.rd_mcmod_sp;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class McmodsplashppMod implements ModInitializer {
   public static final Logger LOGGER = LogManager.getLogger(McmodsplashppMod.class.getSimpleName());

   @Override
   public void onInitialize() {
	   LOGGER.info("Welcome to Fabric rd-132211! MCMod Splashes");
   }
}
