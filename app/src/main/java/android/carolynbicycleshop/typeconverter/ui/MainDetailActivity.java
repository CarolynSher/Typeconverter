package android.carolynbicycleshop.typeconverter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.carolynbicycleshop.typeconverter.Database.MyRepository;
import android.carolynbicycleshop.typeconverter.Entity.ThingEntity;
import android.carolynbicycleshop.typeconverter.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainDetailActivity extends AppCompatActivity {
    final Calendar myCalendarStart = Calendar.getInstance();
    EditText startText;
    private EditText mEditName;
    private EditText mEditID;
    DatePickerDialog.OnDateSetListener startDate;
    private MyRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);
        repository=new MyRepository(getApplication());
        mEditName=findViewById(R.id.name);
        mEditID=findViewById(R.id.ID);
        startText=findViewById(R.id.startDate);
        if(getIntent().getStringExtra("thingName")!=null) {
            int thingNum=getIntent().getIntExtra("thingID",0);
            String thingNumString=Integer.toString(new Integer(thingNum));
            String thingString=getIntent().getStringExtra("thingDate");
            mEditName.setText(getIntent().getStringExtra("thingName"));
            mEditID.setText(thingNumString);
            startText.setText(thingString);
        }

        startText = findViewById(R.id.startDate);
        startDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                updateLabelStart();
            }

        };

        startText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainDetailActivity.this, startDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



    }
    private void updateLabelStart() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startText.setText(sdf.format(myCalendarStart.getTime()));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            String start=startText.getText().toString();

            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            Date thingDate= null;
            try {
                thingDate = sdf.parse(start);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String s=mEditID.getText().toString();
            int val=Integer.parseInt(s);
            ThingEntity newThing=new ThingEntity(val, mEditName.getText().toString(),thingDate);

            repository.insert(newThing);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}