package com.github.commonlib.utils;

import java.io.Closeable;
import java.io.IOException;

public final class CloseUtils {
    /**
     * 关闭一个Closeable
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
