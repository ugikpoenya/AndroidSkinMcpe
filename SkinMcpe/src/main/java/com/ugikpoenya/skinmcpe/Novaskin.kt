package com.ugikpoenya.skinmcpe

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import java.io.Serializable

class Novaskin {

    fun getSearch(context: Context, keyword: String?, next: String, function: (next: String, skins: ArrayList<SkinModel>?) -> (Unit)) {
        val queue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Method.GET, "https://minecraft.novaskin.me/search?json=true&q=$keyword&next=$next",
            Response.Listener { response ->
                try {
                    val novaskinResponse = Gson().fromJson(response, NovaskinResponse::class.java)
                    function(novaskinResponse.pagination?.next.toString(), novaskinResponse.skins)
                } catch (e: Exception) {
                    function("", ArrayList())
                }
            },
            Response.ErrorListener {
                Log.d("LOG", "Error " + it.message)
                function("", ArrayList())
                if (!it.message.isNullOrEmpty()) {
                    Toast.makeText(
                        context,
                        "Failed to load, cek your internet connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                return headers
            }
        }
        queue.add(stringRequest)
    }


    private class NovaskinResponse : Serializable {
        var pagination: Pagination? = null
        var skins: ArrayList<SkinModel>? = null
    }

    private class Pagination : Serializable {
        var next: String? = ""
    }


}