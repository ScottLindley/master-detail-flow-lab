package ly.generalassemb.drewmahrt.shoppinglistdetailview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ly.generalassemb.drewmahrt.shoppinglistdetailview.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity implements ShoppingListAdapter.OnItemSelectedListener{
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        if(findViewById(R.id.detail_fragment_frame)!=null){
            mTwoPane = true;
        }else{
            mTwoPane = false;
        }


        //Setup the RecyclerView
        RecyclerView shoppingListRecyclerView = (RecyclerView) findViewById(R.id.shopping_list_recyclerview);

        ShoppingSQLiteOpenHelper db = ShoppingSQLiteOpenHelper.getInstance(this);
        List<ShoppingItem> shoppingList = db.getShoppingList();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        shoppingListRecyclerView.setLayoutManager(linearLayoutManager);
        shoppingListRecyclerView.setAdapter(new ShoppingListAdapter(shoppingList, this));

    }

    @Override
    public void onItemSelected(int itemID) {
        if (!mTwoPane) {
            // Create the intent
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);

            // Add the ID as an extra
            intent.putExtra(DetailFragment.ITEM_ID_KEY, itemID);

            // Start the detail activity
            startActivity(intent);
        }else{
            Fragment detailFragment = DetailFragment.newInstance(itemID);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_fragment_frame, detailFragment)
                    .commit();
        }
    }
}
