/*
 * Copyright 2011-2014 the original author or authors.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */

package com.orangesignal.android.decklayout.example;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.orangesignal.android.decklayout.Deck;
import com.orangesignal.android.decklayout.DeckManager;

/**
 * 主画面のアクティビティを提供します。
 *
 * @author Koji Sugisawa
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public final class DeckActivity extends Activity {

	private DeckManager mDeckManager;
	private Deck mDeck;
	private View mMenuView;
	private ListView mListView;

	/**
	 * デッキカードが削除可能状態かどうかを保持します。
	 */
	private boolean mAllowRemove;

	//////////////////////////////////////////////////////////////////////////
	// オーバーライド

/*
	@Override
	protected void onSaveInstanceState(final Bundle outState) {
		super.onSaveInstanceState(outState);
		mDeckManager.saveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(final Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mDeckManager.restoreInstanceState(savedInstanceState);
	}
*/

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setupViews(savedInstanceState);
		setupDataSource();

		mListView.setItemChecked(0, true);
		DeckCardFragment f = new DeckCardFragment();
		mDeckManager.attach(f, "top");
	}

	//////////////////////////////////////////////////////////////////////////

	private static final float REMOVE_SPACE = 420F;

	void onDeckCardRemoveDrag(final Deck deck, final View card) {
		mAllowRemove = deck.getCardCount() >= 2 && card.getX() >= REMOVE_SPACE;
	}

	void onDeckCardRemove(final Deck deck) {
		if (mAllowRemove) {
			int pos = deck.getRightCardPosition();
			if (pos == Deck.CARD_NOT_FOUND) {
				pos = deck.getFirstCardPosition();
			}
			if (pos != Deck.CARD_NOT_FOUND) {
				mDeckManager.detach(deck.getChildAt(pos), false);
			}
			mAllowRemove = false;
		}
	}

	boolean onDeckMenuItemSelected(final CheckedTextView v, final Fragment f) {
		if (v.isChecked()) {
			mDeck.showFirstCard();
			return false;
		}
		mDeckManager.attach(f, "top");
		return true;
	}

	//////////////////////////////////////////////////////////////////////////
	// private method

	/**
	 * レイアウトを処理します。
	 * 
	 * @param savedInstanceState
	 */
	private void setupViews(final Bundle savedInstanceState) {
		setContentView(R.layout.main);

		mDeck = (Deck) findViewById(R.id.deck);

		final LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.deck_menu, null);

		mDeck.setSideView(mMenuView, new FrameLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
		mDeck.setLeftMost(
				mMenuView.findViewById(R.id.header).getLayoutParams().height,
				mMenuView.findViewById(R.id.content).getLayoutParams().width
			);
		mListView = (ListView) mMenuView.findViewById(android.R.id.list);

		////////////////////////////////////////////////////////////
		// DeckManager を構成します。

		mDeckManager = new DeckManager(this, R.id.deck);

		////////////////////////////////////////////////////////////
		// イベントを設定します。

		mDeck.setOnDeckCardRemoveListener(new Deck.OnDeckCardRemoveListener() {

			@Override
			public void onLayout(final Deck deck) {
				// 何もしません
			}

			@Override
			public void onRemoveDrag(final Deck deck, final View firstCard) {
				onDeckCardRemoveDrag(deck, firstCard);
			}

			@Override
			public void onRemoveDragEnd(final Deck deck) {
				onDeckCardRemove(deck);
			}

		});

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				onDeckMenuItemSelected((CheckedTextView) view, new DeckCardFragment());
			}

		});
	}

	private void setupDataSource() {
		final ArrayList<String> items = new ArrayList<String>(3);
		items.add("Home");
		items.add("Portfolio");
		items.add("Market");
		mListView.setAdapter(new DeckMenuAdapter(this, items));
	}

	//////////////////////////////////////////////////////////////////////////
	// setter / getter

	public DeckManager getDeckManager() {
		return mDeckManager;
	}

}
