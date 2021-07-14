package com.mialab.healthbutler.manager.util;

import java.awt.image.BufferedImage;

import com.google.code.kaptcha.GimpyEngine;

public class NoDistortion implements GimpyEngine {
    @Override
    public BufferedImage getDistortedImage(BufferedImage bi) {
            return bi;
    }
}
