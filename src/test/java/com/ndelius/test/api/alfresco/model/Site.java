package com.ndelius.test.api.alfresco.model;

import com.google.api.client.util.Key;

/**
 * @author jpotts
 */
public class Site {

    @Key
    public String id;

    @Key
    public String title;

    @Key
    public String visibility;

    @Key
    public String description;

}
