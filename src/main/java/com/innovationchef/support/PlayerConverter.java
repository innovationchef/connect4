package com.innovationchef.support;

import com.innovationchef.constant.Player;

import javax.persistence.AttributeConverter;

public class PlayerConverter implements AttributeConverter<Player, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Player player) {
        return player.val();
    }

    @Override
    public Player convertToEntityAttribute(Integer s) {
        return Player.from(s);
    }
}
