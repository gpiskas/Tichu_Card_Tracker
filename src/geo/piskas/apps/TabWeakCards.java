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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class TabWeakCards extends Activity implements OnClickListener {

	private ImageView b10T2, b9T2, b8T2, b7T2, b6T2, b5T2, b4T2, b3T2, b2T2;
	private ImageView ivCard1T2, ivCard2T2, ivCard3T2, ivCard4T2;
	private Button bRemoveT2;
	private boolean toggleExit=false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weakcards);
		bRemoveT2 = (Button) findViewById(R.id.bRemoveT2);
		bRemoveT2.setOnClickListener(this);
		initializeAllImageViews();
		initializeSmallCards();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (TabHostCards.getEndGameFlag()) {
			toggleExit=true;
			bRemoveT2.setText(R.string.endgame);
		}
	}
	
	private void initializeSmallCards() {
		ivCard1T2 = (ImageView) findViewById(R.id.ivCard1T2);
		ivCard2T2 = (ImageView) findViewById(R.id.ivCard2T2);
		ivCard3T2 = (ImageView) findViewById(R.id.ivCard3T2);
		ivCard4T2 = (ImageView) findViewById(R.id.ivCard4T2);
	}

	private void initializeAllImageViews() {
		b10T2 = (ImageView) findViewById(R.id.b10T2);
		b10T2.setOnClickListener(this);

		b9T2 = (ImageView) findViewById(R.id.b9T2);
		b9T2.setOnClickListener(this);

		b8T2 = (ImageView) findViewById(R.id.b8T2);
		b8T2.setOnClickListener(this);

		b7T2 = (ImageView) findViewById(R.id.b7T2);
		b7T2.setOnClickListener(this);

		b6T2 = (ImageView) findViewById(R.id.b6T2);
		b6T2.setOnClickListener(this);

		b5T2 = (ImageView) findViewById(R.id.b5T2);
		b5T2.setOnClickListener(this);

		b4T2 = (ImageView) findViewById(R.id.b4T2);
		b4T2.setOnClickListener(this);

		b3T2 = (ImageView) findViewById(R.id.b3T2);
		b3T2.setOnClickListener(this);

		b2T2 = (ImageView) findViewById(R.id.b2T2);
		b2T2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		ivCard1T2.setImageResource(R.drawable.snull);
		ivCard2T2.setImageResource(R.drawable.snull);
		ivCard3T2.setImageResource(R.drawable.snull);
		ivCard4T2.setImageResource(R.drawable.snull);

		if (v.getId() == R.id.bRemoveT2) {
			if(toggleExit){
				MainMenu.setContinueState(false);
				finish();
			}else{
				Intent rCi = new Intent(this, RemoveCards.class);
				startActivityForResult(rCi, 0);
			}
			
		} else {
			ArrayList<Integer> playedCards = new ArrayList<Integer>();
			playedCards.addAll(TabHostCards.getPlayedCards());
			String cardTag = (String) v.getTag();
			searchForPlayedCard(playedCards, cardTag);

		}

	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle rmvCards = data.getExtras();
			TabHostCards.removeCards(rmvCards.getIntegerArrayList("cards"));
		} else
			finish();
	}
	
	
	private void searchForPlayedCard(ArrayList<Integer> playedCards,
			String cardTag) {
		boolean blExists = false;
		boolean rdExists = false;
		boolean gnExists = false;
		boolean buExists = false;
		for (int i = 0; i < playedCards.size(); i++) {
			if (getResources().getResourceName(playedCards.get(i)).contains(
					cardTag)) {
				if (getResources().getResourceName(playedCards.get(i))
						.contains("bl" + cardTag)) {
					blExists = true;
				} else if (getResources().getResourceName(playedCards.get(i))
						.contains("rd" + cardTag)) {
					rdExists = true;
				} else if (getResources().getResourceName(playedCards.get(i))
						.contains("gn" + cardTag)) {
					gnExists = true;
				} else if (getResources().getResourceName(playedCards.get(i))
						.contains("bu" + cardTag)) {
					buExists = true;
				}
			}
		}
		if (!blExists) {
			ivCard1T2.setImageResource(getResources().getIdentifier(
					"sbl" + cardTag, "drawable", "geo.piskas.apps"));
		}
		if (!rdExists) {
			ivCard2T2.setImageResource(getResources().getIdentifier(
					"srd" + cardTag, "drawable", "geo.piskas.apps"));
		}
		if (!gnExists) {
			ivCard3T2.setImageResource(getResources().getIdentifier(
					"sgn" + cardTag, "drawable", "geo.piskas.apps"));
		}
		if (!buExists) {
			ivCard4T2.setImageResource(getResources().getIdentifier(
					"sbu" + cardTag, "drawable", "geo.piskas.apps"));
		}
		if (buExists && gnExists && rdExists && blExists) {
			ivCard1T2.setImageResource(R.drawable.snone);
		}

	}
}
