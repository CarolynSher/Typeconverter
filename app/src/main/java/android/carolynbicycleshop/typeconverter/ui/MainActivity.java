package android.carolynbicycleshop.typeconverter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.carolynbicycleshop.typeconverter.DAO.ThingDAO;
import android.carolynbicycleshop.typeconverter.Database.MyRepository;
import android.carolynbicycleshop.typeconverter.Entity.ThingEntity;
import android.carolynbicycleshop.typeconverter.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private MyRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository= new MyRepository(getApplication());
  //      populateData();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainDetailActivity.class);
                //  intent.putExtra("thingID",mThingViewModel.lastID()+1);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ThingAdapter adapter = new ThingAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setWords(repository.getAllThings());



    }

    public void populateData(){
        //MyRepository repository= new MyRepository(getApplication());
        ThingEntity part = new ThingEntity(1, "wheel", new Date());
        repository.insert(part);
        ThingEntity part2 = new ThingEntity(2, "brake", new Date());
        repository.insert(part2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh_menu) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final ThingAdapter adapter = new ThingAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setWords(repository.getAllThings());

            return true;
        }
        if (id == R.id.sample_data) {
            populateData();
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final ThingAdapter adapter = new ThingAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setWords(repository.getAllThings());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}