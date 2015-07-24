package com.wernerappshop.mwerner.translatemygibbrish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

public class MainActivity extends ActionBarActivity {
    private Button translateButton = null;
    private EditText textToDecode = null;
    private String decodeString = null;
    private TextView decodedText = null;
    //setting fragment data members
    private static final String MODEL_TAG = "model";
    private Translate mFrag=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFrag = (Translate)getFragmentManager().findFragmentByTag(MODEL_TAG);

        if (mFrag == null){
            mFrag = new Translate();
            getFragmentManager().beginTransaction().add(mFrag,MODEL_TAG).commit();
        }
        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        //findig edittext and insert shared text
        textToDecode = (EditText) findViewById(R.id.textToDecode);
        translateButton = (Button) findViewById(R.id.button);


        decodedText = (TextView) findViewById(R.id.decodedText);
        setDecodeString();
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
        }


        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Activity", "Button clicked");
                setDecodeString();
                //setting up model fragment for
                Bundle data = new Bundle();
                data.putString("decodeString", decodeString);
                if (mFrag != null){
                    mFrag = null;
                    mFrag = new Translate();
                    getFragmentManager().beginTransaction().add(mFrag,MODEL_TAG).commit();
                }

            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    // Called in Android UI's main thread
    public void onEventMainThread(DecodeReadyEvent event) {
        decodedText.setText(event.getDecodedString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared
            textToDecode.setText(sharedText, TextView.BufferType.EDITABLE);
        }
    }
    public String getDecodeString(){
        return decodeString;
    }
    private void setDecodeString(){
        decodeString = textToDecode.getText().toString();
    }
}
