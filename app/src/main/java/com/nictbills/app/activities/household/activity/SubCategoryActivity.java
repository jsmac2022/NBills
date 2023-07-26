package com.nictbills.app.activities.household.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nictbills.app.R;
import com.nictbills.app.activities.household.adepter.SubCategoryAdapter;
import com.nictbills.app.activities.household.model.Chiald;
import com.nictbills.app.activities.household.model.ChildcatItem;
import com.nictbills.app.activities.household.retrofit.APIClient;
import com.nictbills.app.activities.household.retrofit.GetResult;
import com.nictbills.app.activities.household.utils.CustPrograssbar;
import com.nictbills.app.activities.household.utils.SessionManager;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class SubCategoryActivity extends AppCompatActivity implements SubCategoryAdapter.RecyclerTouchListener, GetResult.MyListener {
    @BindView(R.id.plyer)
    VideoView plyer;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_named)
    TextView txtNameD;
    @BindView(R.id.my_recycler_subcat)
    RecyclerView myRecyclerSubcat;
    String cid;
    String sid;
    String name;
    String named;
    String videoUrl;
    CustPrograssbar custPrograssbar;
    SessionManager sessionManager;
   // User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_sub_category);
        ButterKnife.bind(this);
        custPrograssbar = new CustPrograssbar();
        sessionManager = new SessionManager(SubCategoryActivity.this);
        //user = sessionManager.getUserDetails("");
        cid = getIntent().getStringExtra("cid");
        sid = getIntent().getStringExtra("sid");
        name = getIntent().getStringExtra("name");
        named = getIntent().getStringExtra("named");
        videoUrl = getIntent().getStringExtra("vurl");

        txtName.setText("" + name);
        txtNameD.setText("" + named);
        plyer.setVideoURI(Uri.parse(APIClient.baseUrl + "/" + videoUrl));
        plyer.requestFocus();


        plyer.setOnCompletionListener(mp -> plyer.start());
        plyer.setOnPreparedListener(mp -> plyer.start());

        myRecyclerSubcat.setLayoutManager(new GridLayoutManager(SubCategoryActivity.this, 3));
        myRecyclerSubcat.setItemAnimator(new DefaultItemAnimator());

        getData();
    }

    private void getData() {
        custPrograssbar.prograssCreate(this);
        Call<JsonObject> call = APIClient.getInterface().sChildList(APIClient.APPEND_URL+"subchildlist?main_serv_code="+cid+"&sub_serv_code="+sid);
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
                Chiald chiald = gson.fromJson(result.toString(), Chiald.class);
                if (chiald.getReponse().equalsIgnoreCase("200")) {
                    SubCategoryAdapter adapter = new SubCategoryAdapter(this, chiald.getResult(), this);
                    myRecyclerSubcat.setAdapter(adapter);
                }

            }

        } catch (Exception e) {
            Log.e("Error", "" + e.toString());

        }
    }

    @OnClick({R.id.img_back})
    public void onClick(View view) {
        if (view.getId() == R.id.img_back) {
            finish();
        }
    }

    @Override
    public void onClickSubCategoryItem(ChildcatItem item, int position) {

       // Toast.makeText(this, cid+"-"+sid, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,HouseHoldVendorActivity.class);
        intent.putExtra("msc",cid);
        intent.putExtra("ssc",sid);
        startActivity(intent);

//        startActivity(new Intent(this, ItemListActivity.class)
//                .putExtra("name", name)
//                .putExtra("cid", cid)
//                .putExtra("sid", sid)
//                .putExtra("chid", item.getChildcatId()));
    }


}