package com.example.archek.aivytask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.archek.aivytask.RestApi.createService;

public class MainActivity extends AppCompatActivity implements CoinAdapter.Callback{

    private RecyclerView rv;
    private CoinAdapter adapter = new CoinAdapter(this);
    @Nullable private Call <ListResponse> call;
    private Service service = RestApi.createService( Service.class );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        rv = findViewById( R.id.recyclerView );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager( layoutManager );
        rv.setAdapter( adapter );

//        fillWithFakeDate();
        if(call != null && call.isExecuted()){
            return;
        }
        call = service.getCC( );
        //noinspection ConstantConditions
        call.enqueue( new Callback <ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response <ListResponse> response) {
                showContent();
                MainActivity.this.call = call.clone();
                ListResponse listResponse = response.body();
                if(listResponse != null){
                    adapter.addAll( listResponse.getResults() );
                }
            }

            @Override
            public void onFailure(Call <ListResponse> call, Throwable t) {
                if(call.isCanceled()){
                    Log.d("dasd", toString());
                }
            }
        } );
        showContent();
        showLoading();
    }

    private void showLoading(){
        rv.setVisibility( View.GONE );
    }

    private void showContent(){
        rv.setVisibility(View.VISIBLE);

    }

    @Override
    public void onCoinClick(Fields fields) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


//        Fields fields = new Fields(  "Bitcoin" );
//        String jsonFields = gson.toJson( fields );
//        Fields obj = new Gson().fromJson(jsonFields, Fields.class);
//        Service service = createService( Service.class );
//        Call<List<String>> call = service.getCC();
//        Callback<List<String >> callback = new Callback <List <String>>() {
//            @Override
//            public void onResponse(Call <List <String>> call, Response <List <String>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call <List <String>> call, Throwable t) {
//
//            }
//        };
//
//        call.enqueue(callback);
//    }

//    private void fillWithFakeDate() {
//        List<Fields> fakeList = new ArrayList <>(  );
//        for(int i = 0; i < 20; i++){
//            Fields fields;
//            if(i % 3 == 0){
//                fields = new Fields( "qe1" );
//            } else if(i % 3 == 1){
//                fields = new Fields( "fuck" );
//            }
//            else {
//                fields = new Fields( "Johny" );
//            }
//            fakeList.add(fields);
//            }
//            adapter.addAll( fakeList );
//    }




}

