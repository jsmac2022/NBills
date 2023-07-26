package com.nictbills.app.activities.household.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nictbills.app.R;
import com.nictbills.app.activities.household.adepter.SearchAdapter;
import com.nictbills.app.activities.household.model.Search;
import com.nictbills.app.activities.household.model.SearchChildcatdataItem;
import com.nictbills.app.activities.household.retrofit.APIClient;
import com.nictbills.app.activities.household.retrofit.GetResult;
import com.nictbills.app.activities.household.utils.CustPrograssbar;
import com.nictbills.app.activities.household.utils.SessionManager;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class SearchActivity extends BasicActivity implements GetResult.MyListener, SearchAdapter.RecyclerTouchListener {

    @BindView(R.id.lvl_actionsearch)
    LinearLayout lvlActionsearch;
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.recycler_product)
    RecyclerView recyclerProduct;
    CustPrograssbar custPrograssbar;
    SessionManager sessionManager;
    //User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        custPrograssbar = new CustPrograssbar();
        sessionManager = new SessionManager(SearchActivity.this);
       // user = sessionManager.getUserDetails("");
        recyclerProduct.setLayoutManager(new LinearLayoutManager(this));
        edSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (!edSearch.getText().toString().isEmpty()) {
                    getSearch(edSearch.getText().toString());
                }
                return true;
            }
            return false;
        });

    }

    private void getSearch(String s) {
        custPrograssbar.prograssCreate(SearchActivity.this);
        Call<JsonObject> call = APIClient.getInterface().getSearch(APIClient.APPEND_URL+"search?service="+s);
        GetResult getResult = new GetResult();
        getResult.setMyListener(this);
        getResult.callForLogin(call, "1");
    }

    @Override
    public void callback(JsonObject result, String callNo) {
        try {
            custPrograssbar.closePrograssBar();
            if (callNo.equalsIgnoreCase("1")) {
                Gson gson = new Gson();
                Search search = gson.fromJson(result.toString(), Search.class);
                if(search.getResponse().equals("200")){
                SearchAdapter adapter = new SearchAdapter(this,search.getResult(), this);
                recyclerProduct.setAdapter(adapter);
            }}
        } catch (Exception e) {
            Log.e("Error","-->"+e.toString());
        }
    }

    @Override
    public void onClickSearch(SearchChildcatdataItem dataItem, int position) {

        Toast.makeText(this, dataItem.getMsc()+"-"+dataItem.getSsc(), Toast.LENGTH_SHORT).show();

//        startActivity(new Intent(this, ItemListActivity.class)
//                .putExtra("name", dataItem.getTitle())
//                .putExtra("cid", dataItem.getCategoryId())
//                .putExtra("sid", dataItem.getSubcatId()));
    }
}