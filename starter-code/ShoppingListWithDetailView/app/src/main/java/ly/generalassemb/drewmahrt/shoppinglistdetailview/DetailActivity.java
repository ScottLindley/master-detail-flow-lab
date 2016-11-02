package ly.generalassemb.drewmahrt.shoppinglistdetailview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get ID of selected item
        int selectedItemId = getIntent().getIntExtra(DetailFragment.ITEM_ID_KEY, -1);

        // If we don't have a valid ID, no reason to continue
        if (selectedItemId == -1) {
            Log.d("DetailActivity", "onCreate: No ID passed on the intent!");
            finish();
        }

        Fragment detailFragment = DetailFragment.newInstance(selectedItemId);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.detail_fragment_frame, detailFragment)
                .commit();
    }
}
