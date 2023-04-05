package ro.pub.cs.systems.eim.Colocviu1_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_1MainActivity extends AppCompatActivity {
    private Button northButton;
    private Button eastButton;
    private Button westButton;
    private Button southButton;
    private Button navigateToSecondaryActivityButton;
    private TextView clickedButtonsTextView;
    private int numberOfClicks;
    CommandsBroadcastReceiver commandsBroadcastReceiver;
    IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_main);
        northButton = findViewById(R.id.north_button);
        eastButton = findViewById(R.id.east_button);
        westButton = findViewById(R.id.west_button);
        southButton = findViewById(R.id.south_button);
        navigateToSecondaryActivityButton = findViewById(R.id.navigate_to_secondary_activity_button);
        clickedButtonsTextView = findViewById(R.id.clickedButtonsTextView);
        CardinalButtonsClickHandler clickHandler = new CardinalButtonsClickHandler();
        NavigateToSecondaryActivityClickHandler navigateToSecondaryActivityClickHandler = new NavigateToSecondaryActivityClickHandler();
        northButton.setOnClickListener(clickHandler);
        eastButton.setOnClickListener(clickHandler);
        westButton.setOnClickListener(clickHandler);
        southButton.setOnClickListener(clickHandler);
        navigateToSecondaryActivityButton.setOnClickListener(navigateToSecondaryActivityClickHandler);
        commandsBroadcastReceiver = new CommandsBroadcastReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction("broadcastAction");
        if (savedInstanceState != null && savedInstanceState.containsKey("numberOfClicksState")) {
            numberOfClicks = savedInstanceState.getInt("numberOfClickState");
        } else {
            numberOfClicks = 0;
        }
        Log.d("Number of clicks", String.valueOf(numberOfClicks));
    }

    private class CardinalButtonsClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            clickedButtonsTextView.append(((Button)view).getText() + ", ");
            numberOfClicks += 1;
            if (numberOfClicks > 3) {
                Intent serviceIntent = new Intent(getApplicationContext(), Colocviu1_1Service.class);
                serviceIntent.putExtra("broadcast", clickedButtonsTextView.getText().toString());
                getApplicationContext().startService(serviceIntent);
            }
            Log.d("Number of clicks", String.valueOf(numberOfClicks));
        }
    }

    private class NavigateToSecondaryActivityClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent navigateIntent = new Intent(getApplicationContext(), Colocviu1_1SecondaryActivity.class);
            navigateIntent.putExtra("broadcast", clickedButtonsTextView.getText().toString());
            clickedButtonsTextView.setText("");
            numberOfClicks = 0;
            startActivityForResult(navigateIntent, 1);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("numberOfClickState", numberOfClicks);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("numberOfClickState")) {
            numberOfClicks = savedInstanceState.getInt("numberOfClickState");
        } else {
            numberOfClicks = 0;
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            String buttonClicked;
            if (resultCode == 10) {
                buttonClicked = new String("Register button");
            } else {
                buttonClicked = new String("Cancel button");
            }
            Toast.makeText(this, "The button clicked was " + buttonClicked, Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        Intent serviceIntent = new Intent(this, Colocviu1_1Service.class);
        getApplicationContext().stopService(serviceIntent);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(commandsBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(commandsBroadcastReceiver);
        super.onPause();
    }
}