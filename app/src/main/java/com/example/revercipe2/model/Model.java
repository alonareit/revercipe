package com.example.revercipe2.model;

        import android.os.Handler;
        import android.os.Looper;
        import android.util.Log;

        import androidx.core.os.HandlerCompat;

        import java.util.AbstractMap;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
        import java.util.concurrent.Executor;
        import java.util.concurrent.Executors;

public class Model {


    public static final Model instance = new Model();

    Executor executor =Executors.newFixedThreadPool(1);
    Handler mainThread = HandlerCompat.createAsync(Looper.getMainLooper());
    ModelFirebase modelFirebase = new ModelFirebase();
    private Model() {
        //categories for hashmap


        Ingredients cucumber = new Ingredients("cucumber", "R.drawable.cucumber", false);
        vegetablesList.add(cucumber);
        Ingredients tomato = new Ingredients("tomato", "drawable/tomato.png", false);
        vegetablesList.add(tomato);
        Ingredients lettuce = new Ingredients("lettuce", "drawable/lettuce.png", false);
        vegetablesList.add(lettuce);
        Ingredients parsley = new Ingredients("parsley", "drawable/parsley.png", false);
        vegetablesList.add(parsley);
        Ingredients olive = new Ingredients("olive", "drawable/olive.png", false);
        vegetablesList.add(olive);

        Ingredients cheese = new Ingredients("cheese", "drawable/cheese.png", false);
        dairyList.add(cheese);
        Ingredients chocolate = new Ingredients("chocolate", "drawable/chocolate.png", false);
        dairyList.add(chocolate);
        Ingredients milk = new Ingredients("milk", "drawable/milk.png", false);
        dairyList.add(milk);

        allingredients.add(cucumber);
        allingredients.add(tomato);
        allingredients.add(lettuce);
        allingredients.add(parsley);
        allingredients.add(olive);
        allingredients.add(cheese);
        allingredients.add(chocolate);
        allingredients.add(milk);
        allingredients.add(milk);
        allingredients.add(milk);
        Collections.sort(allingredients);
        Collections.sort(vegetablesList);
        Collections.sort(dairyList);



//    Ingredients dough=new Ingredients("dough","Carbohydrates",,false);
//
//    Ingredients egg=new Ingredients("egg","others",,false);
//    Ingredients oil=new Ingredients("oil","others",,false);
//    Ingredients salt=new Ingredients("salt","others",,false);


        //2
//        categoriesList.put("vegetables",vegetablesList);
//        categoriesList.put("dairy", dairyList);


    }
//    Map<String, List<Ingredients>> categoriesList = new HashMap<>();
//
//    public Map<String, List<Ingredients>> getCategoriesList() {
//        return categoriesList;
//    }
//
//    public void setCategoriesList(Map<String, List<Ingredients>> categoriesList) {
//        this.categoriesList = categoriesList;
//    }

    List<Ingredients> allingredients= new ArrayList<Ingredients>();
    List<Ingredients> vegetablesList = new ArrayList<Ingredients>();
    List<Ingredients> dairyList = new ArrayList<Ingredients>();


    public interface GetAllIngredientsListener{
        void onComplete(List<Ingredients> list);
    }
    public void getAllIngredients(GetAllIngredientsListener listener) {
        modelFirebase.getAllIngredients(listener);
    }

    public interface AddIngredientListener{
        void onComplete();
    }
    public void addIngredient(Ingredients ingredient, AddIngredientListener listener){
        modelFirebase.addIngredient(ingredient,listener);
    }


    public List<Ingredients> getVegetablesList() {
       return vegetablesList;
    }

    public List<Ingredients> getDairyList() {
       return dairyList;
    }
}


