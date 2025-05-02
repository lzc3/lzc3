package com.imageTrans;

import com.github.hui.quick.plugin.image.helper.ImgPixelHelper;
import com.github.hui.quick.plugin.image.wrapper.pixel.ImgPixelOptions;
import com.github.hui.quick.plugin.image.wrapper.pixel.context.PixelContextHolder;
import com.github.hui.quick.plugin.image.wrapper.pixel.model.IPixelStyle;

import java.awt.*;

import static com.github.hui.quick.plugin.image.wrapper.pixel.model.PixelStyleEnum.CHAR_BLACK;

public enum CusPixelStyleEnum implements IPixelStyle {
    CUS_CHAR_BLACK {
        public Color calculateColor(int i, int i1, int i2, int i3) {
            return CHAR_BLACK.calculateColor(i, i1, i2, i3);
        }

        @Override
        public void draw(Graphics2D g2d, ImgPixelOptions options, int x, int y) {
            char ch = ImgPixelHelper.toChar(options.getChars(), g2d.getColor());
            if (g2d.getFont() == null || g2d.getFont().getSize() != options.getBlockSize()) {
                g2d.setFont(options.getFont());
            }
            PixelContextHolder.addChar(y, ch);

            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(ch), x, y);
        }
    }

    ;
    private CusPixelStyleEnum(){};
}
