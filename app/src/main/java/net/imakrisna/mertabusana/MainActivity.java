package net.imakrisna.mertabusana;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import net.imakrisna.mertabusana.fragment.BookingFragment;
import net.imakrisna.mertabusana.fragment.HomeFragment;
import net.imakrisna.mertabusana.fragment.ProfilFragment;

/**
 * Created by Urip on 12/7/2017.
 */

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_main,new HomeFragment(),HomeFragment.class.getSimpleName())
                .commit();
        init();
    }

    private void init() {
        bnv=(BottomNavigationView)findViewById(R.id.bnv);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_main,new HomeFragment(),HomeFragment.class.getSimpleName())
                                .commit();
                        break;
                    case R.id.menu_book:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_main,new BookingFragment(),BookingFragment.class.getSimpleName())
                                .commit();
                        break;
                    case R.id.menu_profil:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_main,new ProfilFragment(),ProfilFragment.class.getSimpleName())
                                .commit();
                        break;
                }
                return true;
            }
        });

    }
}
