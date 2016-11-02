package ly.generalassemb.drewmahrt.shoppinglistdetailview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;


public class DetailFragment extends Fragment {


    public static final String ITEM_ID_KEY = "itemIdKey";
    private ShoppingItem mSelectedItem;
    private int mSelectedItemID;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(int itemID) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ITEM_ID_KEY, itemID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSelectedItemID = getArguments().getInt(ITEM_ID_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TAG", "trying to inflate fragment");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView name = (TextView) view.findViewById(R.id.detail_name);
        TextView description = (TextView) view.findViewById(R.id.detail_description);
        TextView price = (TextView) view.findViewById(R.id.detail_price);
        TextView category = (TextView) view.findViewById(R.id.detail_category);

        mSelectedItem = ShoppingSQLiteOpenHelper.getInstance(getContext())
                .getShoppingItemById(mSelectedItemID);


        // Populate the TextViews
        Log.d("TAG", "selected itmem name :"+mSelectedItem.getName());
        name.setText(mSelectedItem.getName());
        description.setText(mSelectedItem.getDescription());
        category.setText(mSelectedItem.getType());

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        double priceValue = Double.valueOf(mSelectedItem.getPrice());
        price.setText(currencyFormat.format(priceValue));
    }
}
