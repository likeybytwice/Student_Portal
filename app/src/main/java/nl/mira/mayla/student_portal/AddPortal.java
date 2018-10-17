package nl.mira.mayla.student_portal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPortal extends AppCompatActivity {

    //Local variables
    private EditText url;
    private EditText title;
    private Button addButton;

    final public static String URL_KEY = "URL";
    final public static String TITLE_KEY = "TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_portal_view);

        getSupportActionBar().setTitle("Add a portal");

        //Initializing varibales
        url = findViewById(R.id.url_input);
        title = findViewById(R.id.portalName_input);
        addButton = findViewById(R.id.addBtn);

        //set curser at end of text in textfield
        url.setSelection(url.getText().length());

        //When clicked on the add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (url.getText().length() > 0 && title.getText().length() > 0){

                    //An intent is an abstract description of an operation to be performed
                    Intent data = new Intent();

                    data.putExtra(URL_KEY, url.getText().toString());
                    data.putExtra(TITLE_KEY, title.getText().toString());

                    //Send the result back to the activity
                    setResult(Activity.RESULT_OK, data);

                    //Go back to the previous activity
                    finish();
                }
            }
        });
    }
}
