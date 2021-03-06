package com.example.flowersapp

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flowersapp.adapter.FlowerAdapter
import com.example.flowersapp.databinding.ActivityMainBinding
import com.example.flowersapp.model.Flowers
import com.example.flowersapp.rest.RestApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var flowerAdapter: FlowerAdapter
    //Get network manager from the system service
    private val networkManager by lazy{
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        flowerAdapter = FlowerAdapter()

        //Assign active network
        val activeNetwork = networkManager.activeNetworkInfo

        activeNetwork?.let { myNetwork ->
            //Check if network available i.e. connected and not null
            if(myNetwork.isConnected){
                //Here we create background task to fetch info
                RestApi().retrofit.fetchFlowers()
                        //Everytime you use subscribeon you switch to a worker thread
                    .subscribeOn(Schedulers.io())
                        //Observe on lets you get the data in the main thread by using android schedulers
                    .observeOn(AndroidSchedulers.mainThread())
                //When you subscribe this is the time you can handle the error or success or the data
                    //.subscribe(this::handleSuccess, this::handleError)
                    .subscribe(
                        //Success with flowers object
                        {
                                flowers -> handleSuccess(flowers)
                                //Load flowers into adapter for recycler view
                                flowerAdapter.importFlowers(flowers)
                        },
                        //Error with throwable object
                        { error -> handleError(error) }
                    )
            }else{
                //Display error in a toast
                Toast.makeText(baseContext, "Connectivity issues", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.flowerRecycler.apply {
            adapter = flowerAdapter
            layoutManager = GridLayoutManager(baseContext, 1)
        }
    }

    private fun handleError(error: Throwable) {
        Log.d("NetErr", error.localizedMessage)
        Toast.makeText(baseContext, error.localizedMessage, Toast.LENGTH_LONG).show()
    }

    private fun handleSuccess(flowers: Flowers) {
        Toast.makeText(baseContext, flowers[0].name, Toast.LENGTH_LONG).show()
    }
}