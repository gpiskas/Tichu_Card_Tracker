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

public class TabStrongCards extends Activity implements OnClickListener {

	private ImageView bDragon, bPhoenix, bMahjongg, bDogs, bAce, bKing, bQueen,
			bJack;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4;
	private Button bRemove;
	private boolean toggleExit=false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.strongcards);
		bRemove = (Button) findViewById(R.id.bRemove);
		bRemove.setOnClickListener(this);
		initializeAllImageViews();
		initializeSmallCards();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (TabHostCards.getEndGameFlag()) {
			toggleExit=true;
			bRemove.setText(R.string.endgame);
		}
	}

	private void initializeSmallCards() {
		ivCard1 = (ImageView) findViewById(R.id.ivCard1);
		ivCard2 = (ImageView) findViewById(R.id.ivCard2);
		ivCard3 = (ImageView) findViewById(R.id.ivCard3);
		ivCard4 = (ImageView) findViewById(R.id.ivCard4);
	}

	private void initializeAllImageViews() {
		bDragon = (ImageView) findViewById(R.id.bDragon);
		bDragon.setOnClickListener(this);

		bPhoenix = (ImageView) findViewById(R.id.bPhoenix);
		bPhoenix.setOnClickListener(this);

		bMahjongg = (ImageView) findViewById(R.id.bMahjongg);
		bMahjongg.setOnClickListener(this);

		bDogs = (ImageView) findViewById(R.id.bDogs);
		bDogs.setOnClickListener(this);

		bAce = (ImageView) findViewById(R.id.bAce);
		bAce.setOnClickListener(this);

		bKing = (ImageView) findViewById(R.id.bKing);
		bKing.setOnClickListener(this);

		bQueen = (ImageView) findViewById(R.id.bQueen);
		bQueen.setOnClickListener(this);

		bJack = (ImageView) findViewById(R.id.bJack);
		bJack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		ivCard1.setImageResource(R.drawable.snull);
		ivCard2.setImageResource(R.drawable.snull);
		ivCard3.setImageResource(R.drawable.snull);
		ivCard4.setImageResource(R.drawable.snull);

		if (v.getId() == R.id.bRemove) {
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

			if (cardTag.equals("dogs") || cardTag.equals("mahjongg")
					|| cardTag.equals("phoenix") || cardTag.equals("dragon")) {
				searchForPlayedSpecialCard(playedCards, cardTag);
			} else {
				searchForPlayedCard(playedCards, cardTag);
			}
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

	private void searchForPlayedSpecialCard(ArrayList<Integer> playedCards,
			String cardTag) {
		boolean exists = false;

		for (int i = 0; i < playedCards.size(); i++) {
			if (getResources().getResourceName(playedCards.get(i)).contains(
					cardTag)) {
				ivCard1.setImageResource(R.drawable.snone);
				exists = true;
				break;
			}
		}
		if (!exists) {
			ivCard1.setImageResource(getResources().getIdentifier(
					"s" + cardTag, "drawable", "geo.piskas.apps"));
		}

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
			ivCard1.setImageResource(getResources().getIdentifier(
					"sbl" + cardTag, "drawable", "geo.piskas.apps"));
		}
		if (!rdExists) {
			ivCard2.setImageResource(getResources().getIdentifier(
					"srd" + cardTag, "drawable", "geo.piskas.apps"));
		}
		if (!gnExists) {
			ivCard3.setImageResource(getResources().getIdentifier(
					"sgn" + cardTag, "drawable", "geo.piskas.apps"));
		}
		if (!buExists) {
			ivCard4.setImageResource(getResources().getIdentifier(
					"sbu" + cardTag, "drawable", "geo.piskas.apps"));
		}
		if (buExists && gnExists && rdExists && blExists) {
			ivCard1.setImageResource(R.drawable.snone);
		}
	}
}
