package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by meghalagrawal on 10/07/17.
 */

public class StateUtils {

    public static List<String> getStateList() {

        List<String> stateList = new ArrayList<>();

        stateList.add("Andaman and Nicobar Islands");
        stateList.add("Andhra Pradesh");
        stateList.add("Andhra Pradesh (New)");
        stateList.add("Arunachal Pradesh");
        stateList.add("Assam");
        stateList.add("Bihar");
        stateList.add("Chandigarh");
        stateList.add("Chattisgarh");
        stateList.add("Dadra and Nagar Haveli");
        stateList.add("Daman and Diu");
        stateList.add("Delhi");
        stateList.add("Goa");
        stateList.add("Gujarat");
        stateList.add("Haryana");
        stateList.add("Himachal Pradesh");
        stateList.add("Jammu and Kashmir");
        stateList.add("Jharkhand");
        stateList.add("Karnataka");
        stateList.add("Kerala");
        stateList.add("Lakshadweep Islands");
        stateList.add("Madhya Pradesh");
        stateList.add("Maharashtra");
        stateList.add("Manipur");
        stateList.add("Meghalaya");
        stateList.add("Mizoram");
        stateList.add("Nagaland");
        stateList.add("Odisha ");
        stateList.add("Pondicherry");
        stateList.add("Punjab");
        stateList.add("Rajasthan");
        stateList.add("Sikkim");
        stateList.add("Tamil Nadu");
        stateList.add("Telangana");
        stateList.add("Tripura");
        stateList.add("Uttar Pradesh");
        stateList.add("Uttarakhand");
        stateList.add("West Bengal");

        return stateList;
    }


    public static String getStateFromGstInNumberFirstTwoDigit(String gstIn) {


        String gstInFirstTwoLetters = gstIn.substring(0, 2);

        Log.d("ProductFragment", gstInFirstTwoLetters);


        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("35", "Andaman and Nicobar Islands");
        hashMap.put("28", "Andhra Pradesh");
        hashMap.put("37", "Andhra Pradesh (New)");
        hashMap.put("12", "Arunachal Pradesh");
        hashMap.put("18", "Assam");
        hashMap.put("10", "Bihar");
        hashMap.put("04", "Chandigarh");
        hashMap.put("22", "Chattisgarh");
        hashMap.put("26", "Dadra and Nagar Haveli");
        hashMap.put("25", "Daman and Diu");
        hashMap.put("07", "Delhi");
        hashMap.put("30", "Goa");
        hashMap.put("24", "Gujarat");
        hashMap.put("06", "Haryana");
        hashMap.put("02", "Himachal Pradesh");
        hashMap.put("01", "Jammu and Kashmir");
        hashMap.put("20", "Jharkhand");
        hashMap.put("29", "Karnataka");
        hashMap.put("32", "Kerala");
        hashMap.put("31", "Lakshadweep Islands");
        hashMap.put("23", "Madhya Pradesh");
        hashMap.put("27", "Maharashtra");
        hashMap.put("14", "Manipur");
        hashMap.put("17", "Meghalaya");
        hashMap.put("15", "Mizoram");
        hashMap.put("13", "Nagaland");
        hashMap.put("21", "Odisha ");
        hashMap.put("34", "Pondicherry");
        hashMap.put("03", "Punjab");
        hashMap.put("08", "Rajasthan");
        hashMap.put("11", "Sikkim");
        hashMap.put("33", "Tamil Nadu");
        hashMap.put("36", "Telangana");
        hashMap.put("16", "Tripura");
        hashMap.put("09", "Uttar Pradesh");
        hashMap.put("05", "Uttarakhand");
        hashMap.put("19", "West Bengal");


        if (hashMap.containsKey(gstInFirstTwoLetters)) {
            return hashMap.get(gstInFirstTwoLetters);
        } else {
            return null;
        }

    }
}
