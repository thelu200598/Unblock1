package com.mygdx.game.dto;

import com.badlogic.gdx.utils.IntMap;

import java.util.HashMap;
import java.util.Map;

public class PackDT {

    public int    id;
    public String packName;
    public String fileName;

    private PackDT() {}

    public static PackDT of(int id, String packName, String fileName) {
        PackDT dto = new PackDT();
        dto.id    = id;
        dto.packName   = packName;
        dto.fileName = fileName;
        return dto;
    }

    public static Map<Integer, PackDT> packs;

    static {
        packs = new HashMap<>();
        packs.put(1, PackDT.of(1, "Beginner",       "Beginner"));
        packs.put(2, PackDT.of(2, "Easy",           "Easy"));
        packs.put(3, PackDT.of(3, "Relax",          "Relax"));
        packs.put(4, PackDT.of(4, "Challenge",      "Challenge"));


    }
}
