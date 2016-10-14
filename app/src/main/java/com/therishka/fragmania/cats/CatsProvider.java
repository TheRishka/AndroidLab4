package com.therishka.fragmania.cats;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

/**
 * @author Rishad Mustafaev
 */

public class CatsProvider {

    public static final String CATS_PREFERENCES = "cats_preferences_fragmania";

    private final String[] catsNames = new String[]{"Bella", "Tigger", "Chloe", "Shadow", "Oreo",
            "Oliver", "Kitty", "Lucy", "Molly", "Jasper", "Luna", "Smokey", "Gizmo",
            "Simba", "Charlie", "Tiger", "Angel", "Jack", "Lily", "Peanut", "Toby",
            "Baby", "Loki", "Midnight", "Milo", "Princess", "Sophie", "Harley",
            "Max", "Missy", "Rocky", "Zoe", "CoCo", "Misty", "Nala", "Oscar",
            "Pepper", "Buddy", "Pumpkin", "Sasha", "Kiki", "Mittens", "Bailey",
            "Callie", "Lucky", "Patches", "Simon", "Garfield", "George", "Maggie",
            "Sebastian", "Boots", "Cali", "Felix", "Lilly", "Phoebe", "Sammy",
            "Sassy", "Tucker", "Bandit", "Dexter", "Jake", "Precious", "Romeo"};

    @NonNull
    private Hashtable<String, String> nameToPhotoMapper = new Hashtable<>();

    private static CatsProvider sInstance;

    private CatsProvider() {
    }

    public static CatsProvider getInstance() {
        if (sInstance == null) {
            sInstance = new CatsProvider();
        }
        return sInstance;
    }

    public boolean restoreData(@NonNull Context context) {
        SharedPreferences preferences = context.getSharedPreferences(CATS_PREFERENCES, Context.MODE_PRIVATE);
        boolean isExists = false;
        for (String catName : catsNames) {
            if (preferences.contains(catName)) {
                isExists = true;
                if (!nameToPhotoMapper.contains(catName)) {
                    String catPhotoUrl = preferences.getString(catName, "");
                    nameToPhotoMapper.put(catName, catPhotoUrl);
                }
            }
        }
        return isExists;
    }

    public Cat provideRandomCat() {
        int randomCatId = new Random().nextInt(catsNames.length);
        return new Cat(catsNames[randomCatId],
                randomCatId % 2 == 0 ? 1 : 2,
                "");
    }

    public List<Cat> provideCats(int count) {
        return generateRandomData(count);
    }

    @NonNull
    public String getCatPhoto(@NonNull String catName) {
        if (nameToPhotoMapper.containsKey(catName)) {
            return nameToPhotoMapper.get(catName);
        }
        return "";
    }

    private List<Cat> generateRandomData(int count) {
        List<Cat> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Cat item = new Cat();
            int randomNameId = new Random().nextInt(catsNames.length);
            item.setName(catsNames[randomNameId]);
            item.setType(randomNameId % 2 == 0 ? 1 : 2);
            result.add(item);
        }
        return result;
    }

    public boolean saveKittenPhoto(@NonNull Context context,
                                   @NonNull String catName,
                                   @NonNull String catPhoto) {
        nameToPhotoMapper.put(catName, catPhoto);
        SharedPreferences prefs = context.getSharedPreferences(CATS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();

        prefsEditor.putString(catName, catPhoto);

        return prefsEditor.commit();
    }

}
