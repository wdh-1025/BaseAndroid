package com.photo.photoview.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.photo.R;


/**
 * 单张图片显示Fragment
 */
public class ImageDetailFragment extends Fragment {
    private String mImageUrl;
    private ImageView mImageView;
    private ProgressBar progressBar;
    private PhotoViewAttacher mAttacher;

    private static ImageAttribute mImageAttribute;

    public static void setImageAttribute(ImageAttribute imageAttribute) {
        mImageAttribute = imageAttribute;
    }

    public static void unImageAttribute() {
        mImageAttribute = null;
    }


    public static ImageDetailFragment newInstance(String imageUrl) {
        final ImageDetailFragment f = new ImageDetailFragment();
        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.photoview_image_detail, container, false);
        mImageView = (ImageView) v.findViewById(R.id.image);

        mAttacher = new PhotoViewAttacher(mImageView);

        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

            @Override
            public void onPhotoTap(View arg0, float arg1, float arg2) {
                getActivity().finish();
            }
        });

        progressBar = (ProgressBar) v.findViewById(R.id.loading);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mImageAttribute != null) {
            mImageAttribute.ImageAttribute(mImageView, mImageUrl, this);
        }
    }

    public void onLoadingStarted() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void onLoadingFailed(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    public void onLoadingComplete() {
        progressBar.setVisibility(View.GONE);
        mAttacher.update();
    }
}
