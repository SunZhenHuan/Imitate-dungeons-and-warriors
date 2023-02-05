package com;

import java.util.Locale;
import java.util.UUID;

public class UUid {
    static UUID uuid=UUID.randomUUID();

    @Override
    public  String toString() {
        return  ""+uuid ;
    }
}
