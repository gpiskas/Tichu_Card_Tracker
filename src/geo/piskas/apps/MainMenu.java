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

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainMenu extends Activity implements OnClickListener {

	private Button bNew;
	private Button bExit;
	private Button bCont;
	private Button bAbout;
	private static boolean enableCont = false;
	private Locale locale = null;
	public static PopupWindow pwLoading;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		String localeTag = getPrefs.getString("language", "en");
		locale = new Locale(localeTag);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config, null);
		setContentView(R.layout.mainmenu);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		pwLoading = new PopupWindow(inflater.inflate(R.layout.loading, null,
				false), LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				true);
		initialize();
		if (enableCont == true) {
			bCont.setEnabled(true);
		} else {
			bCont.setEnabled(false);
		}
	}

	@Override
	protected void onResume() {
		if (enableCont == true) {
			bCont.setEnabled(true);
		} else {
			bCont.setEnabled(false);
		}
		onCreate(null);
		super.onResume();
	}

	private void initialize() {
		bNew = (Button) findViewById(R.id.bNewGame);
		bExit = (Button) findViewById(R.id.bExit);
		bCont = (Button) findViewById(R.id.bContinue);
		bAbout = (Button) findViewById(R.id.bAbout);
		bNew.setOnClickListener(this);
		bExit.setOnClickListener(this);
		bCont.setOnClickListener(this);
		bAbout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bContinue:
			Intent c = new Intent(this, TabHostCards.class);
			c.setFlags(1); // continue game =1
			startActivity(c);
			break;
		case R.id.bNewGame:
			bCont.setEnabled(false);
			pwLoading.showAtLocation(v, Gravity.CENTER, 0, 0);
			Intent n = new Intent(this, TabHostCards.class);
			n.setFlags(0); // new game =0
			startActivity(n);
			break;
		case R.id.bAbout:
			Intent a = new Intent(this, About.class);
			startActivity(a);
			break;
		case R.id.bExit:
			finish();
			System.runFinalizersOnExit(true);
			System.exit(0);
		}
	}

	@Override
	protected void onPause() {
		if (pwLoading != null) {
			if (pwLoading.isShowing()) {
				pwLoading.dismiss();
			}
		}
		super.onPause();
	}

	public static void setContinueState(boolean state) {
		enableCont = state;
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.popupmenu, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i = new Intent("geo.piskas.apps.PREFS");
		startActivity(i);
		return false;
	}

}
