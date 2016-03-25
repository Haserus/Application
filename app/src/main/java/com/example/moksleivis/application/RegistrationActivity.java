package com.example.moksleivis.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private EditText studentNameEditText;
    private Spinner schoolTypeSpinner;
    ArrayAdapter<CharSequence> adapter;
    String boardType;
    String name;
    ArrayList<String> selection = new ArrayList<String>();
    TextView selection_text;
    String selection_view = "Nenoriu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        this.studentNameEditText = (EditText) findViewById(R.id.student_name);

        name = this.studentNameEditText.getText().toString();
        boolean cancel = false;
        View focusView = null;

        if(TextUtils.isEmpty(name)) {
            studentNameEditText.setError("Fill this field");
            focusView = studentNameEditText;
            cancel = true;
        }

        schoolTypeSpinner = (Spinner)findViewById(R.id.spinner_school_type);
        adapter = ArrayAdapter.createFromResource(this, R.array.school_names,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolTypeSpinner.setAdapter(adapter);
        schoolTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boardType = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(getBaseContext(), boardType + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), studentNameEditText.getText() + "\n"+ boardType + "\n" + selection_view, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void finalSelection(View view){
        String final_selection = "";
        for(String Selections : selection){
            final_selection = final_selection + Selections + "\n";
        }
    selection_text.setText(final_selection);
        selection_text.setEnabled(true);
 }

    public void selectItem(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.checkBox:
                if(checked){
                    selection.add("Noriu");
                    selection_view = "Noriu";
                }
                else{
                    selection.remove("Noriu");
                    selection_view = "Nenoriu";
                }
                break;
        }
    }

    public static List<SchoolType> getSchoolTypes() {
        ArrayList<SchoolType> result = new ArrayList<SchoolType>();

        result.add(SchoolType.KTU);
        result.add(SchoolType.KITM);
        result.add(SchoolType.VDU);
        result.add(SchoolType.SAULESGIMNAZIJA);

        return result;
    }

    enum SchoolType{
        KTU(0),
        KITM(1),
        VDU(2),
        SAULESGIMNAZIJA(3);

        private final int value;

        private SchoolType(int value) {this.value = value;}

        public int getValue() { return value; }

        public static SchoolType valueOf(int value){
            for (SchoolType bt : SchoolType.values()) {
                if (bt.value == value){
                    return bt;
                }
            }
            return null;
        }

    }


    private boolean isNameValid(String name) {
        //TODO: Replace this with your own logic
        return name.contains(" ");
    }
}
