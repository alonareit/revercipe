 package com.example.revercipe2;

        import android.graphics.Color;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.revercipe2.model.Ingredients;
        import com.example.revercipe2.model.Model;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Map;
        import com.squareup.picasso.Picasso;


public class ingredientsFragment extends Fragment {
    //    Map<String, List<Ingredients>> categoriesList;
    List<Ingredients> allingredients;
    List<Ingredients> selectedItemsList;
    EditText editText;
    CheckBox cb;
    Button all_btn,vegetables_btn, carbohydrates_btn, dairy_btn,other_btn;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients,container,false);
 //       categoriesList = Model.instance.getCategoriesList();
        selectedItemsList = new ArrayList<>();

        RecyclerView ingredientslist_rv = view.findViewById(R.id.ingredientsList_rv);
        ingredientslist_rv.setHasFixedSize(true);
        ingredientslist_rv.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerView ingredientsSelected_rv = view.findViewById(R.id.ingredientsSelected_rv);
        ingredientsSelected_rv.setHasFixedSize(true);
        ingredientsSelected_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        adapter = new MyAdapter();
        ingredientslist_rv.setAdapter(adapter);

        ImageAdapter imageAdapter = new ImageAdapter();
        ingredientsSelected_rv.setAdapter(imageAdapter);


        //search
        EditText editText = view.findViewById(R.id.search_et);
        editText.setHint(" Search for ingredients");
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<Ingredients> filteredIngredientsList = new ArrayList<>();
                for(Ingredients item: allingredients){
                    if (item.getName().toLowerCase().contains(s.toString().toLowerCase())){
                        filteredIngredientsList.add(item);
                    }
                }
                adapter.filterList(filteredIngredientsList);


            }
        });

        //categories
        all_btn= view.findViewById(R.id.all_btn);
        vegetables_btn= view.findViewById(R.id.vegetables_btn);
        carbohydrates_btn= view.findViewById(R.id.carbohydrate_btn);
        dairy_btn= view.findViewById(R.id.dairy_btn);
        other_btn= view.findViewById(R.id.other_btn);

        all_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          //      adapter.filterList(Model.instance.getAllIngredients());
            }
        });

        vegetables_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.filterList(Model.instance.getVegetablesList());
            }
        });

        dairy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.filterList(Model.instance.getDairyList());
            }
        });

        refresh();
        return view;
    }

    private void refresh()
    {
        Model.instance.getAllIngredients((list)->{
            allingredients = list;
            adapter.notifyDataSetChanged();
        });
    }

    //the data for the each ingredient row
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ingNameTv;
        CheckBox ingFlagCb;
        ImageView ingImgIv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ingNameTv= itemView.findViewById(R.id.ingredients_listrow_tv);
            ingFlagCb= itemView.findViewById(R.id.ingredients_listrow_cb);
            ingImgIv= itemView.findViewById(R.id.ingredient_listrow_iv);
        }

//        void bind(Ingredients ingredient){
//            ingNameTv.setText(ingredient.getName());
//            ingFlagCb.setChecked(ingredient.isFlag());
//            ingImgIv.setImageResource(R.drawable.chocolate);
//            if (ingredient.getImgPath() != null) {
//                Picasso.get()
//                        .load(ingredient.getImgPath())
//                        .into(ingImgIv);
//            }
//        }
    }

    //adapter is used to show the data on the screen
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @NonNull
        @Override
        //crate the view of each ingredient row
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.row_item_ingredients_list,parent,false);
            MyViewHolder holder =new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Ingredients ingredient = allingredients.get(position);
            holder.ingNameTv.setText(ingredient.getName());
            holder.ingFlagCb.setChecked(ingredient.isFlag());
            Picasso.get().load(R.drawable.cheese).into(holder.ingImgIv);





            holder.ingFlagCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        selectedItemsList.add(ingredient);
                        ingredient.setFlag(true);

                        Log.d("list",selectedItemsList.toString());

                    } else {
                        ingredient.setFlag(false);
                        Log.d("list",selectedItemsList.toString());
                        selectedItemsList.remove(ingredient);

                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return allingredients.size();
        }

        public void filterList(List<Ingredients> filteredList){
            allingredients = filteredList;
            notifyDataSetChanged();

        }
    }




//    public class ImageAdapter extends RecyclerView.Adapter {
//
//        @NonNull
//        @Override
//        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int             viewType) {
//            View itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.row_item_selected_ingredients, parent, false);
//            return new ImageViewHolder(itemView);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
//            Ingredients ingredient = allingredients.get(position);
//            Picasso.get().load(R.drawable.cheese).into(holder.ingImgIv);
//
//            selectedItemsList = new ArrayList<>();
//            holder.ingFlagCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    if(b){
//                        selectedItemsList.add(ingredient);
//                        ingredient.setFlag(true);
//                        Log.d("list",selectedItemsList.toString());
//
//                    } else {
//                        ingredient.setFlag(false);
//                        Log.d("list",selectedItemsList.toString());
//                        selectedItemsList.remove(ingredient);
//
//                    }
//                }
//            });
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return selectedItemsList.size();
//        }
//
//
//        public class ImageViewHolder extends RecyclerView.ViewHolder {
//            ImageView ingImgIv;
//            CheckBox ingFlagCb;
//
//            public ImageViewHolder(View itemView) {
//                super(itemView);
//                ingImgIv= itemView.findViewById(R.id.seletedItem_iv);
//                ingFlagCb= itemView.findViewById(R.id.ingredients_listrow_cb);
//            }
//        }
//    }


    class ImageViewHolder extends RecyclerView.ViewHolder{
        CheckBox ingFlagCb;
        ImageView ingImgIv;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            ingFlagCb= itemView.findViewById(R.id.ingredients_listrow_cb);
            ingImgIv= itemView.findViewById(R.id.selected_listrow_iv);
        }

    }

    //adapter is used to show the data on the screen
    class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder>{

        @NonNull
        @Override
        //crate the view of each ingredient row
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int             viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_item_selected_ingredients, parent, false);
            return new ImageViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            Ingredients ingredient = allingredients.get(position);
            Picasso.get().load(R.drawable.cheese).into(holder.ingImgIv);
//            selectedItemsList = new ArrayList<>();
//            holder.ingFlagCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    if(b){
//                        selectedItemsList.add(ingredient);
//                        ingredient.setFlag(true);
//                        Picasso.get().load(R.drawable.cheese).into(holder.ingImgIv);
//                        Log.d("list",selectedItemsList.toString());
//
//                    } else {
//                        ingredient.setFlag(false);
//                        Log.d("list",selectedItemsList.toString());
//                        selectedItemsList.remove(ingredient);
//
//                    }
//                }
//            });

//            if(ingredient.isFlag()){
//
////                holder.ingImgIv.setVisibility(View.VISIBLE);
//                Picasso.get().load(R.drawable.cheese).into(holder.ingImgIv);
//            }
//            else {
////                holder.ingImgIv.setVisibility(View.GONE);
//            }

        }

        @Override
        public int getItemCount() {
            if(selectedItemsList!=null){
                return selectedItemsList.size();
            }
            return 0;
        }

    }

}

