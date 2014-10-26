package mobi.soft4you.utils;

import java.security.MessageDigest;
import org.spongycastle.crypto.digests.*;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class CriptoUtils
{
    public static byte[] hashSHA512(byte[] source) throws NoSuchProviderException, NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        return md.digest(source);
    }
    
    public static byte[] hashSHA512(String source) throws NoSuchProviderException, NoSuchAlgorithmException
    {
        return hashSHA512(StringUtils.convertToBytes(source));
    }
    
    public static byte[] hashRIPEMD160(byte[] source) throws NoSuchProviderException, NoSuchAlgorithmException
    {
        
        byte[] out = new byte[20];
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(source,0,source.length);
        digest.doFinal(out,0);
//       MessageDigest md = MessageDigest.getInstance("RIPEMD-160");
//        return md.digest(source);
        return out;
    }
    
    public static byte[] hashRIPEMD160(String source) throws NoSuchProviderException, NoSuchAlgorithmException
    {
        return hashRIPEMD160(StringUtils.convertToBytes(source));
    }
}
