package br.edu.utfpr.activitycallandresultreturn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowData extends AppCompatActivity {

    // File > Settings > Editor > Color Scheme > Live Templates > Android > Key
    // then edit "key": switch "private" to "public"
    // then erase "KEY"
    public static final String NAME = "NAME";
    public static final String HAS_A_CAR = "HAS_A_CAR";
    public static final String ROLE = "ROLE";
    public static final String MODE = "MODE";
    public static final String RATING = "RATE";

    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        setTitle(getString(R.string.registered_data));

        Intent intent = getIntent();

        Bundle bundle  = intent.getExtras();

        if (bundle != null) {
            String name = bundle.getString(NAME, getString(R.string.not_registered));
            boolean hasACar = bundle.getBoolean(HAS_A_CAR);
            int id = bundle.getInt(ROLE, -1);

            String output = getString(R.string.name) + ": " + name + "\n";
            output += getString(R.string.has_a_car) + "? ";
            output += (hasACar ? getString(R.string.yes) : getString(R.string.no)) + "\n";

            switch(id) {
                case R.id.radioButtonStudent:
                    output += getString(R.string.student);
                    break;
                case R.id.radioButtonTeacher:
                    output += getString(R.string.teacher);
                    break;
                default:
                    output += getString(R.string.no_role_selected);
            }

            TextView textViewData = findViewById(R.id.textViewData);
            textViewData.setText(output);

            mode = bundle.getInt(MODE, 0);
        }

    }

    private void terminate() {
        if (mode == MainActivity.ASK_RATING) {
            Intent intent = new Intent();

            intent.putExtra(RATING, 1000);

            setResult(Activity.RESULT_OK, intent);
        }

        finish();
    }

    @Override
    public void onBackPressed() {
        terminate();
    }

}