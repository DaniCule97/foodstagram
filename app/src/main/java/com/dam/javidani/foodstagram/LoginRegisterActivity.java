package com.dam.javidani.foodstagram;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginRegisterActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        bundle = new Bundle();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class RegisterFragment extends Fragment implements View.OnClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private Button registerButton;

        public RegisterFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static RegisterFragment newInstance(int sectionNumber) {
            RegisterFragment fragment = new RegisterFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.register_fragment, container, false);

            registerButton = rootView.findViewById(R.id.registerButton);
            registerButton.setOnClickListener(this);
            return rootView;
        }

        @Override
        public void onClick(View view) {



            Intent intent = new Intent(getActivity(), MainActivity.class);
            // intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public static class LoginFragment extends Fragment implements View.OnClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        TextView login, password;

        String IP = "http://dblazquez.iessv.es/ProyectoAndroid";
        String POST = IP +  "/obtener_login_correcto.php";
        public boolean canLogin;

        public LoginFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static LoginFragment newInstance(int sectionNumber) {
            LoginFragment fragment = new LoginFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.login_fragment, container, false);

            Button loginButton = rootView.findViewById(R.id.loginButton);
            loginButton.setOnClickListener(this);

            login = rootView.findViewById(R.id.emailLogin);
            password = rootView.findViewById(R.id.passwordLogin);

            return rootView;
        }

        @Override
        public void onClick(View view) {
            /*
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            */
            LoginWebService loginWebService  = new LoginWebService();
            loginWebService.execute(POST, "" + login.getText(), "" + password.getText());
        }

        class LoginWebService extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {
                String devuelve = "";
                try {

                    HttpURLConnection urlConn;

                    DataOutputStream printout;
                    DataInputStream input;
                    URL url = new URL(strings[0]);
                    urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    urlConn.setUseCaches(false);
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    urlConn.setRequestProperty("Accept", "application/json");
                    urlConn.connect();
                    //Creo el Objeto JSON
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("usuario", strings[1]);
                    jsonParam.put("contrasenya", strings[2]);
                    // Envio los parámetros post.
                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();

                    // Fin del envío de información al servicio
                    // Comienzo de la recepción de datos
                    int respuesta = urlConn.getResponseCode();

                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        String line;
                        BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                        while ((line = br.readLine()) != null) {
                            result.append(line);
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");

                        if (resultJSON != null) {
                            if (resultJSON.equals("1")) {
                                devuelve = "1";
                            } else if (resultJSON.equals("2")) {
                                devuelve = "2";
                            }
                        }
                    }
                    return devuelve;

                } catch (Exception e) {
                }
                return devuelve;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s != null) {
                    if (s.equals("1")) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        // intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        canLogin = false;
                        // errors.setText("LOGIN INCORRECTO");
                    }
                }
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return LoginFragment.newInstance(position + 1);
            }
            else if(position == 1){
                return RegisterFragment.newInstance(position + 1);
            }

            return LoginFragment.newInstance(0);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "Login";
                case 1:
                    return "Register";
            }
            return null;
        }
    }
}
