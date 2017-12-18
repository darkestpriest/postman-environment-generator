package com.darkestapp.model;

import com.darkestapp.model.enums.Version;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public class Environment {

    private String id;
    private String name;
    private Value[] values;
    private long timestamp;
    @SerializedName("_postman_variable_scope")
    private String scope;
    @SerializedName("_postman_exported_at")
    private Date exportedAt;
    @SerializedName("_postman_exported_using")
    private String exportedUsing;

    public Environment(
            String id,
            String name,
            Value[] values,
            long timestamp,
            String scope,
            Date exportedAt,
            Version exportedUsing) {
        this.id = id;
        this.name = name;
        this.values = values;
        this.timestamp = timestamp;
        this.scope = scope;
        this.exportedAt = exportedAt;
        this.exportedUsing = exportedUsing.getCode();
    }

    public String getName() {
        return name;
    }

}
