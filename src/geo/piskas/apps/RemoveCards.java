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
import java.util.Stack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;

public class RemoveCards extends Activity implements OnClickListener {

	private Vibrator vb;
	private ArrayList<Integer> cards;
	private Stack<Integer> undoStack;
	private ImageButton okRC, undoRC;
	private ImageView ivRackCardRC;
	private int idNO;
	private SharedPreferences getPrefs;
	private ImageView iv11RC, iv12RC, iv13RC, iv14RC, iv21RC, iv22RC, iv23RC,
			iv24RC, iv31RC, iv32RC, iv33RC, iv34RC, iv41RC, iv42RC, iv43RC,
			iv44RC, iv51RC, iv52RC, iv53RC, iv54RC, iv61RC, iv62RC, iv63RC,
			iv64RC, iv71RC, iv72RC, iv73RC, iv74RC, iv81RC, iv82RC, iv83RC,
			iv84RC, iv91RC, iv92RC, iv93RC, iv94RC, iv101RC, iv102RC, iv103RC,
			iv104RC, iv111RC, iv112RC, iv113RC, iv114RC, iv121RC, iv122RC,
			iv123RC, iv124RC, iv131RC, iv132RC, iv133RC, iv134RC, iv141RC,
			iv142RC, iv143RC, iv144RC;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.removecards);

		getWindow().setLayout (LayoutParams.FILL_PARENT /* width */ , LayoutParams.FILL_PARENT /* height */);
		
		getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		okRC = (ImageButton) findViewById(R.id.bRackOkRC);
		okRC.setOnClickListener(this);
		okRC.setEnabled(false);
		undoRC = (ImageButton) findViewById(R.id.bRackUndoRC);
		undoRC.setOnClickListener(this);
		undoRC.setEnabled(false);

		cards = new ArrayList<Integer>();
		undoStack = new Stack<Integer>();
		ivRackCardRC = (ImageView) findViewById(R.id.ivRack0RC);
		idNO = R.id.ivRack0RC;
		initializeAllImageViews();
		hideAlreadyPlayedCards();
	}

	private void hideAlreadyPlayedCards() {
		ArrayList<Integer> idsToHide = new ArrayList<Integer>();
		idsToHide.addAll(TabHostCards.getPlayedCards());

		int idNoDr = R.id.ivRackdragonRC;
		ImageView dummyIV = (ImageView) findViewById(idNoDr);

		int drawableIDtoRemove = 0;
		int key = 0;
		for (int i = 0; i < idsToHide.size(); i++) {
			
			drawableIDtoRemove = idsToHide.get(i);
			idNoDr = R.id.ivRackdragonRC;
			dummyIV = (ImageView) findViewById(idNoDr);
			
			for (int j = 0; j < 56; j++) {
				key = getResources().getIdentifier((String) dummyIV.getTag(),
						"drawable", "geo.piskas.apps");
				if (drawableIDtoRemove == key) {
					dummyIV.setImageResource(R.drawable.back);
					dummyIV.setOnClickListener(null);
					break;
				}
				idNoDr++;
				dummyIV = (ImageView) findViewById(idNoDr);
			}
		}
		idsToHide.clear();
	}

	private void initializeAllImageViews() {
		iv11RC = (ImageView) findViewById(R.id.ivRackdragonRC);
		iv11RC.setOnClickListener(this);
		iv12RC = (ImageView) findViewById(R.id.ivRackphoenixRC);
		iv12RC.setOnClickListener(this);
		iv13RC = (ImageView) findViewById(R.id.ivRackmahjonggRC);
		iv13RC.setOnClickListener(this);
		iv14RC = (ImageView) findViewById(R.id.ivRackdogsRC);
		iv14RC.setOnClickListener(this);

		iv21RC = (ImageView) findViewById(R.id.ivRackblaceRC);
		iv21RC.setOnClickListener(this);
		iv22RC = (ImageView) findViewById(R.id.ivRackrdaceRC);
		iv22RC.setOnClickListener(this);
		iv23RC = (ImageView) findViewById(R.id.ivRackgnaceRC);
		iv23RC.setOnClickListener(this);
		iv24RC = (ImageView) findViewById(R.id.ivRackbuaceRC);
		iv24RC.setOnClickListener(this);

		iv31RC = (ImageView) findViewById(R.id.ivRackblkingRC);
		iv31RC.setOnClickListener(this);
		iv32RC = (ImageView) findViewById(R.id.ivRackrdkingRC);
		iv32RC.setOnClickListener(this);
		iv33RC = (ImageView) findViewById(R.id.ivRackgnkingRC);
		iv33RC.setOnClickListener(this);
		iv34RC = (ImageView) findViewById(R.id.ivRackbukingRC);
		iv34RC.setOnClickListener(this);

		iv41RC = (ImageView) findViewById(R.id.ivRackblqueenRC);
		iv41RC.setOnClickListener(this);
		iv42RC = (ImageView) findViewById(R.id.ivRackrdqueenRC);
		iv42RC.setOnClickListener(this);
		iv43RC = (ImageView) findViewById(R.id.ivRackgnqueenRC);
		iv43RC.setOnClickListener(this);
		iv44RC = (ImageView) findViewById(R.id.ivRackbuqueenRC);
		iv44RC.setOnClickListener(this);

		iv51RC = (ImageView) findViewById(R.id.ivRackbljackRC);
		iv51RC.setOnClickListener(this);
		iv52RC = (ImageView) findViewById(R.id.ivRackrdjackRC);
		iv52RC.setOnClickListener(this);
		iv53RC = (ImageView) findViewById(R.id.ivRackgnjackRC);
		iv53RC.setOnClickListener(this);
		iv54RC = (ImageView) findViewById(R.id.ivRackbujackRC);
		iv54RC.setOnClickListener(this);

		iv61RC = (ImageView) findViewById(R.id.ivRackbl10RC);
		iv61RC.setOnClickListener(this);
		iv62RC = (ImageView) findViewById(R.id.ivRackrd10RC);
		iv62RC.setOnClickListener(this);
		iv63RC = (ImageView) findViewById(R.id.ivRackgn10RC);
		iv63RC.setOnClickListener(this);
		iv64RC = (ImageView) findViewById(R.id.ivRackbu10RC);
		iv64RC.setOnClickListener(this);

		iv71RC = (ImageView) findViewById(R.id.ivRackbl9RC);
		iv71RC.setOnClickListener(this);
		iv72RC = (ImageView) findViewById(R.id.ivRackrd9RC);
		iv72RC.setOnClickListener(this);
		iv73RC = (ImageView) findViewById(R.id.ivRackgn9RC);
		iv73RC.setOnClickListener(this);
		iv74RC = (ImageView) findViewById(R.id.ivRackbu9RC);
		iv74RC.setOnClickListener(this);

		iv81RC = (ImageView) findViewById(R.id.ivRackbl8RC);
		iv81RC.setOnClickListener(this);
		iv82RC = (ImageView) findViewById(R.id.ivRackrd8RC);
		iv82RC.setOnClickListener(this);
		iv83RC = (ImageView) findViewById(R.id.ivRackgn8RC);
		iv83RC.setOnClickListener(this);
		iv84RC = (ImageView) findViewById(R.id.ivRackbu8RC);
		iv84RC.setOnClickListener(this);

		iv91RC = (ImageView) findViewById(R.id.ivRackbl7RC);
		iv91RC.setOnClickListener(this);
		iv92RC = (ImageView) findViewById(R.id.ivRackrd7RC);
		iv92RC.setOnClickListener(this);
		iv93RC = (ImageView) findViewById(R.id.ivRackgn7RC);
		iv93RC.setOnClickListener(this);
		iv94RC = (ImageView) findViewById(R.id.ivRackbu7RC);
		iv94RC.setOnClickListener(this);

		iv101RC = (ImageView) findViewById(R.id.ivRackbl6RC);
		iv101RC.setOnClickListener(this);
		iv102RC = (ImageView) findViewById(R.id.ivRackrd6RC);
		iv102RC.setOnClickListener(this);
		iv103RC = (ImageView) findViewById(R.id.ivRackgn6RC);
		iv103RC.setOnClickListener(this);
		iv104RC = (ImageView) findViewById(R.id.ivRackbu6RC);
		iv104RC.setOnClickListener(this);

		iv111RC = (ImageView) findViewById(R.id.ivRackbl5RC);
		iv111RC.setOnClickListener(this);
		iv112RC = (ImageView) findViewById(R.id.ivRackrd5RC);
		iv112RC.setOnClickListener(this);
		iv113RC = (ImageView) findViewById(R.id.ivRackgn5RC);
		iv113RC.setOnClickListener(this);
		iv114RC = (ImageView) findViewById(R.id.ivRackbu5RC);
		iv114RC.setOnClickListener(this);

		iv121RC = (ImageView) findViewById(R.id.ivRackbl4RC);
		iv121RC.setOnClickListener(this);
		iv122RC = (ImageView) findViewById(R.id.ivRackrd4RC);
		iv122RC.setOnClickListener(this);
		iv123RC = (ImageView) findViewById(R.id.ivRackgn4RC);
		iv123RC.setOnClickListener(this);
		iv124RC = (ImageView) findViewById(R.id.ivRackbu4RC);
		iv124RC.setOnClickListener(this);

		iv131RC = (ImageView) findViewById(R.id.ivRackbl3RC);
		iv131RC.setOnClickListener(this);
		iv132RC = (ImageView) findViewById(R.id.ivRackrd3RC);
		iv132RC.setOnClickListener(this);
		iv133RC = (ImageView) findViewById(R.id.ivRackgn3RC);
		iv133RC.setOnClickListener(this);
		iv134RC = (ImageView) findViewById(R.id.ivRackbu3RC);
		iv134RC.setOnClickListener(this);

		iv141RC = (ImageView) findViewById(R.id.ivRackbl2RC);
		iv141RC.setOnClickListener(this);
		iv142RC = (ImageView) findViewById(R.id.ivRackrd2RC);
		iv142RC.setOnClickListener(this);
		iv143RC = (ImageView) findViewById(R.id.ivRackgn2RC);
		iv143RC.setOnClickListener(this);
		iv144RC = (ImageView) findViewById(R.id.ivRackbu2RC);
		iv144RC.setOnClickListener(this);

	}

	@Override
	public void onBackPressed() {
		cards.clear();
		Intent returning = new Intent();
		Bundle rtrn = new Bundle();
		rtrn.putIntegerArrayList("cards", cards);
		returning.putExtras(rtrn);
		setResult(RESULT_OK, returning);
		super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.bRackOkRC) {
			Intent returning = new Intent();
			Bundle rtrn = new Bundle();
			rtrn.putIntegerArrayList("cards", cards);
			returning.putExtras(rtrn);
			setResult(RESULT_OK, returning);
			finish();
		} else if (v.getId() == R.id.bRackUndoRC) {
			if (cards.size() > 0) {
				int id = undoStack.pop();
				ImageView iv = (ImageView) findViewById(id);
				iv.setImageDrawable(getResources().getDrawable(
						cards.get(cards.size() - 1)));
				iv.setOnClickListener(this);
				cards.remove(cards.size() - 1);
				idNO--;
				ivRackCardRC = (ImageView) findViewById(idNO);
				ivRackCardRC.setImageResource(R.drawable.back);

				if (cards.size() == 0) {
					okRC.setEnabled(false);
					undoRC.setEnabled(false);
				}
			}
		} else {
			if (cards.size() < 7) {
				if(getPrefs.getBoolean("vibration", true)) {
					vb.vibrate(50);
				}
				okRC.setEnabled(true);
				undoRC.setEnabled(true);

				ImageView iv = (ImageView) findViewById(v.getId());
				ivRackCardRC.setImageDrawable(iv.getDrawable());

				idNO++;
				ivRackCardRC = (ImageView) findViewById(idNO);

				undoStack.push(v.getId());

				cards.add(getResources().getIdentifier((String) iv.getTag(),
						"drawable", "geo.piskas.apps")); // tag contains
															// filename
				iv.setImageResource(R.drawable.back);
				iv.setOnClickListener(null);

			}

		}

	}
}
