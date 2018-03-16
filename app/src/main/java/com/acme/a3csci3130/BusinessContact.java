package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class BusinessContact implements Serializable {

    public String uid;
    public String businessNumber; // required, 9-digit number
    public String name; // required, 2-48 characters
    public String primaryBusiness; // required, {Fisher, Distributor, Processor, Fish Monger}
    public String address; //  <50 characters
    public String province_territory;

    public BusinessContact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Description: constructor for business contact
     * @param uid unique ID
     * @param name name or business
     * @param businessNumber number of business
     * @param primaryBusiness businesses main activity
     * @param address businesses address
     * @param province_territory the province the business resides in
     */
    public BusinessContact(String uid, String name, String businessNumber, String primaryBusiness,
                           String address, String province_territory){
        this.uid = uid;
        this.name = name;
        this.businessNumber = businessNumber;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province_territory = province_territory;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("businessName", businessNumber);
        result.put("primaryBusiness", primaryBusiness);
        result.put("address", address);
        result.put("province_territory", province_territory);
        return result;
    }
}
