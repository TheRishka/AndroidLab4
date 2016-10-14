package com.therishka.fragmania;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.therishka.fragmania.cats.CatsProvider;

/**
 * @author Rishad Mustafaev
 */

public class CatsDetailsFragment extends Fragment {

    public static final String CAT_FRAGMENT_TAG = "cat_fragment";
    public static final String CAT_NAME_BUNDLE_KEY = "cat_name";
    public static final String CAT_PHOTO_BUNDLE_KEY = "cat_photo";

    TextView catName;
    ImageView catPhoto;
    EditText catPhotoInput;
    Button catPhotoInputConfirm;

    public static CatsDetailsFragment newInstance(String catName, String catPhotoUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(CAT_NAME_BUNDLE_KEY, catName);
        bundle.putString(CAT_PHOTO_BUNDLE_KEY, catPhotoUrl);
        CatsDetailsFragment fragment = new CatsDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgmt_cat_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        catName = (TextView) view.findViewById(R.id.cat_name);
        catPhoto = (ImageView) view.findViewById(R.id.cat_photo);
        catPhotoInput = (EditText) view.findViewById(R.id.cat_photo_input);
        catPhotoInputConfirm = (Button) view.findViewById(R.id.cat_photo_input_confirm);

        if (getArguments() != null) {
            String name = getArguments().getString(CAT_NAME_BUNDLE_KEY, "");
            catName.setText(name);
            String photoUrl = getArguments().getString(CAT_PHOTO_BUNDLE_KEY, "");
            if (photoUrl.length() > 0) {
                Glide.with(getActivity())
                        .load(photoUrl)
                        .fitCenter()
                        .into(catPhoto);
            }
        }

        catPhotoInputConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatsProvider.getInstance().saveKittenPhoto(getActivity(),
                        catName.getText().toString(), catPhotoInput.getText().toString());
                Glide.with(getActivity())
                        .load(catPhotoInput.getText().toString())
                        .fitCenter()
                        .into(catPhoto);
                catPhotoInput.setText("");
            }
        });

    }
}
