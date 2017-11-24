package ru.shaldnikita.Tags.backend.model.dict;

public class Category {
   public static String FOOTBALL = "football";
   public static String GAMES= "games";
   public static String SPORT= "sport";
   public static String PROGRAMMING= "programming";


   private Category(){}

    public static String[] getAllRoles() {
        return new String[] {FOOTBALL,GAMES,SPORT,PROGRAMMING};
    }

}
