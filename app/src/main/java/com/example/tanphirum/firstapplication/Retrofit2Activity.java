package com.example.tanphirum.firstapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tanphirum.firstapplication.pojo.UserItem;
import com.example.tanphirum.firstapplication.webservice.APIClient;
import com.example.tanphirum.firstapplication.webservice.APIUserInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Retrofit2Activity extends AppCompatActivity {

    private ImageView mImgProfile;
    private EditText mEdtUsername;
    private Button mBtnGetData;

    private View mViewProgress;

    private APIUserInterface mApiUserInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);

        mApiUserInterface = APIClient.getClient().create(APIUserInterface.class);

        mImgProfile = findViewById(R.id.img_profile);
        mEdtUsername = findViewById(R.id.edt_username);

        mViewProgress = findViewById(R.id.view_progress);

        mBtnGetData = findViewById(R.id.btn_get_data);

        mBtnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Call<UserItem> getUserList = mApiUserInterface.doGetUserList("2");
                getUserList.enqueue(new Callback<UserItem>() {
                    @Override
                    public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                        if (response.isSuccessful()) {
                            UserItem userItem = response.body();
                            Picasso.get().load("https://z-p3-scontent.fpnh5-1.fna.fbcdn.net/v/t1.0-9/27545098_1557130707667769_3036406450539851267_n.jpg?_nc_cat=0&oh=e3610040d392cfedd9e9e12d9925392e&oe=5BB5077B").into(mImgProfile);
                            Log.d("phirum", userItem.data.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserItem> call, Throwable e) {
                        /*if (e instanceof HttpException) {
                            //ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            MessageUtil.displayToast(context, context.getString(R.string.msg_can_not_connect_server));
                        } else if (e instanceof SocketTimeoutException) {
                            MessageUtil.displayToast(context, context.getString(R.string.msg_can_not_connect_internet));
                        } else if (e instanceof IOException) {
                            MessageUtil.displayToast(context, context.getString(R.string.msg_can_not_connect_internet));
                        } else
                            MessageUtil.displayToast(context, context.getString(R.string.msg_can_not_connect_server));*/
                        //Toast.makeText(v.getContext(), "error " + errorBundle.getRawMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                /*Call<RegisterItem> call = mApiUserInterface.doRegister("testEmail@gmail.com", "1234", null);
                call.enqueue(new Callback<RegisterItem>() {
                    @Override
                    public void onResponse(Call<RegisterItem> call, Response<RegisterItem> response) {
                        if (response.isSuccessful() && response.errorBody() == null) {
                            RegisterItem item = response.body();
                            Toast.makeText(v.getContext(), "Register success with token " + item.token, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterItem> call, Throwable t) {
                        Toast.makeText(v.getContext(), "error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });*/

                /*final RegisterItem registerItem = new RegisterItem("");
                registerItem.withEmail("dd@gmail.com");
                registerItem.withPassword("sdfds");
                Call<Object> callLogin = mApiUserInterface.doLogin(registerItem, 3);
                callLogin.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if (response.isSuccessful() && response.errorBody() == null) {
                            Toast.makeText(v.getContext(), "Login success with token " + response.body(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Toast.makeText(v.getContext(), "error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        });
    }
}
