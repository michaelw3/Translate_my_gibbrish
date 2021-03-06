package com.wernerappshop.mwerner.translatemygibbrish;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import de.greenrobot.event.EventBus;

/**
 * Created by mwerner on 7/23/15.
 */

public class Translate extends Fragment {
    private String value;
    private String result;
    private StringBuilder sb = new StringBuilder();
    private Boolean isStarted = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle extra = getArguments();
//        value = extra.getString("decodeString");
        setRetainInstance(true);
        MainActivity activity = (MainActivity) getActivity();
        value = activity.getDecodeString();
    Log.i("Fragment", "oncreate");
        if(!isStarted){
            isStarted = true;
            Log.i("isStarted", isStarted.toString());
            new DecodeStringHebToEng().run();
        }
    }

    class  DecodeStringHebToEng extends Thread{
        @Override
        public void run() {
            Log.i("runnable", "i Run!");
            Log.i("value", value);
            for (char c : value.toCharArray()) {
                sb.append(returnTempValue(c));
                Log.d("String decoded",sb.toString());
            }
            EventBus.getDefault().post(new DecodeReadyEvent(sb.toString()));
            super.run();
        }
    }
        private  char returnTempValue(char c){
            switch (c) {
                case 'א':
                    return 't';
                case 'ב':
                    return 'c';
                case 'ג':
                    return 'd';
                case 'ד':
                    return 's';
                case 'ה':
                    return 'v';
                case 'ו':
                    return 'u';
                case 'ז':
                    return 'z';
                case 'ח':
                    return 'j';
                case 'ט':
                    return 'y';
                case 'י':
                    return 'h';
                case 'כ':
                    return 'f';
                case 'ך':
                    return 'l';
                case 'ל':
                    return 'k';
                case 'מ':
                    return 'n';
                case 'ם':
                    return 'o';
                case 'נ':
                    return 'b';
                case 'ן':
                    return 'i';
                case 'ס':
                    return 'x';
                case 'ע':
                    return 'g';
                case 'פ':
                    return 'p';
                case 'ף':
                    return ';';
                case 'צ':
                    return 'm';
                case 'ץ':
                    return '.';
                case 'ק':
                    return 'e';
                case 'ר':
                    return 'r';
                case 'ש':
                    return 'a';
                case 'ת':
                    return ',';
                case ' ':
                    return ' ';
                case '/':
                    return 'q';
                case '\'':
                    return 'w';
                default:
                    return ' ';
            }


        }



}
