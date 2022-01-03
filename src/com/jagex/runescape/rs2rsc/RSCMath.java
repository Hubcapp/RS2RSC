package com.jagex.runescape.rs2rsc;

public class RSCMath {
    public static double combatFormulaRSC(int attack, int strength, int defence, int hitpoints, int prayer, int ranged, int magic)
    {
        double combatLevel = ((defence + hitpoints) * 0.25) + ((magic + prayer) * 0.125);
        double warrior = (attack + strength) * 0.25;
        double ranger = ranged * 0.375;
        return (combatLevel + (((ranged * 1.5) > (attack + strength)) ? ranger : warrior));
    }
}
