package com.meteor.extrabotany.common.lib;

import net.minecraft.util.ResourceLocation;

public class LibReference {
	
	public static final String MOD_ID = "ExtraBotany";
	public static final String MOD_NAME = MOD_ID;
	public static final String BUILD = "GRADLE:BUILD";
	public static final String VERSION = "GRADLE:VERSION-" + BUILD;
	public static final String DEPENDENCIES = "required-after:Botania";
	
	public static final String PROXY_COMMON = "com.meteor.extrabotany.common.proxy.CommonProxy";
	public static final String PROXY_CLIENT = "com.meteor.extrabotany.client.proxy.ClientProxy";
	
	public static final String MODEL_HESTIACHASTITY = "extrabotany:textures/models/armor/armorrelic.png";
	public static final String MODEL_RELIC = "extrabotany:textures/models/armor/armorrelic0.png";
	public static final ResourceLocation POTION_SLOWPARTICLESORTING = new ResourceLocation("extrabotany","textures/icons/slowparticlesorting.png");
	public static final ResourceLocation POTION_FASTPARTICLESORTING = new ResourceLocation("extrabotany","textures/icons/fastparticlesorting.png");
	
}
