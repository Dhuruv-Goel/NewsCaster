package com.example.newscaster.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newscaster.DataClass.NewsDataClass
import com.example.newscaster.DataClass.mainNewsClass
import com.example.newscaster.R
import com.example.newscaster.RecyclerViewAdapter.*
import com.example.newscaster.RetrofitInstance
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response


class TechnologyFragment : Fragment(), TechnoNewsItemClicked,ShareBtnClicked {
    private lateinit var arrayList: ArrayList<NewsDataClass>
    private lateinit var adapter: TechnoRVAdapter
    private  lateinit var country:String
    private  lateinit var category:String
    private lateinit var recyclerView: RecyclerView
    private lateinit var api:String
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var shimmerView: ShimmerFrameLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_technology, container, false)
        country ="in"
        category = "technology"
        api ="dd398d70a2194386b91362383db2e1cd"
        recyclerView = view.findViewById(R.id.rvtechno)
        shimmerView = view.findViewById(R.id.shimmer_view_container)
        arrayList =ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TechnoRVAdapter(this,this)
        recyclerView.adapter = adapter
        swipe = view.findViewById(R.id.swipeRefresh)
        swipe.setOnRefreshListener {
            getNewsData()
        }
        getNewsData()
        return view
    }

    private fun getNewsData() {
        RetrofitInstance.apiInterface.getCategoryNews(country,category,100,api).enqueue(object : retrofit2.Callback<mainNewsClass?> {
            override fun onResponse(
                call: Call<mainNewsClass?>,
                response: Response<mainNewsClass?>
            ) {
                if(response.isSuccessful){
                    shimmerView.stopShimmer()
                    shimmerView.visibility = View.GONE
                    arrayList.addAll(response.body()!!.articles)
                    adapter.updateNews(arrayList)
                }
            }

            override fun onFailure(call: Call<mainNewsClass?>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }
    override fun OnItemClicked(items: NewsDataClass) {
        val builder = CustomTabsIntent.Builder()
        val intent= builder.build()
        intent.launchUrl(requireContext(), Uri.parse(items.url))
    }
    override fun onButtonCheckedListener(items: NewsDataClass) {
        val message = "Download this app 'NewsCaster' and get regular update to every happenings in the India such that this news :" + items.url
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT,message)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent,"Share link to app:"))
    }


}