package nl.mira.mayla.student_portal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PortalAdapter.PortalClickListener {

    //Local variables
    private List<Portal> mPortals;
    private PortalAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public static int position;
    final public static String LINK = "websiteLink";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Student Portal");

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        //Create some portals
        mPortals = new ArrayList<>();
        mPortals.add(new Portal("VLO", "https://vlo.informatica.hva.nl/"));
        mPortals.add(new Portal("HvA", "http://www.hva.nl/"));
        mPortals.add(new Portal("Rooster", "https://rooster.hva.nl/"));
        mPortals.add(new Portal("A-Z lijst", "https://student.hva.nl/hbo-ict/a-z/az.html"));
        updateUI();

        //Start the add portal activity when we clicked on the add button.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //An intent is an abstract description of an operation to be performed
                //Initialize an Intent to navigate to the add portal activity
                startActivityForResult(new Intent(MainActivity.this, AddPortal.class), 10);

            }
        });

    }

    //update the user interface
    private void updateUI(){
        if(mAdapter == null){
            mAdapter = new PortalAdapter(mPortals, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //When returning back to the main activity, retrieve the url and title
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        //Check if the result code is the right one
        if (resultCode == Activity.RESULT_OK) {

            //Check if the request code is correct save data
            if (requestCode == 10) {
                String url = data.getStringExtra(AddPortal.URL_KEY);
                String title = data.getStringExtra(AddPortal.TITLE_KEY);

                Portal portal = new Portal(title, url);
                mPortals.add(portal);
                mAdapter.notifyDataSetChanged();
                updateUI();
            }
        }
    }

    //When portal gets clicked
    public void portalOnClick(int i) {
        Intent intent = new Intent(MainActivity.this, WebView.class);
        position = i;
        intent.putExtra(MainActivity.LINK, mPortals.get(i).getPortalUrl());
        startActivity(intent);
    }
}
