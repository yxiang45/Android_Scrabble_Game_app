package edu.gatech.seclass.words6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity implements View.OnClickListener {
    private Button goHomeButton;
    private Intent intent;
    private Integer finalScore = 0;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.game_over);

        goHomeButton = findViewById(R.id.backToHomeFromFinalScore);
        goHomeButton.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            finalScore = b.getInt("finalScore");
        }

        TextView score = findViewById(R.id.finalScoreValue);
        score.setText(Integer.toString(finalScore));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backToHomeFromFinalScore:
                intent = new Intent(this, Words6300.class);
                startActivity(intent);
                break;

        }
    }
}
