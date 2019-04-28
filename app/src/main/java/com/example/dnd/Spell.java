package com.example.dnd;

import java.io.Serializable;

public class Spell implements Serializable {

    public String spellName;
    public String spellMana;
    public String spellDuration;
    public String spellCastTime;
    public String spellRange;
    public String spellComponents;
    public String spellEffect;
    public String spellDescription;

    public Spell(String spellName, String spellMana, String spellDuration, String spellCastTime, String spellRange, String spellComponents, String spellEffect, String spellDescription) {
        this.spellName = spellName;
        this.spellMana = spellMana;
        this.spellDuration = spellDuration;
        this.spellCastTime = spellCastTime;
        this.spellRange = spellRange;
        this.spellComponents = spellComponents;
        this.spellEffect = spellEffect;
        this.spellDescription = spellDescription;
    }


    public Spell(String spellName) {
        this.spellName = spellName;
    }

    public Spell () {}

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public String getSpellMana() {
        return spellMana;
    }

    public void setSpellMana(String spellMana) {
        this.spellMana = spellMana;
    }

    public String getSpellDuration() {
        return spellDuration;
    }

    public void setSpellDuration(String spellDuration) {
        this.spellDuration = spellDuration;
    }

    public String getSpellCastTime() {
        return spellCastTime;
    }

    public void setSpellCastTime(String spellCastTime) {
        this.spellCastTime = spellCastTime;
    }

    public String getSpellRange() {
        return spellRange;
    }

    public void setSpellRange(String spellRange) {
        this.spellRange = spellRange;
    }

    public String getSpellComponents() {
        return spellComponents;
    }

    public void setSpellComponents(String spellComponents) {
        this.spellComponents = spellComponents;
    }

    public String getSpellEffect() {
        return spellEffect;
    }

    public void setSpellEffect(String spellEffect) {
        this.spellEffect = spellEffect;
    }

    public String getSpellDescription() {
        return spellDescription;
    }

    public void setSpellDescription(String spellDescription) {
        this.spellDescription = spellDescription;
    }
}
