package com.api.senaibank.classe.service;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.api.senaibank.classe.Endereco;

@Service
public class ViaCepService {
    public static Endereco getEnderecoByCep(String cep) {
        Endereco endereco = new Endereco();
        OkHttpClient client = new OkHttpClient();

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        Request request = new Request.Builder().url(url).build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String result = response.body().string();

                Gson gson = new Gson();
                endereco = gson.fromJson(result, Endereco.class);

            } else {
                System.out.println("Erro ao buscar CEP:" + response.code());
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar CEP:" + e.getMessage());
        }
        return endereco;
    }

}
