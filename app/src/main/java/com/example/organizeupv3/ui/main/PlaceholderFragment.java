package com.example.organizeupv3.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.organizeupv3.MapsActivity;
import com.example.organizeupv3.R;
import com.example.organizeupv3.databinding.FragmentAboutTabbedBinding;
import com.google.android.gms.maps.model.LatLng;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentAboutTabbedBinding binding;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentAboutTabbedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView tvAbout = root.findViewById(R.id.tvAbout);
        TextView tvMessage = root.findViewById(R.id.tvMessage);
        ImageView ivLogo = root.findViewById(R.id.ivLogo);
        WebView wvBrowser = root.findViewById(R.id.wvBrowser);
        Button btnViewMap = root.findViewById(R.id.btnViewMap);

        String url = "https://www.cut.ac.cy/";
        wvBrowser.setWebChromeClient(new WebChromeClient());
        wvBrowser.loadUrl(url);

        if(getArguments().getInt(ARG_SECTION_NUMBER) == 1){
            tvAbout.setVisibility(View.VISIBLE);
            tvMessage.setVisibility(View.VISIBLE);
            ivLogo.setVisibility(View.VISIBLE);
            wvBrowser.setVisibility(View.INVISIBLE);
            btnViewMap.setVisibility(View.INVISIBLE);
        }else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2){
            tvAbout.setVisibility(View.INVISIBLE);
            tvMessage.setVisibility(View.INVISIBLE);
            ivLogo.setVisibility(View.INVISIBLE);
            wvBrowser.setVisibility(View.VISIBLE);
            btnViewMap.setVisibility(View.INVISIBLE);
        }else if(getArguments().getInt(ARG_SECTION_NUMBER) == 3){
            tvAbout.setVisibility(View.INVISIBLE);
            tvMessage.setVisibility(View.INVISIBLE);
            ivLogo.setVisibility(View.INVISIBLE);
            wvBrowser.setVisibility(View.INVISIBLE);
            btnViewMap.setVisibility(View.VISIBLE);

            btnViewMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LatLng coords;
                    Bundle arguements = new Bundle();
                    coords = new LatLng(34.68140340793207, 33.04504604811046);
                    arguements.putString("marker","Department of Electrical Engineering and Computer Engineering and Informatics");
                    arguements.putParcelable("coordinates", coords);
                    Intent in = new Intent(getContext(), MapsActivity.class);
                    in.putExtras(arguements);
                    startActivity(in);
                }
            });
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}