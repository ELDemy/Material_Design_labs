package com.dmy.mynavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class FormFragment extends Fragment {
    Button enterBtn;
    TextInputLayout userNameTextInput;
    RadioGroup radioGroup;
    CheckBox checkBoxJava;
    CheckBox checkBoxCpp;
    CheckBox checkBoxCsh;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userNameTextInput = view.findViewById(R.id.txtInput_user);
        radioGroup = view.findViewById(R.id.radio_gender_group);
        enterBtn = view.findViewById(R.id.btn_enter);
        checkBoxJava = view.findViewById(R.id.checkBox_java);
        checkBoxCpp = view.findViewById(R.id.checkBox_cpp);
        checkBoxCsh = view.findViewById(R.id.checkBox_csh);


        enterBtn.setOnClickListener((newView)->{
//            navigateLegacy(view);
            navigate(view);
        });
    }

    private void navigateLegacy(View view){
        Navigation.findNavController(view).navigate(R.id.action_formFragment_to_detailsFragment);

    }

    private void navigate(View view){
        String userName = userNameTextInput.getEditText().getText().toString();
        String gender = getGender(view);
        ArrayList<String> languages = getLanguages(view);


        UserData userData = new UserData(userName,"",gender, languages);

        FormFragmentDirections.ActionFormFragmentToDetailsFragment action =
                FormFragmentDirections.actionFormFragmentToDetailsFragment(userData);

        Navigation.findNavController(view).navigate(action);
    }

    private String getGender(View view){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        Log.i("Gender",Integer.toString( selectedId));
        RadioButton selected = view.findViewById(selectedId);
        return selected.getText().toString();
    }

    private ArrayList<String>  getLanguages(View view){
        ArrayList<String> languages = new ArrayList<>();
        if(checkBoxCpp.isChecked()){
            languages.add(checkBoxCpp.getText().toString());
        }
        if(checkBoxJava.isChecked()){
            languages.add(checkBoxJava.getText().toString());
        }
        if(checkBoxCsh.isChecked()){
            languages.add(checkBoxCsh.getText().toString());
        }
        return languages;
    }
}