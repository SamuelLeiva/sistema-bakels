package com.example.proyectofinal;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyectofinal.database.DatabaseHelper;
import com.example.proyectofinal.database.UserData;
import com.example.proyectofinal.utils.HelperDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    private Context context;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        final TextInputLayout usernameTextInput = view.findViewById(R.id.username_text_input);
        final TextInputEditText usernameTextEditText = view.findViewById(R.id.username_edit_text);

        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordTextEditText = view.findViewById(R.id.password_edit_text);

        final TextView registerTv = view.findViewById(R.id.link_register_text);

        MaterialButton loginButton = view.findViewById(R.id.login_button);
        //MaterialButton registerButton = view.findViewById(R.id.register_button);

        databaseHelper = new DatabaseHelper(this.getContext());

//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String getUsername = usernameTextEditText.getText().toString();
//                String getPassword = passwordTextEditText.getText().toString();
//
//                UserData data = new UserData(getUsername, getPassword);
//
//                boolean userExists = databaseHelper.findUser(data);
//
//                if(userExists){
//                    openDialog("Un usuario con ese email ya existe");
//                } else {
//                    databaseHelper.registerUser(new UserData(getUsername, getPassword));
//                    openDialog("Nuevo usuario registrado.");
//                }
//            }
//        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getUsername = usernameTextEditText.getText().toString();
                String getPassword = passwordTextEditText.getText().toString();
                boolean answer = databaseHelper.loginUser(new UserData(getUsername, getPassword));
                if(!isPasswordValid(passwordTextEditText.getText())) {
                    passwordTextInput.setError(getString(R.string.error_password));
                } else if(!answer) {
                    Log.e("DB", "Ese usuario no existe");
                    openDialog("Usuario o contraseña incorrecta.");
                } else
                 {
                    passwordTextInput.setError(null);
                    ((NavigationHost) getActivity()).navigateTo(new ProductListFragment(), false);
                }
            }
        });

        passwordTextEditText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent){
                if(isPasswordValid(passwordTextEditText.getText())){
                    passwordTextInput.setError(null);
                }
                return false;
            }
        });

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new RegisterFragment(), false);
            }
        });

        return view;
    }

    private boolean isPasswordValid(@Nullable Editable text){
        return text != null && text.length() >= 5;
    }

    public void openDialog(String message){
        HelperDialog dialog = new HelperDialog(message);
        dialog.show(getFragmentManager(), "Example dialog");
    }
}
