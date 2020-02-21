package com.yourapplets.androiddevelopbasic;

import com.yourapplets.androiddevelopbasic.network.BaseResponse;

public class LoginResponse extends BaseResponse {

    public String getName(){
        return (String) getData();
    }

    public void setName(String name){
        setData(name);
    }
}
