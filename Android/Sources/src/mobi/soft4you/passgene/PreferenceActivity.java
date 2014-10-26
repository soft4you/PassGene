package mobi.soft4you.passgene;

import mobi.soft4you.utils.StringUtils;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceGroup;

public class PreferenceActivity extends android.preference.PreferenceActivity implements OnSharedPreferenceChangeListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        addPreferencesFromResource(R.xml.preferences);
        
        InitSummaries();

    }
    
    
    /**
     * Инициализация отображения значений по-умолчанию. Необходимо данный метод вызывать после создания Activity-наследника
     * (в конце метода onCreate)
     */
    protected void InitSummaries()
    {
        ProcessItems(this.getPreferenceScreen());

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
    
    @SuppressWarnings("deprecation")
    @Override
    protected void onDestroy()
    {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

        super.onDestroy();
    }

    /**
        * Set the summaries of all preferences
        */
      protected void ProcessItems(PreferenceGroup pg)
      {
        for (int i = 0; i < pg.getPreferenceCount(); ++i) 
        {
            Preference p = pg.getPreference(i);
            
            ProcessPreferenceItem(p);
            
            if (p instanceof PreferenceGroup)
            {
                this.ProcessItems((PreferenceGroup) p); // recursion
            }
            else
                this.setSummary(p);
        }
      }

      /**
        * Set the summaries of the given preference
        */
      private void setSummary(Preference pref) {
        // react on type or key
          
          if (pref != null && 
              pref.getSummary() != null && 
              StringUtils.isNullOrEmpty(pref.getSummary().toString())
             )
          {
              return;
          }
          
          if (pref instanceof ListPreference) 
          {
              ListPreference listPref = (ListPreference)pref;
              pref.setSummary(StringUtils.isNullOrEmpty(StringUtils.Empty + listPref.getEntry()) ? StringUtils.Empty : listPref.getEntry());
          }
          else if (pref instanceof EditTextPreference) 
          {
            EditTextPreference editTextPref = (EditTextPreference)pref; 
            pref.setSummary(StringUtils.isNullOrEmpty(editTextPref.getText()) ? StringUtils.Empty : editTextPref.getText()); 
          }

      }

      /**
        * used to change the summary of a preference
        */
      public void onSharedPreferenceChanged(SharedPreferences sp, String key) {
        @SuppressWarnings("deprecation")
        Preference pref = findPreference(key);
        this.setSummary(pref);
      }
      
      
        /**
         * Обработать элемент настроек. Метод вызывается до того, как будут инициализированые Summaries
         * @param preference
         */
        private final void ProcessPreferenceItem(Preference preference)
        {
            if (preference instanceof ListPreference) 
            {
                ProcessListPreferenceItem((ListPreference)preference);
            }
            else if (preference instanceof EditTextPreference) 
            {
                ProcessEditTextPreferenceItem((EditTextPreference)preference);
            }
        }
      
        protected void ProcessListPreferenceItem(ListPreference preference)
        {
        }
        protected void ProcessEditTextPreferenceItem(EditTextPreference preference)
        {
        }
    
}
