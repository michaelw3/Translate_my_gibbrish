package com.wernerappshop.mwerner.translatemygibbrish;

/**
 * Created by mwerner on 7/23/15.
 */
public class DecodeReadyEvent {
    private String decodedString;

    DecodeReadyEvent(String decodedString){
        this.decodedString = decodedString;
    }
    String getDecodedString(){
        return(decodedString);
    }
}
