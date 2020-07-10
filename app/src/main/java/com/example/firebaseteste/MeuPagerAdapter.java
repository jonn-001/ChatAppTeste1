package com.example.firebaseteste;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MeuPagerAdapter extends FragmentStateAdapter {

    public MeuPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new ConversasFragment();
            case 1: return new ContatosFragment();
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
