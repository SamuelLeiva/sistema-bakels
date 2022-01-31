package com.example.proyectofinal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyectofinal.database.DatabaseHelper;
import com.example.proyectofinal.database.UserData;
import com.example.proyectofinal.utils.HelperDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends Fragment {
    private DatabaseHelper databaseHelper;
    private Context context;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.register_fragment, container, false);

        final TextView loginTv = view.findViewById(R.id.link_login_text);

        final TextInputLayout usernameTextInput = view.findViewById(R.id.register_username_text_input);
        final TextInputEditText usernameTextEditText = view.findViewById(R.id.register_username_edit_text);

        final TextInputLayout passwordTextInput = view.findViewById(R.id.register_password_text_input);
        final TextInputEditText passwordTextEditText = view.findViewById(R.id.register_password_edit_text);

        MaterialButton registerButton = view.findViewById(R.id.register_button);

        databaseHelper = new DatabaseHelper(this.getContext());

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername = usernameTextEditText.getText().toString();
                String getPassword = passwordTextEditText.getText().toString();

                UserData data = new UserData(getUsername, getPassword);

                boolean userExists = databaseHelper.findUser(data);

                if(userExists){
                    openDialog("Un usuario con ese email ya existe");
                } else {
                    databaseHelper.registerUser(new UserData(getUsername, getPassword));
                    openDialog("Nuevo usuario registrado.");
                }
            }
        });

        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new LoginFragment(), false);
            }
        });

//        passwordTextEditText.setOnKeyListener(new View.OnKeyListener(){
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent){
//                if(isPasswordValid(passwordTextEditText.getText())){
//                    passwordTextInput.setError(null);
//                }
//                return false;
//            }
//        });

        return view;
    }

    public void openDialog(String message){
        HelperDialog dialog = new HelperDialog(message);
        dialog.show(getFragmentManager(), "Example dialog");
    }
}
