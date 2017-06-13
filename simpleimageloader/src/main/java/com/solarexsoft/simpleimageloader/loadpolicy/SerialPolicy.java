package com.solarexsoft.simpleimageloader.loadpolicy;

import com.solarexsoft.simpleimageloader.core.BitmapRequest;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public class SerialPolicy implements LoadPolicy {
    @Override
    public int compareTo(BitmapRequest o1, BitmapRequest o2) {
        return o2.getSerialNum() - o1.getSerialNum();
    }
}
