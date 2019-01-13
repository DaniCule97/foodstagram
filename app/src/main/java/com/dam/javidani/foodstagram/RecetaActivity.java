package com.dam.javidani.foodstagram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

public class RecetaActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta);

        viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        getIncomingIntent();
        Log.d("RecetaActivity", "ONCREATE");

    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("autor") &&
                getIntent().hasExtra("nombte")){
            String autor = getIntent().getStringExtra("autor");
            String nombre = getIntent().getStringExtra("nombre");

            Log.d("RecetaActivity", "getIncomingIntent: " + autor + "-" + nombre);

            setAutor(autor);
            setNombre(nombre);
        }
    }

    private void setAutor(String autor){
        TextView autorTv = findViewById(R.id.acRecAutor);
        autorTv.setText(autor);
    }

    private void setNombre(String nombre){
        TextView nombreTv = findViewById(R.id.acRecNombre);
        nombreTv.setText(nombre);
    }

    class ViewPagerAdapter extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;
        private Integer[] images = {R.drawable.img_imagine_dragons, R.drawable.img_imagine_dragons, R.drawable.img_imagine_dragons};

        public ViewPagerAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.custom_layout, null);
            ImageView imageView = view.findViewById(R.id.imageView);
            // imageView.setImageResource(images[position]);

            TextView tvNombre = findViewById(R.id.acRecNombre);

            ViewPager vp = (ViewPager) container;
            vp.addView(view, 0);


            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            ViewPager vp = (ViewPager) container;
            View view = (View) object;
            vp.removeView(view);
        }
    }
}
