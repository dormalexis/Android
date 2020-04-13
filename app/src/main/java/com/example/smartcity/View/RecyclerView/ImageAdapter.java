package com.example.smartcity.View.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.GlideApp;

import static com.example.smartcity.Utilitaries.App.getContext;

public class ImageAdapter extends PagerAdapter {

    Item itemSelected;

    public ImageAdapter(Item itemSelected)
    {
        this.itemSelected = itemSelected;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.drawable.ic_check);

        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ((ViewPager) container).addView(imageView, 0);

        GlideApp.with(getContext())
                .load(itemSelected.getPictures().get(position).getPath())
                .apply(requestOptions)
                .into(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return itemSelected.getPictures().size();
    }
}
