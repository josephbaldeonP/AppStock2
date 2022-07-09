package com.example.consumoapirest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPIService {

@GET("Obtener_producto.php")
    Call<UResponse>getUniversidades();


}
