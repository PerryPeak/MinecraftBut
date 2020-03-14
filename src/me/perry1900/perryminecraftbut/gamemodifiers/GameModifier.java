package me.perry1900.perryminecraftbut.gamemodifiers;

public abstract class GameModifier {
    boolean toggled = false;

    public abstract void toggle();

    public boolean isTogged() {
        return toggled;
    }
}
