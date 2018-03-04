package com.innovatorlabs.androidarchitecturecomponents.db;

import com.innovatorlabs.androidarchitecturecomponents.db.entity.DescriptionEntity;
import com.innovatorlabs.androidarchitecturecomponents.db.entity.NameEntity;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    private static String[] names = {"Kranthi", "Mahesh", "Vinay", "Swapna", "Nithin", "Varnika"};

    private static String[] dob = {"08/01/94", "05/06/1992", "24/05/1995", "23/08/1990", "02/06/2012", "20/10/2016"};

    private static String[] nicks = {"Kiran", "Mahi", "Vinu", "Sappi", "Nithu", "Varu"};

    public static List<NameEntity> generateNames(){

        List<NameEntity> namesList = new ArrayList<>();

        for(int i = 0; i < names.length; i++){

            NameEntity nameEntity = new NameEntity(i + 1, names[i]);

            namesList.add(nameEntity);
        }

        return namesList;
    }

    public static List<DescriptionEntity> generateDescriptions(){

        List<DescriptionEntity> descriptionsList = new ArrayList<>();

        for(int i = 0; i < names.length; i++){

            DescriptionEntity nameEntity = new DescriptionEntity(i + 1, names[i], dob[i], nicks[i]);

            descriptionsList.add(nameEntity);
        }

        return descriptionsList;
    }
}
