package com.therishka.fragmania;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.therishka.fragmania.cats.CatsProvider;

/**
 * @author Rishad Mustafaev
 */

public class CatsActivity extends Activity implements CatsAdapter.CatItemClickListener {

    RecyclerView mCatsList;
    CatsAdapter mCatsAdapter;
    boolean isBigMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats_list);
        CatsProvider.getInstance().restoreData(this);
        mCatsList = (RecyclerView) findViewById(R.id.cats_list);
        mCatsList.setLayoutManager(new LinearLayoutManager(this));
        mCatsAdapter = new CatsAdapter();
        mCatsList.setAdapter(mCatsAdapter);

        mCatsAdapter.setCats(CatsProvider.getInstance().provideCats(74));
        mCatsAdapter.setCatItemClickListener(this);

        // эта View может быть только в layout_w900dp
        if (findViewById(R.id.cat_detail_fragment_container) != null) {
            isBigMode = true;
        }
    }

    @Override
    public void onItemClick(String catName) {
        toCatDetailsFragment(catName);
    }

    private void toCatDetailsFragment(String catName) {
        if (isBigMode) {
            Fragment fragment = new CatsDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(CatsDetailsFragment.CAT_NAME_BUNDLE_KEY, catName);
            bundle.putString(CatsDetailsFragment.CAT_PHOTO_BUNDLE_KEY,
                    CatsProvider.getInstance().getCatPhoto(catName));
            fragment.setArguments(bundle);

            getFragmentManager().beginTransaction()
                    .replace(R.id.cat_detail_fragment_container, fragment, CatsDetailsFragment.class.getName())
                    .commit();
        }
    }
}
