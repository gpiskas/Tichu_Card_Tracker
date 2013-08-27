/*
*  Tichu Card Tracker
*  Copyright (C) 2013  George Piskas
*
*  This program is free software; you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation; either version 2 of the License, or
*  (at your option) any later version.
*
*  This program is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License along
*  with this program; if not, write to the Free Software Foundation, Inc.,
*  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
*  Contact: geopiskas@gmail.com
*/

package geo.piskas.apps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class Prefs extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
		Preference versionPref = findPreference("versionPref");
		versionPref
				.setOnPreferenceClickListener(new OnPreferenceClickListener() {
					public boolean onPreferenceClick(Preference preference) {
						try{
							Intent intent = new Intent(Intent.ACTION_VIEW);
							intent.setData(Uri
									.parse("market://details?id=geo.piskas.apps"));
							startActivity(intent);
							return true;
						} catch(Exception e) {
							e.printStackTrace();
							Toast toast = Toast.makeText(getApplicationContext(), R.string.updateError, Toast.LENGTH_SHORT);
							toast.show();
						}
						return true;
					}
				});
		
		Preference languagePref = findPreference("language");
		languagePref
		.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

					@Override
					public boolean onPreferenceChange(Preference arg0, Object arg1) {
						Toast toast = Toast.makeText(getApplicationContext(), R.string.restartPrompt, Toast.LENGTH_SHORT);
						toast.show();
						return true;
					}
				});
	}

}
