package bd.org.bitm.mad.batch33.retrofitexample;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  implements Filterable{

    private List<Product> products;
    private List<Product> filteredList;


    public ProductAdapter(List<Product> products) {
        this.products = products;
        this.filteredList = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = filteredList.get(position);

        holder.textViewName.setText(product.getName());
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();
                List<Product>tempList = new ArrayList<>();
                if(query.isEmpty()){
                    filteredList = products;
                }else{
                    for(Product p : products){
                        if(p.getName().toLowerCase().contains(query.toLowerCase())){
                            tempList.add(p);
                        }
                    }
                    filteredList = tempList;
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (List<Product>) results.values;
                notifyDataSetChanged();//refresh recyclerview items
            }
        };
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName,textViewPrice;
        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
