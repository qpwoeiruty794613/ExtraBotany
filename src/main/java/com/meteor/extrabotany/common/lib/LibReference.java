package com.meteor.extrabotany.common.lib;

import net.minecraft.util.ResourceLocation;

public class LibReference {	
	public static final String MOD_ID = "ExtraBotany";
	public static final String MOD_NAME = MOD_ID;
	public static final String PREFIX_MOD = "extrabotany:";
	public static final String BUILD = "GRADLE:BUILD";
	public static final String VERSION = "GRADLE:VERSION-" + BUILD;
	public static final String DEPENDENCIES = "required-after:Botania;after:Baubles";
	
	public static final String PROXY_COMMON = "com.meteor.extrabotany.common.CommonProxy";
	public static final String PROXY_CLIENT = "com.meteor.extrabotany.client.ClientProxy";
	
	public static final String CATEGORY_CLIENT = "client";
	public static final String CATEGORY_COMMON = "common";
	public static final String CATEGORY_ID = "ids";
	public static final String PREFIX_CONFIG = "config.ExtraBotany.";
	
	public static final String MODEL_HESTIACHASTITY = "extrabotany:textures/models/armor/armorrelic.png";
	public static final String MODEL_RELIC = "extrabotany:textures/models/armor/armorrelic0.png";
	
	public static final ResourceLocation POTION_SLOWPARTICLESORTING = new ResourceLocation("extrabotany","textures/icons/slowparticlesorting.png");
	public static final ResourceLocation POTION_FASTPARTICLESORTING = new ResourceLocation("extrabotany","textures/icons/fastparticlesorting.png");
	public static final ResourceLocation POTION_RESIDUALPAIN = new ResourceLocation("extrabotany","textures/icons/residualpain.png");
	public static final ResourceLocation MODEL_POOLGAIA = new ResourceLocation("extrabotany","textures/models/poolgaia.png");
	public static final ResourceLocation BAR_SHIELD = new ResourceLocation("extrabotany","textures/gui/shieldHud.png");
	public static final ResourceLocation SHIELD = new ResourceLocation("extrabotany","textures/icons/shield.png");
	public static final ResourceLocation ATHENA = new ResourceLocation("extrabotany","textures/icons/athena.png");
}
