package ro.pub.cs.systems.eim.Colocviu1_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_1MainActivity extends AppCompatActivity {
    private Button northButton;
    private Button eastButton;
    private Button westButton;
    private Button southButton;
    private TextView clickedButtonsTextView;
    private int numberOfClicks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_main);
        northButton = findViewById(R.id.north_button);
        eastButton = findViewById(R.id.east_button);
        westButton = findViewById(R.id.west_button);
        southButton = findViewById(R.id.south_button);
        clickedButtonsTextView = findViewById(R.id.clickedButtonsTextView);
        CardinalButtonsClickHandler clickHandler = new CardinalButtonsClickHandler();
        northButton.setOnClickListener(clickHandler);
        eastButton.setOnClickListener(clickHandler);
        westButton.setOnClickListener(clickHandler);
        southButton.setOnClickListener(clickHandler);
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
            Log.d("Number of clicks", String.valueOf(numberOfClicks));
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
}