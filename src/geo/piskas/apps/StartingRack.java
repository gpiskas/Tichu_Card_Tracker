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

public class StartingRack extends Activity implements OnClickListener {

	private Vibrator vb;
	private ArrayList<Integer> cards;
	private Stack<Integer> undoStack;
	private ImageButton ok, undo;
	private ImageView ivRackCard;
	private int idNO;
	private SharedPreferences getPrefs;
	private ImageView iv11, iv12, iv13, iv14, iv21, iv22, iv23, iv24, iv31,
			iv32, iv33, iv34, iv41, iv42, iv43, iv44, iv51, iv52, iv53, iv54,
			iv61, iv62, iv63, iv64, iv71, iv72, iv73, iv74, iv81, iv82, iv83,
			iv84, iv91, iv92, iv93, iv94, iv101, iv102, iv103, iv104, iv111,
			iv112, iv113, iv114, iv121, iv122, iv123, iv124, iv131, iv132,
			iv133, iv134, iv141, iv142, iv143, iv144;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startingrack);

		getWindow().setLayout (LayoutParams.FILL_PARENT /* width */ , LayoutParams.FILL_PARENT /* height */);
		
		getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		ok = (ImageButton) findViewById(R.id.bRackOk);
		ok.setOnClickListener(this);
		ok.setEnabled(false);
		undo = (ImageButton) findViewById(R.id.bRackUndo);
		undo.setOnClickListener(this);
		undo.setEnabled(false);

		cards = new ArrayList<Integer>();
		undoStack = new Stack<Integer>();
		ivRackCard = (ImageView) findViewById(R.id.ivRack0);
		idNO = R.id.ivRack0;
		initializeAllImageViews();
	}

	private void initializeAllImageViews() {
		iv11 = (ImageView) findViewById(R.id.ivRackdragon);
		iv11.setOnClickListener(this);
		iv12 = (ImageView) findViewById(R.id.ivRackphoenix);
		iv12.setOnClickListener(this);
		iv13 = (ImageView) findViewById(R.id.ivRackmahjongg);
		iv13.setOnClickListener(this);
		iv14 = (ImageView) findViewById(R.id.ivRackdogs);
		iv14.setOnClickListener(this);

		iv21 = (ImageView) findViewById(R.id.ivRackblace);
		iv21.setOnClickListener(this);
		iv22 = (ImageView) findViewById(R.id.ivRackrdace);
		iv22.setOnClickListener(this);
		iv23 = (ImageView) findViewById(R.id.ivRackgnace);
		iv23.setOnClickListener(this);
		iv24 = (ImageView) findViewById(R.id.ivRackbuace);
		iv24.setOnClickListener(this);

		iv31 = (ImageView) findViewById(R.id.ivRackblking);
		iv31.setOnClickListener(this);
		iv32 = (ImageView) findViewById(R.id.ivRackrdking);
		iv32.setOnClickListener(this);
		iv33 = (ImageView) findViewById(R.id.ivRackgnking);
		iv33.setOnClickListener(this);
		iv34 = (ImageView) findViewById(R.id.ivRackbuking);
		iv34.setOnClickListener(this);

		iv41 = (ImageView) findViewById(R.id.ivRackblqueen);
		iv41.setOnClickListener(this);
		iv42 = (ImageView) findViewById(R.id.ivRackrdqueen);
		iv42.setOnClickListener(this);
		iv43 = (ImageView) findViewById(R.id.ivRackgnqueen);
		iv43.setOnClickListener(this);
		iv44 = (ImageView) findViewById(R.id.ivRackbuqueen);
		iv44.setOnClickListener(this);

		iv51 = (ImageView) findViewById(R.id.ivRackbljack);
		iv51.setOnClickListener(this);
		iv52 = (ImageView) findViewById(R.id.ivRackrdjack);
		iv52.setOnClickListener(this);
		iv53 = (ImageView) findViewById(R.id.ivRackgnjack);
		iv53.setOnClickListener(this);
		iv54 = (ImageView) findViewById(R.id.ivRackbujack);
		iv54.setOnClickListener(this);

		iv61 = (ImageView) findViewById(R.id.ivRackbl10);
		iv61.setOnClickListener(this);
		iv62 = (ImageView) findViewById(R.id.ivRackrd10);
		iv62.setOnClickListener(this);
		iv63 = (ImageView) findViewById(R.id.ivRackgn10);
		iv63.setOnClickListener(this);
		iv64 = (ImageView) findViewById(R.id.ivRackbu10);
		iv64.setOnClickListener(this);

		iv71 = (ImageView) findViewById(R.id.ivRackbl9);
		iv71.setOnClickListener(this);
		iv72 = (ImageView) findViewById(R.id.ivRackrd9);
		iv72.setOnClickListener(this);
		iv73 = (ImageView) findViewById(R.id.ivRackgn9);
		iv73.setOnClickListener(this);
		iv74 = (ImageView) findViewById(R.id.ivRackbu9);
		iv74.setOnClickListener(this);

		iv81 = (ImageView) findViewById(R.id.ivRackbl8);
		iv81.setOnClickListener(this);
		iv82 = (ImageView) findViewById(R.id.ivRackrd8);
		iv82.setOnClickListener(this);
		iv83 = (ImageView) findViewById(R.id.ivRackgn8);
		iv83.setOnClickListener(this);
		iv84 = (ImageView) findViewById(R.id.ivRackbu8);
		iv84.setOnClickListener(this);

		iv91 = (ImageView) findViewById(R.id.ivRackbl7);
		iv91.setOnClickListener(this);
		iv92 = (ImageView) findViewById(R.id.ivRackrd7);
		iv92.setOnClickListener(this);
		iv93 = (ImageView) findViewById(R.id.ivRackgn7);
		iv93.setOnClickListener(this);
		iv94 = (ImageView) findViewById(R.id.ivRackbu7);
		iv94.setOnClickListener(this);

		iv101 = (ImageView) findViewById(R.id.ivRackbl6);
		iv101.setOnClickListener(this);
		iv102 = (ImageView) findViewById(R.id.ivRackrd6);
		iv102.setOnClickListener(this);
		iv103 = (ImageView) findViewById(R.id.ivRackgn6);
		iv103.setOnClickListener(this);
		iv104 = (ImageView) findViewById(R.id.ivRackbu6);
		iv104.setOnClickListener(this);

		iv111 = (ImageView) findViewById(R.id.ivRackbl5);
		iv111.setOnClickListener(this);
		iv112 = (ImageView) findViewById(R.id.ivRackrd5);
		iv112.setOnClickListener(this);
		iv113 = (ImageView) findViewById(R.id.ivRackgn5);
		iv113.setOnClickListener(this);
		iv114 = (ImageView) findViewById(R.id.ivRackbu5);
		iv114.setOnClickListener(this);

		iv121 = (ImageView) findViewById(R.id.ivRackbl4);
		iv121.setOnClickListener(this);
		iv122 = (ImageView) findViewById(R.id.ivRackrd4);
		iv122.setOnClickListener(this);
		iv123 = (ImageView) findViewById(R.id.ivRackgn4);
		iv123.setOnClickListener(this);
		iv124 = (ImageView) findViewById(R.id.ivRackbu4);
		iv124.setOnClickListener(this);

		iv131 = (ImageView) findViewById(R.id.ivRackbl3);
		iv131.setOnClickListener(this);
		iv132 = (ImageView) findViewById(R.id.ivRackrd3);
		iv132.setOnClickListener(this);
		iv133 = (ImageView) findViewById(R.id.ivRackgn3);
		iv133.setOnClickListener(this);
		iv134 = (ImageView) findViewById(R.id.ivRackbu3);
		iv134.setOnClickListener(this);

		iv141 = (ImageView) findViewById(R.id.ivRackbl2);
		iv141.setOnClickListener(this);
		iv142 = (ImageView) findViewById(R.id.ivRackrd2);
		iv142.setOnClickListener(this);
		iv143 = (ImageView) findViewById(R.id.ivRackgn2);
		iv143.setOnClickListener(this);
		iv144 = (ImageView) findViewById(R.id.ivRackbu2);
		iv144.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.bRackOk) {
			Intent returning = new Intent();
			Bundle rtrn = new Bundle();
			rtrn.putIntegerArrayList("cards", cards);
			returning.putExtras(rtrn);
			setResult(RESULT_OK, returning);
			finish();
		} else if (v.getId() == R.id.bRackUndo) {
			if (cards.size() > 0) {
				int id = undoStack.pop();
				ImageView iv = (ImageView) findViewById(id);
				iv.setImageDrawable(getResources().getDrawable(
						cards.get(cards.size() - 1)));
				iv.setOnClickListener(this);
				cards.remove(cards.size() - 1);
				idNO--;
				ivRackCard = (ImageView) findViewById(idNO);
				ivRackCard.setImageResource(R.drawable.back);

				if (cards.size() < 14) {
					ok.setEnabled(false);
				}
				if (cards.size() == 0) {
					undo.setEnabled(false);
				}
			}
		} else {
			if (cards.size() < 14) {
				if(getPrefs.getBoolean("vibration", true)) {
					vb.vibrate(50);
				}
				undo.setEnabled(true);

				ImageView iv = (ImageView) findViewById(v.getId());
				ivRackCard.setImageDrawable(iv.getDrawable());

				idNO++;
				ivRackCard = (ImageView) findViewById(idNO);


				undoStack.push(v.getId());

				cards.add(getResources().getIdentifier((String) iv.getTag(),
						"drawable", "geo.piskas.apps")); // tag contains
															// filename
				iv.setImageResource(R.drawable.back);
				iv.setOnClickListener(null);

				if (cards.size() == 14) {
					ok.setEnabled(true);
				}
			}

		}

	}

	@Override
	public void onBackPressed() {
		cards.clear();
		MainMenu.setContinueState(false);
		super.onBackPressed();
	}
}
