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

import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class TabHostCards extends TabActivity {
	
	private static boolean endFlag=false;
	private static ArrayList<Integer> playedCards= new ArrayList<Integer>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cardshost);
		
		if (getIntent().getFlags()==0) {
			playedCards.clear();
			Intent sRi = new Intent(this, StartingRack.class);
			startActivityForResult(sRi, 0);
			MainMenu.setContinueState(true);
		}

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent;
		// strong cards
		intent = new Intent().setClass(this, TabStrongCards.class);
		spec = tabHost.newTabSpec(" ")
				.setIndicator("STRONG CARDS", res.getDrawable(R.drawable.strongrack))
				.setContent(intent);
		tabHost.addTab(spec);
		// weak cards
		intent = new Intent().setClass(this, TabWeakCards.class);
		spec = tabHost.newTabSpec(" ")
				.setIndicator("WEAK CARDS")
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}

	public static void removeCards(ArrayList<Integer> cards) {
		playedCards.addAll(cards);
		if(playedCards.size()==56){
			endFlag=true;
		}
	}


	public static ArrayList<Integer> getPlayedCards() {
		return playedCards;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle strtRack = data.getExtras();
			playedCards.addAll(strtRack.getIntegerArrayList("cards"));
		} else
			finish();
	}

	public static boolean getEndGameFlag() {
		return endFlag;		
	}
}
