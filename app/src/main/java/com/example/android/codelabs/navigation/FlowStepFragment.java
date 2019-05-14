package com.example.android.codelabs.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class FlowStepFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
//        int flowStepNumber = getArguments().getInt("flowStepNumber");

        // TODO STEP 8 - Use type-safe arguments - remove previous line!
        FlowStepFragmentArgs safeArgs = FlowStepFragmentArgs.fromBundle(getArguments());
        int flowStepNumber = safeArgs.getFlowStepNumber();
        // TODO END STEP 8

        if (2 == flowStepNumber) {
            return inflater.inflate(R.layout.flow_step_two_fragment, container, false);
        }
        return inflater.inflate(R.layout.flow_step_one_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getView().findViewById(R.id.next_button).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.next_action));
    }
}
