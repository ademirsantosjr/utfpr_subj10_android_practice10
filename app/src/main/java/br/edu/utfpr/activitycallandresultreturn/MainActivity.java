package br.edu.utfpr.activitycallandresultreturn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private CheckBox checkBoxHasACar;
    private RadioGroup radioGroupRole;

    public static final int ASK_RATING = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        checkBoxHasACar = findViewById(R.id.checkBoxHasACar);
        radioGroupRole = findViewById(R.id.radioGroupRole);
    }

    public void submitAskingForRating(View view) {
        callScreen2(true);
    }

    public void submitWithoutAskingRating(View view) {
        callScreen2(false);
    }

    public void callScreen2(boolean askForRating) {
        Intent intent = new Intent(this, ShowData.class);
        String name = editTextName.getText().toString();

        if (!name.equals("")) {
            intent.putExtra(ShowData.NAME, name);
        }

        intent.putExtra(ShowData.HAS_A_CAR, checkBoxHasACar.isChecked());

        int id = radioGroupRole.getCheckedRadioButtonId();

        if (id != -1) {
            intent.putExtra(ShowData.ROLE, id);
        }

        if (askForRating) {
            intent.putExtra(ShowData.MODE, ASK_RATING);
            startActivityForResult(intent, ASK_RATING);
        } else {
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        if (requestCode == ASK_RATING && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();

            if(bundle != null) {
                int rating = bundle.getInt(ShowData.RATING);

                Toast.makeText(this,
                        getString(R.string.rating) + ": " + rating,
                        Toast.LENGTH_LONG).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}