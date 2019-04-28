package com.example.dnd;

public class Character {

    private String name;
    private String char_class;
    private String race;
    private String level;
    private String alignment;
    private String initiative;
    private String str_value;
    private String str_mod;
    private String dex_value;
    private String dex_mod;
    private String con_value;
    private String con_mod;
    private String int_value;
    private String int_mod;
    private String wis_value;
    private String wis_mod;
    private String char_value;
    private String char_mod;
    private String max_hp;
    private String current_hp;
    private String max_mana;
    private String current_mana;
    private String armor_class;
    private String speed;
    private String proficiency;

    public Character() {}

    public Character(String name, String char_class, String race, String level, String alignment, String initiative, String str_value, String str_mod, String dex_value, String dex_mod, String con_value, String con_mod, String int_value, String int_mod, String wis_value, String wis_mod, String char_value, String char_mod, String max_hp, String current_hp, String max_mana, String current_mana, String armor_class, String speed, String proficiency) {
        this.name = name;
        this.char_class = char_class;
        this.race = race;
        this.level = level;
        this.alignment = alignment;
        this.initiative = initiative;
        this.str_value = str_value;
        this.str_mod = str_mod;
        this.dex_value = dex_value;
        this.dex_mod = dex_mod;
        this.con_value = con_value;
        this.con_mod = con_mod;
        this.int_value = int_value;
        this.int_mod = int_mod;
        this.wis_value = wis_value;
        this.wis_mod = wis_mod;
        this.char_value = char_value;
        this.char_mod = char_mod;
        this.max_hp = max_hp;
        this.current_hp = current_hp;
        this.max_mana = max_mana;
        this.current_mana = current_mana;
        this.armor_class = armor_class;
        this.speed = speed;
        this.proficiency = proficiency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChar_class() {
        return char_class;
    }

    public void setChar_class(String char_class) {
        this.char_class = char_class;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getInitiative() {
        return initiative;
    }

    public void setInitiative(String initiative) {
        this.initiative = initiative;
    }

    public String getStr_value() {
        return str_value;
    }

    public void setStr_value(String str_value) {
        this.str_value = str_value;
    }

    public String getStr_mod() {
        return str_mod;
    }

    public void setStr_mod(String str_mod) {
        this.str_mod = str_mod;
    }

    public String getDex_value() {
        return dex_value;
    }

    public void setDex_value(String dex_value) {
        this.dex_value = dex_value;
    }

    public String getDex_mod() {
        return dex_mod;
    }

    public void setDex_mod(String dex_mod) {
        this.dex_mod = dex_mod;
    }

    public String getCon_value() {
        return con_value;
    }

    public void setCon_value(String con_value) {
        this.con_value = con_value;
    }

    public String getCon_mod() {
        return con_mod;
    }

    public void setCon_mod(String con_mod) {
        this.con_mod = con_mod;
    }

    public String getInt_value() {
        return int_value;
    }

    public void setInt_value(String int_value) {
        this.int_value = int_value;
    }

    public String getInt_mod() {
        return int_mod;
    }

    public void setInt_mod(String int_mod) {
        this.int_mod = int_mod;
    }

    public String getWis_value() {
        return wis_value;
    }

    public void setWis_value(String wis_value) {
        this.wis_value = wis_value;
    }

    public String getWis_mod() {
        return wis_mod;
    }

    public void setWis_mod(String wis_mod) {
        this.wis_mod = wis_mod;
    }

    public String getChar_value() {
        return char_value;
    }

    public void setChar_value(String char_value) {
        this.char_value = char_value;
    }

    public String getChar_mod() {
        return char_mod;
    }

    public void setChar_mod(String char_mod) {
        this.char_mod = char_mod;
    }

    public String getMax_hp() {
        return max_hp;
    }

    public void setMax_hp(String max_hp) {
        this.max_hp = max_hp;
    }

    public String getCurrent_hp() {
        return current_hp;
    }

    public void setCurrent_hp(String current_hp) {
        this.current_hp = current_hp;
    }

    public String getMax_mana() {
        return max_mana;
    }

    public void setMax_mana(String max_mana) {
        this.max_mana = max_mana;
    }

    public String getCurrent_mana() {
        return current_mana;
    }

    public void setCurrent_mana(String current_mana) {
        this.current_mana = current_mana;
    }

    public String getArmor_class() {
        return armor_class;
    }

    public void setArmor_class(String armor_class) {
        this.armor_class = armor_class;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getProficiency() {return  proficiency;}

    public void setProficiency(String proficiency) {this.proficiency = proficiency;}
}
