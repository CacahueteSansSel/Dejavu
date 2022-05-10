package io.cacahuetedev.dejavu.item;

import io.cacahuetedev.dejavu.entity.CardReaderBlockEntity;

public class CardItem extends DejavuItem {
    CardReaderBlockEntity.Level accessLevel;

    public CardItem(CardReaderBlockEntity.Level accessLevel, Settings settings) {
        super(settings);
        this.accessLevel = accessLevel;
    }

    public CardReaderBlockEntity.Level getAccessLevel() {
        return accessLevel;
    }
}
