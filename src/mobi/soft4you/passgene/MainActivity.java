package mobi.soft4you.passgene;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Vector;

import mobi.soft4you.utils.BinUtils;
import mobi.soft4you.utils.CriptoUtils;
import mobi.soft4you.utils.StringUtils;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends ActionBarActivity
{
    private static final String PREFERENCE_PARAM__VERSION = "version";
    private static final String PREFERENCE_PARAM__VERSION_INT = "version_int";
    private static final String PREFERENCE_PARAM__PASSWORD_LENGTH_INT = "password_length_int";
    private static final int PREFERENCE_ACTIVITY_CODE = 1;
    
    static final String[] password_symbol_sets={
       "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!.,?'\":;@#$%|^&()[]{}<>*_+-=/\\",
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890",
        "abcdefghijklmnopqrstuvwxyz1234567890"
    };
    
    TextView tvVersion;
    SeekBar sbVersion;
    EditText tePassword, teMasterPassword, teSite, teLogin;
    ToggleButton tbSite, tbLogin, tbRememberMainPassword;
    ImageButton ibViewMasterPassword;
    
    SeekBar sbPasswordLength;
    TextView tePasswordLengthView;
    
    TextView tvSysmbolsSetName;
    
    SharedPreferences mPreferences;
    
    android.content.DialogInterface.OnClickListener mOnClickSites = 
            new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    ListView lv = ((AlertDialog) dialog).getListView();
                    
                    if (which == Dialog.BUTTON_POSITIVE && lv.getCheckedItemPosition() != ListView.INVALID_POSITION)
                    {
                        disableSaveData = true;
                        try
                        {
                            int pos = lv.getCheckedItemPosition();
                            if (sites.moveToPosition(pos))
                            {
                                teSite.setText(sites.getString(1));
                                tbSite.setChecked(true);
                            }
                        }
                        finally
                        {
                            disableSaveData = false;
                        }
                    }
                }
            };
    android.content.DialogInterface.OnClickListener mOnClickLogins = 
            new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    ListView lv = ((AlertDialog) dialog).getListView();
                    
                    if (which == Dialog.BUTTON_POSITIVE && lv.getCheckedItemPosition() != ListView.INVALID_POSITION)
                    {
                        disableSaveData = true;
                        try
                        {
                            int pos = lv.getCheckedItemPosition();
                            if (logins.moveToPosition(pos))
                            {
                                teLogin.setText(logins.getString(1));
                                //teLogin.setText(lv.getItemAtPosition(lv.getCheckedItemPosition()).toString());
                                tbLogin.setChecked(true);
                            }
                        }
                        finally
                        {
                            disableSaveData = false;
                        }
                    }
                }
            };
    
    DB mDB;
    
    Cursor sites, logins;
    /**
     * Не сохранять в базе содержимое TextEdit'а
     */
    protected boolean disableSaveData;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mDB = new DB(this);
        mDB.open();
        
        sites = mDB.getSites();
        startManagingCursor(sites);
        logins = mDB.getLogins();
        startManagingCursor(logins);
        
        
        tvVersion = ((TextView) findViewById(R.id.tvVersion));
        sbVersion = (SeekBar)findViewById(R.id.sbVersion);
        teSite = ((EditText)findViewById(R.id.teSite));
        
        sbPasswordLength = (SeekBar)findViewById(R.id.sbPasswordLength);
        tePasswordLengthView = (TextView)findViewById(R.id.tvPasswordLength);
        
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        
        tvSysmbolsSetName = (TextView)findViewById(R.id.tvSymbolSetName);
        
        updateDisplaySymbolsSet();
        
        teSite.addTextChangedListener(new TextWatcher()
        {
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }
            
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after)
            {
            }
            
            @Override
            public void afterTextChanged(Editable s)
            {
                disableSaveData = true;
                try
                {
                    tbSite.setChecked(mDB.existsSite(s.toString()));
                }
                finally
                {
                    disableSaveData = false;
                }
            }
        });
        
        teLogin = ((EditText)findViewById(R.id.teLogin));
        
        teLogin.addTextChangedListener(new TextWatcher()
        {
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }
            
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after)
            {
            }
            
            @Override
            public void afterTextChanged(Editable s)
            {
                disableSaveData = true;
                try
                {
                    tbLogin.setChecked(mDB.existsLogin(s.toString()));
                }
                finally
                {
                    disableSaveData = false;
                }
             }
        });
        
        tePassword = ((EditText)findViewById(R.id.tePassword));
        teMasterPassword = ((EditText)findViewById(R.id.teMasterPassword));
        
        String savedMasterPassword = mPreferences.getString("master_password", StringUtils.Empty);
        
        ibViewMasterPassword = (ImageButton)findViewById(R.id.ib_view_master_password);

        if (!StringUtils.isNullOrEmpty(savedMasterPassword))
        {
            teMasterPassword.setText(savedMasterPassword);
            ibViewMasterPassword.setVisibility(View.GONE);
        }
        

        ibViewMasterPassword.setOnTouchListener(new OnTouchListener()
        {
            
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    teMasterPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    teMasterPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    return true;
                }
                return false;
            }
        });
        
        
        tbSite = (ToggleButton)findViewById(R.id.tbSiteIsRemembered);
        tbSite.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (disableSaveData)
                    return;
                
                if (!isChecked)
                {
                    mDB.forgetSite(teSite.getText().toString());
                }
                else
                    mDB.rememberSite(teSite.getText().toString());
                
                sites.requery();
            }
        });
        
        tbLogin = (ToggleButton)findViewById(R.id.tbLoginIsRemembered);
        tbLogin.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (disableSaveData)
                    return;
                
                if (!isChecked)
                {
                    mDB.forgetLogin(teLogin.getText().toString());
                }
                else
                    mDB.rememberLogin(teLogin.getText().toString());
                
                logins.requery();
            }
        });
        
        
        tbRememberMainPassword = (ToggleButton)findViewById(R.id.tbRememberMainPassword);
        
        if (!StringUtils.isNullOrEmpty(savedMasterPassword))
        {
            disableSaveData = true;
            try
            {
                tbRememberMainPassword.setChecked(true);
            }
            finally
            {
                disableSaveData = false;
            }
        }
        
        tbRememberMainPassword.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (disableSaveData)
                    return;
                
                if (!isChecked)
                {
                    mPreferences.edit().putString("master_password", StringUtils.Empty).commit();
                    teMasterPassword.setText("master password");
                    ibViewMasterPassword.setVisibility(View.VISIBLE);
                }
                else
                {
                    AlertDialog.Builder builder = new Builder(MainActivity.this);
                    builder.setMessage(R.string.save_main_password_query);
                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                    {
                        
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                            mPreferences.edit().putString("master_password", teMasterPassword.getText().toString()).commit();
                            ibViewMasterPassword.setVisibility(View.GONE);
                        }
                    });
                    builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener()
                    {
                        
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.cancel();
                            tbRememberMainPassword.setChecked(false);
                        }
                    });
                    
                    builder.setOnCancelListener(new OnCancelListener()
                    {
                        
                        @Override
                        public void onCancel(DialogInterface dialog)
                        {
                            tbRememberMainPassword.setChecked(false);                                                       
                        }
                    });
                    builder.setCancelable(true);
                    builder.show();
                }
            }
        });
        
        
        
        sbPasswordLength.setProgress(mPreferences.getInt(PREFERENCE_PARAM__PASSWORD_LENGTH_INT, 14));
        tePasswordLengthView.setText(StringUtils.Empty + (sbPasswordLength.getProgress() + 1));
        
        sbPasswordLength.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {
            
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
           }
            
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }
            
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser)
            {
                mPreferences.edit().putInt(PREFERENCE_PARAM__PASSWORD_LENGTH_INT, progress).commit();
                tePasswordLengthView.setText(StringUtils.Empty + (progress + 1));
            }
        });
        
        sbVersion.setProgress(mPreferences.getInt(PREFERENCE_PARAM__VERSION_INT, 0));
        tvVersion.setText(StringUtils.Empty + (sbVersion.getProgress() + 1));
        
        sbVersion.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {
            
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
           }
            
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }
            
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser)
            {
                mPreferences.edit().putInt(PREFERENCE_PARAM__VERSION_INT, progress).commit();
                tvVersion.setText(StringUtils.Empty + (progress + 1));
            }
        });
        
        
        ((ImageButton)findViewById(R.id.ib_sites)).setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                showSiteSelector();
            }
        });
        
        
        ((ImageButton)findViewById(R.id.ib_logins)).setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                showLoginSelector();
            }
        });
        
        
        ((Button) findViewById(R.id.btnGenerate)).setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                String masterPassword = ((EditText) findViewById(R.id.teMasterPassword)).getText().toString();
                String site = ((EditText) findViewById(R.id.teSite)).getText().toString();
                String login = ((EditText) findViewById(R.id.teLogin)).getText().toString();
                String version = StringUtils.Empty + (sbVersion.getProgress()+1);
                
                String password = "";
                
                try
                {
                    
//                    for (Provider provider : Security.getProviders()) {
//                        System.out.println("Provider: " + provider.getName());
//                        for (Provider.Service service : provider.getServices()) {
//                            System.out.println("  Algorithm: " + service.getAlgorithm());
//                        }
//                    }
                    
//                    byte[] t2 = CriptoUtils.hashSHA512(new byte[] { 1, 2, 3 });
//                    String st2 = BinUtils.hexString(t2);
//                    //27864cc5219a951a7a6e52b8c8dddf6981d098da1658d96258c870b2c88dfbcb51841aea172a28bafa6a79731165584677066045c959ed0f9929688d04defc29
//                    byte[] t1 = CriptoUtils.hashRIPEMD160(new byte[] { 1, 2, 3 });
//                    String st1 = BinUtils.hexString(t1);
//                    //79f901da2609f020adadbf2e5f68a16c8c3f7d57
                    
                    String tt1 = BinUtils.hexString(CriptoUtils.hashRIPEMD160(expandVer(version)));
                    String tt2 = BinUtils.hexString(CriptoUtils.hashSHA512(site));
                    
                    byte[] hashedPassword = CriptoUtils.hashSHA512((sbPasswordLength.getProgress()+1) + BinUtils.hexString(CriptoUtils.hashRIPEMD160(expandVer(version) + 
                                                                        BinUtils.hexString(CriptoUtils.hashSHA512(site + 
                                                                                           BinUtils.hexString(CriptoUtils.hashRIPEMD160(login + 
                                                                                                              BinUtils.hexString(CriptoUtils.hashSHA512(masterPassword))
                                                                                                              ))
                                                                                          ))
                                                                       )));
                    
                    byte[] hashedPasswordWOFirstByte = new byte[hashedPassword.length-2];
                    
                    System.arraycopy(hashedPassword, 1, hashedPasswordWOFirstByte, 0, hashedPasswordWOFirstByte.length);
                    
                    BigInteger bi = new BigInteger(hashedPasswordWOFirstByte);
                    bi = bi.abs();
                    
                    Vector<Integer> basesOfBigNum = new Vector<Integer>();
                    
                    String baseSring = StringUtils.Empty;
                    
                    int index = Integer.parseInt(mPreferences.getString("preference_key__symbols_set", "1"));
                    if (index != 0)
                    {
                        baseSring = password_symbol_sets[index-1];
                    }
                    else
                        baseSring = mPreferences.getString("preference_key__user_symbols_set", password_symbol_sets[0]);
                    BigInteger basement = BigInteger.valueOf(baseSring.length());
                    
//                    baseSring = "01";
//                    basement = BigInteger.valueOf(baseSring.length());
//                    bi = new BigInteger("9");
                    
                    BigInteger tmp = bi;
                    BigInteger mod = bi;
                    
                    while(tmp.compareTo(BigInteger.ZERO) != 0)
                    {
                        mod = tmp.mod(basement);
                        tmp = tmp.divide(basement);
                        basesOfBigNum.insertElementAt(Integer.valueOf(mod.intValue()), 0);
                    }
                    
                    if (basesOfBigNum.size() == 0)
                        basesOfBigNum.add(Integer.valueOf(0));
                    
                    //basesOfBigNum.add(Integer.valueOf(mod.intValue()));
                    
                    StringBuilder result = new StringBuilder();
                    
                    for(int i =0; i< basesOfBigNum.size(); i++)
                        result.append(baseSring.charAt(basesOfBigNum.elementAt(i).intValue()));
                    
                    password = result.toString();
                }
                catch (NoSuchProviderException e)
                {
                    password = "NoSuchProviderException";
                }
                catch (NoSuchAlgorithmException e)
                {
                    password = "NoSuchAlgorithmException";
                }
                
                ((EditText) findViewById(R.id.tePassword)).setText(password.substring(0, Math.min(password.length(), sbPasswordLength.getProgress()+1)));
                
            }
        });
    }
    
    protected void showLoginSelector()
    {
        AlertDialog.Builder builder = new Builder(this);
        builder.setTitle(getString(R.string.login));
        //logins = mDB.getLogins();
        builder.setSingleChoiceItems(logins, -1, "a_name", mOnClickLogins);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, mOnClickLogins);
        builder.create().show();
    }

    protected void showSiteSelector()
    {
        AlertDialog.Builder builder = new Builder(this);
        builder.setTitle(getString(R.string.site));
        builder.setSingleChoiceItems(sites, -1, "a_name", mOnClickSites);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, mOnClickSites);
        builder.create().show();
    }

    String expandVer(String ver)
    {
        if (StringUtils.isNullOrEmpty(ver))
            return "0";
        else
        {
            try
            {
                int verAsInt = Integer.parseInt(ver);
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < verAsInt; i++)
                    sb.append(i);
                
                return sb.toString();
            }
            catch (NumberFormatException e)
            {
                return "0";
            }
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    @Override
    protected void onPause()
    {
        Editor e = mPreferences.edit();
        
        e.putInt(PREFERENCE_PARAM__PASSWORD_LENGTH_INT, sbPasswordLength.getProgress());
        e.putInt(PREFERENCE_PARAM__VERSION_INT, sbVersion.getProgress());
        e.commit();
        
        super.onPause();
    }

    private void clearFields()
    {
        teMasterPassword.setText(StringUtils.Empty);
        teSite.setText(StringUtils.Empty);
        teLogin.setText(StringUtils.Empty);
        tePassword.setText(StringUtils.Empty);
        //sbPasswordLength.setProgress(0);
        //sbVersion.setProgress(0);
    }
    
    @Override
    protected void onStop()
    {
        super.onStop();
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            clearFields();
        }
        
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    protected void onDestroy()
    {
        clearFields();
        mDB.close();
        super.onDestroy();
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        
        switch (item.getItemId())
        {
            case R.id.menu_clear_stored_data_all:
                mDB.clearData(true, true);
                sites.requery();
                logins.requery();
                tbLogin.setChecked(false);
                tbSite.setChecked(false);
                return true;
            
            case R.id.menu_clear_stored_data_sites:
                mDB.clearData(true, false);
                sites.requery();
                tbSite.setChecked(false);
                return true;
            
            case R.id.menu_clear_stored_data_logins:
                mDB.clearData(false, true);
                logins.requery();
                tbLogin.setChecked(false);
                return true;
            case R.id.menu_settings:
                startActivityForResult(new Intent(this, PreferenceActivity.class), PREFERENCE_ACTIVITY_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        
        
    }
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PREFERENCE_ACTIVITY_CODE)
        {
            updateDisplaySymbolsSet();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateDisplaySymbolsSet()
    {
        String baseSring = StringUtils.Empty;
        
        int index = Integer.parseInt(mPreferences.getString("preference_key__symbols_set", "1"));
        tvSysmbolsSetName.setText(getResources().getStringArray(R.array.password_set_names)[index]);
    }
}
