package com.jagex.runescape.rs2rsc;

public class RSCConfig {
    public static boolean rscProtocol = true;

    public static void Start()
    {
        if (!rscProtocol)
            return;

        System.out.println("using rsc protocol");
    }
}
