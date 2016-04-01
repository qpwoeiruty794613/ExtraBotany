package com.meteor.extrabotany.common.lexicon;

import com.meteor.extrabotany.common.ExtraBotany;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.common.lexicon.BLexiconEntry;

public class ELexiconEntry extends BLexiconEntry{
	
	public ELexiconEntry(String unlocalizedName, LexiconCategory category) {
		super(unlocalizedName, category);
		setKnowledgeType(ExtraBotany.extraKnowledge);
	}

}
