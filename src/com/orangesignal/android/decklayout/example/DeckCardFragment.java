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

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;

import com.orangesignal.android.decklayout.DeckManager;

/**
 * {@link DeckActivity} 用のフラグメントを提供します。
 * 
 * @author Koji Sugisawa
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class DeckCardFragment extends Fragment {

	private DeckActivity mDeckActivity;

	@Override
	public void onAttach(final Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof DeckActivity)) {
			throw new IllegalStateException("Activity must be DeckActivity");
		}
		mDeckActivity = (DeckActivity) activity;
	}

	protected final DeckManager getDeckManager() {
		return mDeckActivity.getDeckManager();
	}

}
