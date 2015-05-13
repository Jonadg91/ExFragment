package com.jonathanduque.exfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.LinearGradient;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private ViewPager viewPager;

    private LinearLayout page1;
    private LinearLayout page2;
    private LinearLayout page3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager =(ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new MyPageAdapter());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                Toast.makeText(MainActivity.this,"Page "+(i+1),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

       /*  Fragment dinamico sin menu
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        Batman fragment=new Batman();
        fragmentTransaction.add(android.R.id.content, fragment).commit(); */
    }



    class MyPageAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View page=null;

            switch (position){
                case 0:
                    if (page1==null)
                        page1=(LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_superman,null);
                    page=page1;
                    break;
                case 1:
                    if (page2==null)
                        page2=(LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_batman,null);
                    page=page2;
                    break;
                default:
                    if (page3==null)
                        page3=(LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_guason,null);
                    page=page3;
                    break;


            }
            ((ViewPager) container).addView(page,0);
            return page;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view==o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View)object);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        if(id == R.id.batman_menu){
            Batman fragment=new Batman();
            fragmentTransaction.replace(android.R.id.content, fragment).commit();
        }
        if(id == R.id.superman_menu){
            Superman fragment=new Superman();
            fragmentTransaction.replace(android.R.id.content, fragment).commit();
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
