package com.example.cschw04;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class KidAdd extends AppCompatActivity {

    private Kid currentKid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_add);
        currentKid = new Kid();
        initSaveButton();
        initTextChangedEvents();
        initChangeDateButton();
    }

    private void initChangeDateButton() {
        Button changeDate = (Button) findViewById(R.id.btnBirthday);
        changeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                DatePickerDialog datePickerDialog = new DatePickerDialog();
                datePickerDialog.show(fm, "DatePick");
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context. INPUT_METHOD_SERVICE );
        EditText editName = (EditText) findViewById(R.id. editName );
        imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
    }

    //@Override
    public void didFinishDatePickerDialog(Time selectedTime) {
        TextView birthDay = (TextView) findViewById(R.id.textBirthday);
        birthDay.setText(DateFormat.format("MM/dd/yyyy", selectedTime.toMillis(false)).toString());
        currentKid.setBirthday(selectedTime);
    }

    private void initSaveButton(){
        Button saveButton = (Button) findViewById(R.id. buttonSave );
        saveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KidDataSource ds = new KidDataSource(KidAdd.this ); //1
                ds.open(); //2
                boolean wasSuccessful = false; //3
                if ( currentKid .getContactID()==-1) { //4
                    wasSuccessful = ds.insertKid( currentKid );
                    int newId = ds.getLastKidId();
                    currentKid.setContactID(newId);
                }
                else {
                    wasSuccessful = ds.updateKid( currentKid );
                }
                ds.close(); //5
                if (wasSuccessful) { //6
                    ToggleButton editToggle = (ToggleButton) findViewById(R.id. toggleButtonEdit );
                    editToggle.toggle();
                    setForEditing(false);
                }
            }
        });
    }

    private void initTextChangedEvents(){
        final EditText contactName = (EditText) findViewById(R.id.editName ); //1
        contactName.addTextChangedListener( new TextWatcher() { //2
            public void afterTextChanged(Editable s) { //3
                currentKid.setContactName(contactName.getText().toString()); //4
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) { //5
                // Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) { //6
                // Auto-generated method stub
            }
        });

        final EditText cellNumber = (EditText) findViewById(R.id.editCell);
        cellNumber.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentKid.setCellNumber(cellNumber.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub

            }
        });

        cellNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    private void setForEditing(boolean enabled) {
        EditText editName = (EditText) findViewById(R.id.editName);
        EditText editCell = (EditText) findViewById(R.id.editCell);
        EditText editEmail = (EditText) findViewById(R.id.editEMail);
        Button buttonChange = (Button) findViewById(R.id.btnBirthday);
        Button buttonSave = (Button) findViewById(R.id.buttonSave);

        editName.setEnabled(enabled);
        editCell.setEnabled(enabled);
        editEmail.setEnabled(enabled);
        buttonChange.setEnabled(enabled);
        buttonSave.setEnabled(enabled);

        if (enabled) {
            editName.requestFocus();
        }
        else {
            ScrollView s = (ScrollView) findViewById(R.id.scrollView1);
            s.fullScroll(ScrollView.FOCUS_UP);
            s.clearFocus();
        }

    }
}
