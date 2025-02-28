package com.example.mycommerce.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mycommerce.Item;
import com.example.mycommerce.ItemAdapter;
import com.example.mycommerce.MainActivity;
import com.example.mycommerce.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycommerce.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    RecyclerView recyclerView;
    ArrayList<Item> itemArrayList;
    ItemAdapter itemAdapter;

    String[] names = new String[]{"test 1", "test 2", "test 3", "test 4"};
    int[] images = new int[]{
            R.drawable.onboarding_image, R.drawable.onboarding_image, R.drawable.onboarding_image, R.drawable.onboarding_image,
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.homeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        recyclerView.setHasFixedSize(true);
        itemArrayList = new ArrayList<>();

        itemAdapter = new ItemAdapter(requireContext(), itemArrayList);
        recyclerView.setAdapter(itemAdapter);

        for(int i = 0; i < names.length; i++){
            Item item = new Item(names[i], images[i]);
            itemArrayList.add(item);
        }
    itemAdapter.notifyDataSetChanged();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}