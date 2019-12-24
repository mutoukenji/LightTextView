package tech.yaog.testsimpletextview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import tech.yaog.widgets.LightTextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout mainContainer = findViewById(R.id.main_container);

        LightTextView lightTextView = new LightTextView(this);
        lightTextView.setText("A");
        lightTextView.setTextColor(Color.RED);
        lightTextView.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, this.getResources().getDisplayMetrics()));
        lightTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mainContainer.addView(lightTextView);

        LightTextView test = findViewById(R.id.test);
        LightTextView test2 = findViewById(R.id.test2);
        test.postDelayed(new Runnable() {
            @Override
            public void run() {
                test.setText("T");
                lightTextView.setText("ABCDEFG");
                test2.setText("CCAA");
                lightTextView.requestLayout();
            }
        }, 1000);
    }
}
