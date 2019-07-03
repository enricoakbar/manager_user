package com.ena.managemenapk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ena.managemenapk.Model.Users;
import com.ena.managemenapk.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity
{
    private Button loginButton;
    private ProgressDialog loadingBar;
    private EditText InputUserName, InputPassword;
    private Button LoginButton, mainLoginButton;
    private TextView AdminLink, NotAdminLink;


    private String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.main_login_btn);
        loadingBar = new ProgressDialog(this);

        Paper.init(this);

        String UserNipKey = Paper.book().read(Prevalent.UserNipKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);

        if (UserNipKey != "" && UserPasswordKey != "")
        {
            if (!TextUtils.isEmpty(UserNipKey) && !TextUtils.isEmpty(UserPasswordKey))
            {
                AllowAccess(UserNipKey, UserPasswordKey);

                loadingBar.setTitle("Already Logged In");
                loadingBar.setMessage("Please Wait...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }
        LoginButton = (Button) findViewById(R.id.login_btn);
        mainLoginButton = (Button) findViewById(R.id.main_login_btn);
        InputPassword = (EditText) findViewById(R.id.login_password_input);
        InputUserName = (EditText) findViewById(R.id.login_username_input);
        loadingBar = new ProgressDialog(this);
        AdminLink = (TextView) findViewById(R.id.admin_panel_link);
        NotAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);

        chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me_chkb);


        mainLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setVisibility(View.VISIBLE);
                InputPassword.setVisibility(View.VISIBLE);
                InputUserName.setVisibility(View.VISIBLE);
                mainLoginButton.setVisibility(View.GONE);
                chkBoxRememberMe.setVisibility(View.VISIBLE);
                AdminLink.setVisibility(View.VISIBLE);
            }
        });
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                chkBoxRememberMe.setVisibility(View.INVISIBLE);
                parentDbName = "Admins";
            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                chkBoxRememberMe.setVisibility(View.VISIBLE);
                parentDbName = "Users";
            }
        });
    }

    private void LoginUser()
    {
        String username = InputUserName.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "NIP belum diisi",Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Password belum diisi",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login");
            loadingBar.setMessage("Tolong tunggu sebentar");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(username, password);
        }

    }
    private void AllowAccessToAccount(final String username, final String password)
    {
        if (chkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserNipKey, username);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(username).exists())
                {
                    Users usersData = dataSnapshot.child(parentDbName).child(username).getValue(Users.class);

                    if (usersData.getUsername().equals(username))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            if (parentDbName.equals("Admins"))
                            {
                                Toast.makeText(MainActivity.this, "Logged in successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(MainActivity.this, AdminCategoryActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else if (parentDbName.equals("Users"))
                            {
                                Toast.makeText(MainActivity.this, "Logged in successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }
                        }
                        else {
                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Account with this "+username+" number do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void AllowAccess(final String nip, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(nip).exists())
                {
                    Users usersData = dataSnapshot.child("Users").child(nip).getValue(Users.class);

                    if (usersData.getUsername().equals(nip))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            Toast.makeText(MainActivity.this, "Logged in successfully...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            Prevalent.currentOnlineUser = usersData;
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else {
                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Account with this "+nip+" number do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
