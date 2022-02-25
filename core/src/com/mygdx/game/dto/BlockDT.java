package com.mygdx.game.dto;

import java.util.HashMap;
import java.util.Map;

public class BlockDT {
    public int id;
    public String name;
    public String  region;

    public static BlockDT blockDT(int id, String name, String region){
        BlockDT b = new BlockDT();
        b.id = id;
        b.name = name;
        b.region = region;
        return b;
    }

    public static Map<Integer, BlockDT> blocks;

    static {
        blocks = new HashMap<>();
        blocks.put(0, BlockDT.blockDT   (1,     "icon_p1",     "icon_p1"));
        blocks.put(1, BlockDT.blockDT   (2,     "icon_p2",     "icon_p2"));
        blocks.put(2, BlockDT.blockDT   (3,     "icon_p3",     "icon_p3"));
        blocks.put(3, BlockDT.blockDT   (4,     "icon_p4",     "icon_p4"));
        blocks.put(4, BlockDT.blockDT   (5,     "icon_p5",     "icon_p5"));
        blocks.put(5, BlockDT.blockDT   (6,     "icon_p6",     "icon_p6"));
        blocks.put(6, BlockDT.blockDT   (7,     "icon_p7",     "icon_p7"));
        blocks.put(7, BlockDT.blockDT   (8,     "icon_p8",     "icon_p8"));
        blocks.put(8, BlockDT.blockDT   (9,     "icon_p9",     "icon_p9"));
        blocks.put(9, BlockDT.blockDT   (10,    "icon_p10",   "icon_p10"));
        blocks.put(10, BlockDT.blockDT  (11,     "icon_p11",    "icon_p11"));
        blocks.put(11, BlockDT.blockDT  (12,     "icon_p12",    "icon_p12"));
        blocks.put(12, BlockDT.blockDT  (13,     "icon_p13",    "icon_p13"));
        blocks.put(13, BlockDT.blockDT  (14,     "icon_p14",    "icon_p14"));
        blocks.put(14, BlockDT.blockDT  (15,     "icon_p15",    "icon_p15"));
        blocks.put(15, BlockDT.blockDT  (16,     "icon_p16",    "icon_p16"));
        blocks.put(16, BlockDT.blockDT  (17,     "icon_p17",    "icon_p17"));
        blocks.put(17, BlockDT.blockDT  (18,     "icon_p18",    "icon_p18"));
        blocks.put(18, BlockDT.blockDT  (19,     "icon_p19",    "icon_p19"));
        blocks.put(19, BlockDT.blockDT  (20,     "icon_p20",    "icon_p20"));
    }
}
