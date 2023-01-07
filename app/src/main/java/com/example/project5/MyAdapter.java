package com.example.project5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * adapter for the recycler view for pizza options
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private String[] data;
    private int[] images;
    private Context context;

    private static final int NY_CRUST_INDEX = 3;
    private static final int CHICAGO_CRUST_INDEX = 2;

    /**
     * constructor for myadapter
     * @param ct the context from which it is being called from
     * @param data the string array with the pizza options
     * @param images the resources integers for all the images
     */
    public MyAdapter(Context ct, String[] data, int[] images){
        context = ct;
        this.data = data;
        this.images = images;
    }


    /**
     * creates layout inflater obj and inflate the my_row xml
     * @param parent ViewGroup
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * dynamically sets each row with image and pizza option
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pizzaOption_txt.setText(data[position]);
        holder.cardImage.setImageResource(images[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderPageActivity.class);
                int index = 0;
                String[] dataInfo = data[holder.getAdapterPosition()].split(" ");
                if(dataInfo.length <= 0)
                    return;

                if(dataInfo[0].equalsIgnoreCase("New")){
                    index = NY_CRUST_INDEX;
                }
                else{
                    index = CHICAGO_CRUST_INDEX;
                }
                intent.putExtra("data", dataInfo[index]);
                intent.putExtra("image", images[holder.getAdapterPosition()]);
                intent.putExtra("style", dataInfo[0]);
                intent.putExtra("flavor", findFlavor(data[holder.getAdapterPosition()]));
                context.startActivity(intent);
            }
        });
    }

    /**
     * returns the flavor of the current pizza option
     * @param data
     * @return
     */
    private String findFlavor(String data){
        int indexOfComma = data.indexOf(",");
        int indexOfFlavor = data.indexOf("Flavor");
        if(indexOfFlavor == -1){
            return data.substring(indexOfComma+2);
        }
        else{
            return data.substring(indexOfComma+2);
        }
    }

    /**
     *
     * @return the size of the recycler view
     */
    @Override
    public int getItemCount() {
        return data.length;
    }

    /**
     * holder for the recycler view
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pizzaOption_txt;
        ImageView cardImage;
        ConstraintLayout mainLayout;

        /**
         * constructor
         * @param itemView the item being added to the recycler view
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaOption_txt = itemView.findViewById(R.id.pizzaOption_txt);
            cardImage = itemView.findViewById(R.id.cardImage);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
