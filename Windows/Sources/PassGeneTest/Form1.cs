using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Security.Cryptography;
using System.Numerics;

namespace WindowsFormsApplication1
{
    public partial class Form1 :Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnGenerate_Click(object sender, EventArgs e)
        {
            String master = teMastrPassword.Text;
            String site = teSite.Text;
            String login = teLogin.Text;

            int version = Convert.ToInt32(seVersion.Value);
            int passwordLength = Convert.ToInt32(sePasswordLength.Value);

            RIPEMD160 hashRIPEMD160 = RIPEMD160Managed.Create();
            SHA512 hashSHA512 = SHA512Managed.Create();
            String password;
            try
            {

                //byte[] t1 = hashRIPEMD160.ComputeHash(new byte[] { 1, 2, 3 });
                //String st1 = hexString(t1);
                ////79f901da2609f020adadbf2e5f68a16c8c3f7d57
                //byte[] t2 = hashSHA512.ComputeHash(new byte[] { 1, 2, 3 });
                //String st2 = hexString(t2);
                ////27864cc5219a951a7a6e52b8c8dddf6981d098da1658d96258c870b2c88dfbcb51841aea172a28bafa6a79731165584677066045c959ed0f9929688d04defc29
                byte[] bytes = Encoding.UTF8.GetBytes(expandVer(version));
                String tt1 = hexString(hashRIPEMD160.ComputeHash(bytes));
                String tt2 = hexString(hashSHA512.ComputeHash(Encoding.UTF8.GetBytes(site)));

                byte[] hashedPassword = hashSHA512.ComputeHash(Encoding.UTF8.GetBytes(passwordLength + hexString(hashRIPEMD160.ComputeHash(Encoding.UTF8.GetBytes(expandVer(version) +
                                                                                 hexString(hashSHA512.ComputeHash(Encoding.UTF8.GetBytes(site +
                                                                                                    hexString(hashRIPEMD160.ComputeHash(Encoding.UTF8.GetBytes(login +
                                                                                                                       hexString(hashSHA512.ComputeHash(Encoding.UTF8.GetBytes(master)))
                                                                                                                       )))
                                                                                                   )))
                                                                                )))));
                    
                    byte[] hashedPasswordWOFirstByte = new byte[hashedPassword.Length-2];

                    String ttt = hashRIPEMD160.OutputBlockSize.ToString();
                    Array.Copy(hashedPassword, 1, hashedPasswordWOFirstByte, 0, hashedPasswordWOFirstByte.Length);
                    Array.Reverse(hashedPasswordWOFirstByte);
                    BigInteger bi = new BigInteger( hashedPasswordWOFirstByte);
                    bi = BigInteger.Abs( bi );
                    
                    List<Int32> basesOfBigNum = new List<Int32>();
                    
                    String baseSring = cbSymbolsSet.Text; //"abcdefghijklmnopqrstuvwxyz1234567890~!@#$%^&*()_+-=";
                    BigInteger basement = new BigInteger(baseSring.Length);
                    
                    BigInteger tmp = bi;
                    BigInteger mod = bi;
                    
                    while(tmp.CompareTo(BigInteger.Zero) != 0)
                    {
                        mod = BigInteger.ModPow( tmp, 1, basement);
                        tmp = BigInteger.Divide( tmp, basement);
                        basesOfBigNum.Insert(0, (Int32)mod);
                    }
                    
                    if (basesOfBigNum.Count == 0)
                        basesOfBigNum.Add(0);
                    
                    StringBuilder result = new StringBuilder();
                    
                    for(int i =0; i< basesOfBigNum.Count; i++)
                        result.Append(baseSring[basesOfBigNum[i]]);
                    
                    password = result.ToString();
                }
                catch (Exception ex)
                {
                    password = ex.Message;
                }

            tePassword.Text = password.Substring(0, Math.Min(password.Length, passwordLength));
            }
        

        String expandVer(int ver)
        {
            if (ver <= 0)
                return "0";
            else
            {
                try
                {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < ver; i++)
                        sb.Append(i.ToString());

                    return sb.ToString();
                }
                catch (Exception e)
                {
                    return "0";
                }
            }
        }

        private static String[] hexChars = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

        public static String hexString(byte[] bytes)
        {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.Length; i++)
            {
                sb.Append(hexChars[(bytes[i] >> 4) & 0x0F]).Append(hexChars[(bytes[i]) & 0x0F]);
            }

            return sb.ToString();
        }

        private void button1_MouseDown(object sender, MouseEventArgs e)
        {
            teMastrPassword.PasswordChar = char.MinValue;
        }

        private void button1_MouseUp(object sender, MouseEventArgs e)
        {
             teMastrPassword.PasswordChar = '*';
        }

    }
}
