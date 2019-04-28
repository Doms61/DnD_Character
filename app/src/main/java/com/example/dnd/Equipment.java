package com.example.dnd;

import java.io.Serializable;

public class Equipment implements Serializable {

    String name;
    String type;
    String dmg;
    String dmgType;
    String def;
    String desc;

    public Equipment(String name, String type, String dmg, String dmgType, String def, String desc) {
        this.name = name;
        this.type = type;
        this.dmg = dmg;
        this.dmgType = dmgType;
        this.def = def;
        this.desc = desc;
    }

    public Equipment() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDmg() {
        return dmg;
    }

    public void setDmg(String dmg) {
        this.dmg = dmg;
    }

    public String getDmgType() {
        return dmgType;
    }

    public void setDmgType(String dmgType) {
        this.dmgType = dmgType;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
