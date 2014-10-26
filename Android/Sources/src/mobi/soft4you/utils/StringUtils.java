package mobi.soft4you.utils;

import android.text.TextUtils;

public final class StringUtils
{
    public static final String Empty = "";

    public static byte[] convertToBytes(String source)
    {
        return source.getBytes();
    }

    public static boolean isNullOrEmpty(String ver)
    {
        return TextUtils.isEmpty(ver);
    }
}