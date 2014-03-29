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

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangesignal.android.decklayout.DeckManager;

/**
 * {@link DeckActivity} 用のフラグメントを提供します。
 * 
 * @author 杉澤 浩二
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DeckCardFragment extends Fragment {

	private DeckActivity mDeckActivity;

	@Override
	public void onAttach(final Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof DeckActivity)) {
			throw new IllegalStateException("Activity must be DeckActivity");
		}
		mDeckActivity = (DeckActivity) activity;
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		final View root = inflater.inflate(R.layout.card, null);
		root.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT, FILL_PARENT));

		root.findViewById(android.R.id.button1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				getDeckManager().attach(new DeckCardFragment(), String.valueOf(System.currentTimeMillis()));
			}
		});

		return root;
	}

	
	protected final DeckManager getDeckManager() {
		return mDeckActivity.getDeckManager();
	}

}
