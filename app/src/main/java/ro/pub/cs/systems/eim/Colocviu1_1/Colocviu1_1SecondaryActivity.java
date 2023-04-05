package ro.pub.cs.systems.eim.Colocviu1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_1SecondaryActivity extends AppCompatActivity {
    Button registerButton;
    Button cancelButton;
    TextView commandsTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_secondary);
        registerButton = findViewById(R.id.register_button);
        cancelButton = findViewById(R.id.cancel_button);
        ClickHandler clickHandler = new ClickHandler();
        registerButton.setOnClickListener(clickHandler);
        cancelButton.setOnClickListener(clickHandler);
        commandsTextView = findViewById(R.id.commandsTextView);
        Intent intentFromMain = getIntent();
        if (intentFromMain != null && intentFromMain.hasExtra("commands")) {
            commandsTextView.setText(intentFromMain.getStringExtra("commands"));
        }
    }

    private class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register_button:
                    setResult(10);
                    break;
                case R.id.cancel_button:
                    setResult(20);
                    break;
            }
            finish();
        }
    }
}