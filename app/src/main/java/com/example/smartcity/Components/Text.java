package com.example.smartcity.Components;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;


public class Text extends AppCompatTextView {

        public Text(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

        public Text(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public Text(Context context) {
            super(context);
            init();
        }

        private void init() {
            if (!isInEditMode()) {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Tommy.otf");
                setTypeface(tf);
            }
        }
}

