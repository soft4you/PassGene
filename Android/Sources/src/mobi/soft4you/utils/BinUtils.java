package mobi.soft4you.utils;

public class BinUtils
{
    private static String[] hexChars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String hexString(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < bytes.length; i++)
        {
            sb.append(hexChars[(bytes[i]>>4) & 0x0F]).append(hexChars[(bytes[i]) & 0x0F]);
        }
        
        return sb.toString();
    }
}
