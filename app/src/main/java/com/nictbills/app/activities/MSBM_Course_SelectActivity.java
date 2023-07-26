package com.nictbills.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.adapter.CourseTitleAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.MsbmResponse;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MSBM_Course_SelectActivity extends AppCompatActivity implements OnClickRecyclerViewInterface {
    private SmartMaterialSpinner subject_type_spinner,category_type_spinner;
    private List<String> subjectList, categoryList;
    private String selectedSubject, selectedCategory, CAT_CODE;
    private int SUB_CODE;
    private ArrayList<MsbmResponse> mList;
    private RecyclerView.LayoutManager layoutManager;
    private NestedScrollView nestedSV;
    private boolean loading = true;
    private int page = 0, lastPage=10;
    private String finalPage;
    private Button course_search_Button;
    private RecyclerView userRV;
    private ProgressBar loadingPB;
    private RetrofitInterface retroFitInterface;
    private LinearLayout no_category_match_linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_s_b_m__course__select);
        subject_type_spinner = findViewById(R.id.subject_type_spinner);
        category_type_spinner = findViewById(R.id.category_type_spinner);
        course_search_Button = findViewById(R.id.course_search_Button);
        no_category_match_linearLayout = findViewById(R.id.no_category_match_linearLayout);
        userRV = findViewById(R.id.idRVUsers);
        loadingPB = findViewById(R.id.idPBLoading);
        nestedSV = findViewById(R.id.idNestedSV);
        initSubjectValueList();
        initCategoriesValueList();

        userRV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(MSBM_Course_SelectActivity.this);
        userRV.setLayoutManager(layoutManager);
        userRV.setItemAnimator(new DefaultItemAnimator());
        loadingPB.setVisibility(View.GONE);


        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    page+=10;
                    //Log.e("shubbbbbbb+", String.valueOf(page));
                    loadingPB.setVisibility(View.VISIBLE);
                    getAllCourse(page, lastPage);
                }
            }
        });

        course_search_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedSubject = String.valueOf(subject_type_spinner.getSelectedItem());
                selectedCategory = String.valueOf(category_type_spinner.getSelectedItem());

                if ( !selectedSubject.equals("null") && selectedSubject.trim().length()>0 ) {

                    if (selectedSubject.trim().equalsIgnoreCase("Business Management")){
                        SUB_CODE = 510;
                    } else if (selectedSubject.trim().equalsIgnoreCase("Health and Psychology")){
                        SUB_CODE = 511;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Business Law")){
                        SUB_CODE = 512;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Information Technology")){
                        SUB_CODE = 513;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Personal Development")){
                        SUB_CODE = 514;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Teaching and Education")){
                        SUB_CODE = 515;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Health and Safety")){
                        SUB_CODE = 516;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Finance and Accounting")){
                        SUB_CODE = 517;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Office Productivity")){
                        SUB_CODE = 518;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Sales and Marketing")){
                        SUB_CODE = 519;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Leadership and Entrepreneurship")){
                        SUB_CODE = 520;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Human Resources")){
                        SUB_CODE = 521;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Project & Operations Management")){
                        SUB_CODE = 523;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Business Research")){
                        SUB_CODE = 524;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Hotel, Travel and Hospitality Management")){
                        SUB_CODE = 728;
                    }else if (selectedSubject.trim().equalsIgnoreCase("Logistics and Supply Chain Management")){
                        SUB_CODE = 729;
                    }else {

                        Toast.makeText(MSBM_Course_SelectActivity.this, "Please select subject", Toast.LENGTH_SHORT).show();
                    }

                    if ( !selectedCategory.equals("null") && selectedCategory.trim().length()>0 ){

                        if (selectedCategory.trim().equalsIgnoreCase("Professional Certificate Courses")){
                            CAT_CODE = "PCC";
                        } else if (selectedCategory.trim().equalsIgnoreCase("Advanced Professional Certificate Courses")){
                            CAT_CODE = "APC";
                        }else if (selectedCategory.trim().equalsIgnoreCase("Online SEMP")){
                            CAT_CODE = "SEM";
                        }else if (selectedCategory.trim().equalsIgnoreCase("Master's Degree")){
                            CAT_CODE = "MBA";
                        }else if (selectedCategory.trim().equalsIgnoreCase("International Postgraduate Diploma")){
                            CAT_CODE = "DIP";
                        }else if (selectedCategory.trim().equalsIgnoreCase("Bachelor's Degree")){
                            CAT_CODE = "BAC";
                        } else {
                            Toast.makeText(MSBM_Course_SelectActivity.this, "Please select Category", Toast.LENGTH_SHORT).show();
                        }

                        getAllCourseButton();

                        //getAllCourse(page, lastPage);
                        // imple

                    }else {

                        Toast.makeText(MSBM_Course_SelectActivity.this, "Please select Category", Toast.LENGTH_SHORT).show();
                    }

                }else {

                    Toast.makeText(MSBM_Course_SelectActivity.this, "Please select subject", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }

    private void setAdapter(){
        CourseTitleAdapter adapter = new CourseTitleAdapter(MSBM_Course_SelectActivity.this, mList,this);
        userRV.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MSBM_Course_SelectActivity.this,DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    private void getAllCourse(int page, int limit) {


        if (page > limit) {
            // checking if the page number is greater than limit.
            // displaying toast message in this case when page>limit.
            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();
            loadingPB.setVisibility(View.GONE);
            return;

        }

        loadingPB.setVisibility(View.VISIBLE);

        retroFitInterface = RetrofitClient.getClient(Constant.MSBM_BASE_URL).create(RetrofitInterface.class);
        Call<ResponseBody> call = retroFitInterface.getAllCourse(Constant.PUBLISHER_ID,"","IND",SUB_CODE,CAT_CODE,1,page);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                loadingPB.setVisibility(View.GONE);

                String xml = null;
                try {
                    xml = response.body().string().trim().replaceAll(System.getProperty("line.separator"), "");
                    //  xml = response.body().string();

                    Log.e("Shubhammmmm",xml);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                XmlToJson xmlToJson = new XmlToJson.Builder(xml).build();
                try{
                    JSONObject emp=(new JSONObject(xmlToJson.toString().trim())).getJSONObject("response");

                    finalPage=emp.getString("totalResults");
                   // Log.e("fffff++++",finalPage);
                    lastPage = Integer.parseInt(finalPage);
                    //limit = emp.getInt("totalResults");
                    JSONObject results = emp.getJSONObject("results");
                    JSONArray resultArray = results.getJSONArray("result");
                    //text.setText(""+resultArray.length());

                    mList=new ArrayList<>();

                    for (int i = 0; i < resultArray.length(); i++) {

                        MsbmResponse bean=new MsbmResponse();

                        JSONObject object1 = resultArray.getJSONObject(i);
//                        String dfdsf=emp.getString("deliveryFormat");
//                        JSONObject objectDetails2 = object1.getJSONObject("courseTitle");
                        String snippet = object1.getString("snippet").trim();
                        String thumbnail = object1.getString("thumbnail").trim();
                        String url = object1.getString("url").trim();
                        String courseTitle = object1.getString("courseTitle").trim();
                        String duration = object1.getString("duration").trim();
                        String effort = object1.getString("effort").trim();
                        JSONObject costObject = object1.getJSONObject("cost");
                        String amount = costObject.getString("amount").trim();
                        String img_path = object1.getString("thumbnail").trim();

                        Log.e("imgggg+",img_path);


                        bean.setAmount(amount);
                        bean.setCourseTitle(courseTitle);
                        bean.setDuration(duration);
                        bean.setSnippet(snippet);
                        bean.setThumbnail(thumbnail);
                        bean.setEffort(effort);
                        bean.setUrl(url);
                        bean.setCourseIMG(img_path);
                        mList.add(bean);
                        Log.e("checkData","data:- "+snippet);
                    }
                    Log.e("sizzzeeee+", String.valueOf(mList.size()));
                    if (mList.size()==0){

                        Toast.makeText(MSBM_Course_SelectActivity.this, "NO record found", Toast.LENGTH_SHORT).show();

                    }else {

                        setAdapter();

                    }


                }catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // progressLog.dismiss();
                loadingPB.setVisibility(View.GONE);
                //Log.e("MSG",t.getMessage());

                Toast.makeText(MSBM_Course_SelectActivity.this, "something_went_wrong_please_try_again_later", Toast.LENGTH_SHORT).show();
            }

        });
    }


    private void getAllCourseButton() {

        final ProgressDialog progressLog = new ProgressDialog(MSBM_Course_SelectActivity.this);
        progressLog.setTitle("loading");
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();
        retroFitInterface = RetrofitClient.getClient(Constant.MSBM_BASE_URL).create(RetrofitInterface.class);
        Call<ResponseBody> call = retroFitInterface.getAllCourse(Constant.PUBLISHER_ID,"","IND",SUB_CODE,CAT_CODE,1,10);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //loadingPB.setVisibility(View.GONE);
                progressLog.dismiss();
                String xml = null;
                try {
                    xml = response.body().string().trim().replaceAll(System.getProperty("line.separator"), "");
                    //  xml = response.body().string();

                    Log.e("Shubhammmmm",xml);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                XmlToJson xmlToJson = new XmlToJson.Builder(xml).build();
                try {
                    JSONObject jsonObject = (new JSONObject(xmlToJson.toString().trim())).getJSONObject("response");

                    Log.e("Jsonnn+++", xmlToJson.toString().trim());

                    if (!jsonObject.has("results")) {
                        //get Value of video
                        no_category_match_linearLayout.setVisibility(View.VISIBLE);
                        nestedSV.setVisibility(View.GONE);
                        // Toast.makeText(MSBM_Course_SelectActivity.this, "Select another category", Toast.LENGTH_SHORT).show();

                    } else {
                        no_category_match_linearLayout.setVisibility(View.GONE);
                        nestedSV.setVisibility(View.VISIBLE);
                        finalPage = jsonObject.getString("totalResults");
                        Log.e("fffff++++", finalPage);
                        lastPage = Integer.parseInt(finalPage);
                        //limit = emp.getInt("totalResults");


                        JSONObject results = jsonObject.getJSONObject("results");

                        try {
                            JSONArray resultArray = results.getJSONArray("result");
                            //text.setText(""+resultArray.length());

                            mList = new ArrayList<>();

                            for (int i = 0; i < resultArray.length(); i++) {

                                MsbmResponse bean = new MsbmResponse();

                                JSONObject object1 = resultArray.getJSONObject(i);
//                        String dfdsf=emp.getString("deliveryFormat");
//                        JSONObject objectDetails2 = object1.getJSONObject("courseTitle");
                                String snippet = object1.getString("snippet").trim();
                                String thumbnail = object1.getString("thumbnail").trim();
                                String url = object1.getString("url").trim();
                                String courseTitle = object1.getString("courseTitle").trim();
                                String duration = object1.getString("duration").trim();
                                String effort = object1.getString("effort").trim();
                                JSONObject costObject = object1.getJSONObject("cost");
                                String amount = costObject.getString("amount").trim();

                                //JSONObject subjectImgObject = object1.getJSONObject("snippet");
                                // String snippet = object1.getString("snippet").trim();
                                String img_path = object1.getString("thumbnail").trim();

                                Log.e("imgggg+", img_path);

                                bean.setAmount(amount);
                                bean.setCourseTitle(courseTitle);
                                bean.setDuration(duration);
                                bean.setSnippet(snippet);
                                bean.setThumbnail(thumbnail);
                                bean.setEffort(effort);
                                bean.setUrl(url);
                                bean.setCourseIMG(img_path);
                                mList.add(bean);
                                Log.e("checkData", "data:- " + snippet);
                            }

                            //Log.e("sizzzeeee+", String.valueOf(mList.size()));

                            setAdapter();

                        }catch (JSONException e){
                            e.printStackTrace();
                            no_category_match_linearLayout.setVisibility(View.VISIBLE);
                            nestedSV.setVisibility(View.GONE);
                            // here write your code to get value from Json object which is not getJSONArray.
                        }

                    }
//                    String str="Employee Name:"+empname+"\n"+"Employee Salary:"+empsalary;
//                    text.setText(str);
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressLog.dismiss();
                //loadingPB.setVisibility(View.GONE);
                //Log.e("MSG",t.getMessage());

                Toast.makeText(MSBM_Course_SelectActivity.this, "something_went_wrong_please_try_again_later", Toast.LENGTH_SHORT).show();
            }

        });
    }







    private void initSubjectValueList() {
        //spEmptyItem = findViewById(R.id.sp_empty_item);

        String[] values = new String[] { "Business Management", "Health and Psychology", "Business Law", "Information Technology", "Personal Development", "Teaching and Education", "Health and Safety", "Finance and Accounting", "Office Productivity", "Sales and Marketing", "Leadership and Entrepreneurship", "Human Resources", "Project & Operations Management", "Business Research", "Hotel, Travel and Hospitality Management", "Logistics and Supply Chain Management"};

        subjectList = new ArrayList<>();

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            subjectList.add(values[i]);

        }
        subject_type_spinner.setItem(subjectList);

        subject_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    private void initCategoriesValueList() {

        //spEmptyItem = findViewById(R.id.sp_empty_item);

        String[] values = new String[] { "Professional Certificate Courses", "Advanced Professional Certificate Courses", "Online SEMP", "Master's Degree", "International Postgraduate Diploma", "Bachelor's Degree"};

        categoryList = new ArrayList<>();

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            categoryList.add(values[i]);

        }
        category_type_spinner.setItem(categoryList);

        category_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onListItem(View view, int adapterPosition) {

        String URL = mList.get(adapterPosition).getUrl();

        switch (view.getId()){

            case R.id.book_course_BTN:

               // Log.e("URL++++",URL);

                Intent intent = new Intent(MSBM_Course_SelectActivity.this,MSBMDashboardActivity.class);
                intent.putExtra("course_link",URL);
                startActivity(intent);
                finish();
                break;
        }

    }
}