package br.edu.utfpr.activitycallandresultreturn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private CheckBox checkBoxHasACar;
    private RadioGroup radioGroupRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        checkBoxHasACar = findViewById(R.id.checkBoxHasACar);
        radioGroupRole = findViewById(R.id.radioGroupRole);
    }

    public void submit(View view) {
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

        startActivity(intent);
    }

}