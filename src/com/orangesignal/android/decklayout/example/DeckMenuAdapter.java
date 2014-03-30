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

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

/**
 * トップメニュー用の Adapter クラスを提供します。
 * 
 * @author Koji Sugisawa
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
final class DeckMenuAdapter extends ArrayAdapter<String> {

	/**
	 * View を再利用して Adapter を高速化するための ViewHolder クラス
	 */
	static final class ViewHolder {
		CheckedTextView text;
	}

	private final LayoutInflater mInflater;

	/**
	 * コンストラクタです。
	 * 
	 * @param context コンテキスト
	 * @param items メニュー項目のリスト
	 */
	public DeckMenuAdapter(final Context context, final ArrayList<String> items) {
		super(context, 0, items);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(final int position, View view, final ViewGroup parent) {
		final ViewHolder holder;

		if (view == null) {
			view = mInflater.inflate(R.layout.deck_menu_item, null);
			holder = new ViewHolder();
			holder.text = (CheckedTextView) view.findViewById(android.R.id.text1);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		final String item = getItem(position);
		if (item != null) {
//			holder.text.setChecked(checked)
			holder.text.setText(item);
		}

		return view;
	}

}
