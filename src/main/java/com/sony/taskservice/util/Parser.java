package com.sony.taskservice.util;

import org.bson.types.ObjectId;

public final class Parser {

    private Parser() {
    }

    public static ObjectId parseObjectId(String idString) {
        return new ObjectId(idString);
    }
}
