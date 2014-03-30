package com.orangesignal.android.decklayout.example;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangesignal.android.decklayout.DeckListener;

public final class DetailFragment extends DeckCardFragment implements DeckListener {

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		final View root = inflater.inflate(R.layout.detail, null);
		root.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT, FILL_PARENT));
		return root;
	}

	@Override
	public void onDeckChanged() {
	}

}
