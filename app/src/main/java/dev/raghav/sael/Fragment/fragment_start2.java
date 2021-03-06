package dev.raghav.sael.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dev.raghav.sael.R;

public class fragment_start2 extends Fragment {


    Button btn_start3;
    Button button_check;
    TextView tv_answer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_text_start_2, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Learning English");

        btn_start3=view.findViewById(R.id.button_start);
        button_check=view.findViewById(R.id.button_check);
        tv_answer=view.findViewById(R.id.tv_answer);

        btn_start3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment view_creat=new fragment_complete();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,view_creat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv_answer.setVisibility(View.VISIBLE);

            }
        });

    }

}
