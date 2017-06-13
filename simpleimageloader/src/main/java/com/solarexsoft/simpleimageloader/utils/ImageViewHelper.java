package com.solarexsoft.simpleimageloader.utils;

import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public class ImageViewHelper {
    private static int DEFAULT_WIDTH = 200;
    private static int DEFAULT_HEIGHT = 200;

    public static int getImageViewWidth(ImageView imageView) {
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            int width = 0;
            if (layoutParams != null && layoutParams.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
                width = imageView.getWidth();
            }
            if (width <= 0 && layoutParams != null) {
                width = layoutParams.width;
            }
            if (width <= 0) {
                width = getImageViewFieldValue(imageView, "mMaxWidth");
            }
            if (width > 0) {
                return width;
            } else {
                return DEFAULT_WIDTH;
            }
        }
        return DEFAULT_WIDTH;
    }

    public static int getImageViewHeight(ImageView imageView) {
        if (imageView != null) {
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            int height = 0;
            if (params != null && params.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
                height = imageView.getHeight();
            }
            if (height <= 0 && params != null) {
                height = params.height;
            }

            if (height <= 0) {
                height = getImageViewFieldValue(imageView, "mMaxHeight");
            }
            if (height > 0) {
                return height;
            } else {
                return DEFAULT_HEIGHT;
            }
        }
        return DEFAULT_HEIGHT;
    }

    private static int getImageViewFieldValue(ImageView imageView, String fieldName) {
        try {
            Field field = ImageView.class.getField(fieldName);
            field.setAccessible(true);
            int value = (Integer) field.get(imageView);
            if (value > 0 && value < Integer.MAX_VALUE) {
                return value;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
