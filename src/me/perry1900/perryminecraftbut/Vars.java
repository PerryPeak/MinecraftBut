package me.perry1900.perryminecraftbut;

import me.perry1900.perryminecraftbut.gamemodifiers.GameModifier;

import java.util.HashMap;
import java.util.Map;

public class Vars {
    private Map<String, GameModifier> gameModifiers = new HashMap();
    private int maxhealth;
    private ModifierGUI modifiergui;

    public Vars(PerryMinecraftBut main) {
        modifiergui = new ModifierGUI(main);
    }

    public Map<String, GameModifier> getGameModifiers() {
        return gameModifiers;
    }

    public void setGameModifiers(Map<String, GameModifier> newGameModifiers) {
        gameModifiers = newGameModifiers;
    }

    public void setMaxhealth(int newMaxHealth) {
        maxhealth = newMaxHealth;
    }

    public int getMaxhealth() {
        return maxhealth;
    }

    public ModifierGUI getModifierGUI() {
        return modifiergui;
    }
}
