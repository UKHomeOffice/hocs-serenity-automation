package com.ndelius.test.api.alfresco.model;

import com.google.api.client.util.Key;
import java.util.ArrayList;

/**
 * @author jpotts
 */
public class List<T extends Entry> {
    @Key
    public ArrayList<T> entries;

    @Key
    public Pagination pagination;
}
